package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.*;
import edu.sabanciuniv.nanuvcell.exception.AuthenticationException;
import edu.sabanciuniv.nanuvcell.exception.HomeInternetNotFoundException;
import edu.sabanciuniv.nanuvcell.exception.MobileTariffNotFoundException;
import edu.sabanciuniv.nanuvcell.exception.UserNotFoundException;
import edu.sabanciuniv.nanuvcell.model.*;
import edu.sabanciuniv.nanuvcell.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository repository;
    private final HomeInternetService homeInternetService;
    private final MobileTariffService mobileTariffService;
    private final RemainingUseService remainingUseService;
    private final InvoiceService invoiceService;

    public UserService(UserRepository repository,
                       HomeInternetService homeInternetService,
                       MobileTariffService mobileTariffService,
                       RemainingUseService remainingUseService, InvoiceService invoiceService) {
        this.repository = repository;
        this.homeInternetService = homeInternetService;
        this.mobileTariffService = mobileTariffService;
        this.remainingUseService = remainingUseService;
        this.invoiceService = invoiceService;
    }


    public class CreditCardLimitGenerator {
        private static final double MIN_LIMIT = 250.0;
        private static final double MAX_LIMIT = 50000.0;

        public static double generateRandomLimit() {
            Random random = new Random();
            return MIN_LIMIT + (MAX_LIMIT - MIN_LIMIT) * random.nextDouble();
        }
    }

    public UserDto findUserById(Long id) {
        User user = findUserByGivenId(id);

        return UserDto.convert(user);
    }

    protected User findUserByGivenId(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found by id : " + id));
    }

    public LoginResponseDto login(LoginRequest request) {
        User user = repository.findByEmail(request.email())
                .orElseThrow(
                        () -> new UserNotFoundException("User not found by email : " + request.email()));

        if (request.password().equals(user.getPassword())) {
            return new LoginResponseDto(user.getId(), user.getRole());
        } else {
            throw new AuthenticationException("Credentials is wrong");
        }
    }

    private boolean emailInUse(String email) {
        Optional<User> user = repository.findByEmail(email);

        if (user.isPresent()) {
            return true;
        }

        return false;
    }

    public void registerUserToTheHomeInternet(RegisterTariffRequest request) {
        HomeInternet homeInternet = homeInternetService.findHomeInternetById(request);

        User user = findUserByGivenId(request.userId());

        if (user.getHomeInternet() != null) {
            throw new HomeInternetNotFoundException("User already has a home internet !!!");
        }

        if (!user.getRole().equals(homeInternet.getRole())) {
            throw new HomeInternetNotFoundException("User is " + user.getRole() + " but he want + " + homeInternet.getRole() + " tariff");
        }

        if((user.getCreditCardNumber().equals(request.creditCardNumber()) &&
                user.getCreditCardCVV().equals(request.creditCardCVV()) &&
                user.getCreditCardPassword().equals(request.creditCardPassword()))){
            if((user.getCreditCardLimit())>=homeInternet.getTariffPrice()){
                user.setCreditCardLimit(user.getCreditCardLimit()- homeInternet.getTariffPrice());
                LocalDateTime registrationDate = LocalDateTime.now();
                LocalDateTime tariffEndDate = registrationDate.plusYears(1);
                user.setHomeInternetTariffStartDate(registrationDate);
                user.setHomeInternetTariffEndDate(tariffEndDate);
                user.setHomeInternet(homeInternet);

            }else{
                throw new AuthenticationException("Credit card balance insufficient");
            }

        }else {
            throw new AuthenticationException("Credit card information incorrect");
        }

        if(user.getRole()!=Roles.SURVIVOR){
            user.setRole(Roles.CUSTOMER);
        }

        repository.save(user);

        remainingUseService.createRemainingUseHomeInternet(user,homeInternet);

        invoiceService.createHomeInternetTariffInvoice(user,homeInternet);


    }

    public void registerUserToTHeMobileTariff(RegisterTariffRequest request) {
        MobileTariff mobileTariff = mobileTariffService.findMobileTariffById(request.tariffId());

        User user = findUserByGivenId(request.userId());

        if (user.getMobileTariff() != null) {
            throw new MobileTariffNotFoundException("User already has a mobile tariff !!!");
        }

        if (!user.getRole().equals(mobileTariff.getRole())) {
            throw new MobileTariffNotFoundException("User is " + user.getRole() + " but he want + " + mobileTariff.getRole() + " tariff");
        }

        if((user.getCreditCardNumber().equals(request.creditCardNumber()) &&
                user.getCreditCardCVV().equals(request.creditCardCVV()) &&
                user.getCreditCardPassword().equals(request.creditCardPassword()))){
            if((user.getCreditCardLimit())>=mobileTariff.getTariffPrice()){
                user.setCreditCardLimit(user.getCreditCardLimit()- mobileTariff.getTariffPrice());
                LocalDateTime registrationDate = LocalDateTime.now();
                LocalDateTime tariffEndDate = registrationDate.plusYears(1);
                user.setMobileTariffStartDate(registrationDate);
                user.setMobileTariffEndDate(tariffEndDate);
                user.setMobileTariff(mobileTariff);
            }else{
                throw new AuthenticationException("Credit card balance insufficient");
            }

        }else {
            throw new AuthenticationException("Credit card information incorrect");
        }

        if(user.getRole()!=Roles.SURVIVOR){
            user.setRole(Roles.CUSTOMER);
        }

        repository.save(user);

        remainingUseService.createRemainingUse(user, mobileTariff);

        if(mobileTariff instanceof InvoiceMobile){
            invoiceService.createPostPaidMobileTariffInvoice(user,mobileTariff);
        }

    }

    private boolean isSurvivor(String address) {

        String[] disasterCities = { "Kahramanmaraş", "Hatay", "Adıyaman", "Osmaniye", "Gaziantep", "Adana", "Kilis"
                , "Malatya", "Diyarbakır", "Şanlıurfa", "Elazığ"};

        for(String searchDisasterCity:disasterCities){
            if(address.toLowerCase().contains(searchDisasterCity.toLowerCase())){
                return true;
            }

        }

        return false;

    }

    public void createUser(CreateUserRequest request) {

        if(request.name()==null||request.surName()==null||request.gender()==null||request.phoneNumber()==null||
                request.address()==null||request.email()==null||request.password()==null||
                request.creditCardNumber()==null||request.creditCardCVV()==null||request.creditCardPassword()==null){
            throw new AuthenticationException("Any of the fields cannot be left blank");
        }

        if(request.password().length()<6){
            throw new AuthenticationException("Password must have at least 6 characters");
        }

        if(isSurvivor(request.address())){
            User user = User.builder()
                    .name(request.name())
                    .surName(request.surName())
                    .gender(request.gender())
                    .phoneNumber(request.phoneNumber())
                    .address(request.address())
                    .email(request.email())
                    .password(request.password())
                    .role(Roles.SURVIVOR)
                    .creditCardNumber(request.creditCardNumber())
                    .creditCardCVV(request.creditCardCVV())
                    .creditCardPassword(request.creditCardPassword())
                    .creditCardLimit(CreditCardLimitGenerator.generateRandomLimit())
                    .build();

            repository.save(user);
        }

        else if(!emailInUse(request.email())){
            User user = User.builder()
                    .name(request.name())
                    .surName(request.surName())
                    .gender(request.gender())
                    .phoneNumber(request.phoneNumber())
                    .address(request.address())
                    .email(request.email())
                    .password(request.password())
                    .role(Roles.NEW)
                    .creditCardNumber(request.creditCardNumber())
                    .creditCardCVV(request.creditCardCVV())
                    .creditCardPassword(request.creditCardPassword())
                    .creditCardLimit(CreditCardLimitGenerator.generateRandomLimit())
                    .build();

            repository.save(user);
        }

        else if (emailInUse(request.email())) {
            throw new AuthenticationException("Email already in use!!!");
        }

    }

    public void deleteUserById(Long id) {

        if(findUserByGivenId(id)==null){
            throw new UserNotFoundException("User not found by id : " + id);
        }

        User user = findUserByGivenId(id);
        Double invoiceTotalAmount = invoiceService.getTotalInvoicesAmountByUser(user);

        if(invoiceTotalAmount!=0){
            throw new AuthenticationException("Please pay your unpaid bills..");
        }

        repository.deleteById(id);
    }

    public void payAllInvoices(InvoicePaymentRequest request){
        User user = findUserByGivenId(request.userId());

        List<InvoiceDto> invoiceDtoList = invoiceService.getInvoicesDtosByUserId(user.getId());

        long count = invoiceDtoList.stream().filter(invoiceDto -> invoiceDto.invoicePaymentDate().isAfter(LocalDateTime.now())).count();

        if(count==2){
            throw new AuthenticationException("You don't have any invoices to pay! Please wait your Invoice Payment Date!");
        }
        Double invoiceTotalAmount = invoiceService.getTotalInvoicesAmountByUser(user);

        if(invoiceTotalAmount==0){
            throw new AuthenticationException("You don't have any invoices to pay!");
        }

        if((user.getCreditCardNumber().equals(request.creditCardNumber()) &&
                user.getCreditCardCVV().equals(request.creditCardCVV()) &&
                user.getCreditCardPassword().equals(request.creditCardPassword()))){
            if((user.getCreditCardLimit())>=invoiceTotalAmount){
                user.setCreditCardLimit(user.getCreditCardLimit()- invoiceTotalAmount);

            }else{
                throw new AuthenticationException("Credit card balance insufficient");
            }

        }else {
            throw new AuthenticationException("Credit card information incorrect");
        }

        repository.save(user);

    }

    public void payMobileInvoice(InvoicePaymentRequest request){
        User user = findUserByGivenId(request.userId());

        List<Invoice> invoiceList = invoiceService.getInvoicesByUserId(user.getId());

        Invoice mobileTariffInvoice = null;

        for(Invoice invoice:invoiceList){
            if(invoice.getTariff() instanceof MobileTariff){
                mobileTariffInvoice = invoice;
            }
        }

        Tariff tariffToCreateNewInvoice = mobileTariffInvoice.getTariff();

        if(mobileTariffInvoice.getInvoiceAmountPaymentDate().isAfter(LocalDateTime.now())){
            throw new AuthenticationException("You don't have any mobile invoices to pay! Please wait your Invoice Payment Date!");
        }

        Double mobileTariffInvoiceAmount = mobileTariffInvoice.getInvoiceAmount();

        if(mobileTariffInvoiceAmount==0){
            throw new AuthenticationException("You don't have any invoices to pay!");
        }

        if((user.getCreditCardNumber().equals(request.creditCardNumber()) &&
                user.getCreditCardCVV().equals(request.creditCardCVV()) &&
                user.getCreditCardPassword().equals(request.creditCardPassword()))){
            if((user.getCreditCardLimit())>=mobileTariffInvoiceAmount){
                user.setCreditCardLimit(user.getCreditCardLimit()- mobileTariffInvoiceAmount);

            }else{
                throw new AuthenticationException("Credit card balance insufficient");
            }

        }else {
            throw new AuthenticationException("Credit card information incorrect");
        }

        invoiceService.deleteMobileInvoicesByUserId(user);
        invoiceService.createAnotherPostPaidMobileTariffInvoice(user, (MobileTariff) tariffToCreateNewInvoice);
        remainingUseService.deleteRemainingUseByUserId(user.getId());
        remainingUseService.createRemainingUse(user,(MobileTariff) tariffToCreateNewInvoice);
        repository.save(user);

    }

    public void payHomeInternetInvoice(InvoicePaymentRequest request){
        User user = findUserByGivenId(request.userId());

        List<Invoice> invoiceList = invoiceService.getInvoicesByUserId(user.getId());

        Invoice homeInternetInvoice = null;

        for(Invoice invoice:invoiceList){
            if(invoice.getTariff() instanceof HomeInternet){
                homeInternetInvoice = invoice;
            }
        }

        Tariff tariffToCreateNewInvoice = homeInternetInvoice.getTariff();

        if(homeInternetInvoice.getInvoiceAmountPaymentDate().isAfter(LocalDateTime.now())){
            throw new AuthenticationException("You don't have any mobile invoices to pay! Please wait your Invoice Payment Date!");
        }

        Double homeInternetInvoiceAmount = homeInternetInvoice.getInvoiceAmount();

        if(homeInternetInvoiceAmount==0){
            throw new AuthenticationException("You don't have any invoices to pay!");
        }

        if((user.getCreditCardNumber().equals(request.creditCardNumber()) &&
                user.getCreditCardCVV().equals(request.creditCardCVV()) &&
                user.getCreditCardPassword().equals(request.creditCardPassword()))){
            if((user.getCreditCardLimit())>=homeInternetInvoiceAmount){
                user.setCreditCardLimit(user.getCreditCardLimit()- homeInternetInvoiceAmount);

            }else{
                throw new AuthenticationException("Credit card balance insufficient");
            }

        }else {
            throw new AuthenticationException("Credit card information incorrect");
        }

        invoiceService.deleteHomeInternetInvoiceByUserId(user);
        invoiceService.createAnotherHomeInternetTariffInvoice(user, (HomeInternet) tariffToCreateNewInvoice);
        remainingUseService.deleteRemainingUseHomeInternetByUserId(user.getId());
        remainingUseService.createRemainingUseHomeInternet(user,(HomeInternet) tariffToCreateNewInvoice);
        repository.save(user);

    }

    public InvoiceDto viewMobileInvoiceByUserId(Long id) {
        User user = findUserByGivenId(id);

        List<Invoice> invoiceList = invoiceService.getInvoicesByUserId(user.getId());

        Invoice mobileTariffInvoice = null;

        for (Invoice invoice : invoiceList) {
            if (invoice.getTariff() instanceof MobileTariff) {
                mobileTariffInvoice = invoice;
            }
        }
        return InvoiceDto.convert(mobileTariffInvoice);


    }

    public InvoiceDto viewHomeInternetInvoiceByUserId(Long id) {

        User user = findUserByGivenId(id);

        List<Invoice> invoiceList = invoiceService.getInvoicesByUserId(user.getId());

        Invoice homeInternetInvoice = null;

        for (Invoice invoice : invoiceList) {
            if (invoice.getTariff() instanceof HomeInternet) {
                homeInternetInvoice = invoice;
            }
        }
        return InvoiceDto.convert(homeInternetInvoice);

    }


}
