package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.CreateInvoiceRequest;
import edu.sabanciuniv.nanuvcell.dto.InvoiceDto;
import edu.sabanciuniv.nanuvcell.dto.UserDto;
import edu.sabanciuniv.nanuvcell.model.*;
import edu.sabanciuniv.nanuvcell.repository.InvoiceRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository repository;
    //private final UserService userService;
    private final TariffService tariffService;


    public InvoiceService(InvoiceRepository repository, /*UserService userService, */TariffService tariffService) {
        this.repository = repository;
        //this.userService = userService;
        this.tariffService = tariffService;
    }

    public List<InvoiceDto> getInvoicesDtosByUserId(Long id) {
        return repository.findAll()
                .stream()
                .filter(i -> {
                    if (i.getUser().getId() == id) {
                        return true;
                    }
                    return false;
                })
                .map(InvoiceDto::convert)
                .collect(Collectors.toList());
    }

    public List<Invoice> getInvoicesByUserId(Long id) {
        return repository.findAll()
                .stream()
                .filter(i -> {
                    if (i.getUser().getId() == id) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    protected void createPostPaidMobileTariffInvoice(User user, MobileTariff mobileTariff){

        Invoice postPaidTariffInvoice = Invoice.builder()
                .user(user)
                .tariff(mobileTariff)
                .invoiceAmount(mobileTariff.getTariffPrice())
                .invoiceAmountPaymentDate(user.getMobileTariffStartDate().plusMonths(1))
                .invoiceAmountPaymentLastDate(user.getMobileTariffStartDate().plusMonths(1).plusDays(7))
                .build();

        repository.save(postPaidTariffInvoice);
    }

    protected void createAnotherPostPaidMobileTariffInvoice(User user, MobileTariff mobileTariff){

        long numberOfInvoice = ChronoUnit.MONTHS.between(user.getMobileTariffStartDate(),LocalDateTime.now());

        Invoice postPaidTariffInvoice = Invoice.builder()
                .user(user)
                .tariff(mobileTariff)
                .invoiceAmount(mobileTariff.getTariffPrice())
                .invoiceAmountPaymentDate(user.getMobileTariffStartDate().plusMonths(numberOfInvoice))
                .invoiceAmountPaymentLastDate(user.getMobileTariffStartDate().plusMonths(numberOfInvoice).plusDays(7))
                .build();

        repository.save(postPaidTariffInvoice);
    }

    protected void createHomeInternetTariffInvoice(User user, HomeInternet homeInternet){

        Invoice homeInternetTariffInvoice = Invoice.builder()
                .user(user)
                .tariff(homeInternet)
                .invoiceAmount(homeInternet.getTariffPrice())
                .invoiceAmountPaymentDate(user.getHomeInternetTariffStartDate().plusMonths(1))
                .invoiceAmountPaymentLastDate(user.getHomeInternetTariffStartDate().plusMonths(1).plusDays(7))
                .build();

        repository.save(homeInternetTariffInvoice);
    }

    protected void createAnotherHomeInternetTariffInvoice(User user, HomeInternet homeInternet){

        long numberOfInvoice = ChronoUnit.MONTHS.between(user.getHomeInternetTariffStartDate(),LocalDateTime.now());

        Invoice homeInternetTariffInvoice = Invoice.builder()
                .user(user)
                .tariff(homeInternet)
                .invoiceAmount(homeInternet.getTariffPrice())
                .invoiceAmountPaymentDate(user.getHomeInternetTariffStartDate().plusMonths(numberOfInvoice))
                .invoiceAmountPaymentLastDate(user.getHomeInternetTariffStartDate().plusMonths(numberOfInvoice).plusDays(7))
                .build();

        repository.save(homeInternetTariffInvoice);
    }

    protected double getTotalInvoicesAmountByUser(User user){
        List<Invoice> invoiceList = user.getInvoiceList();

        double invoiceTotalamount = 0;
        for(Invoice invoice:invoiceList){
            invoiceTotalamount += invoice.getInvoiceAmount();
        }

        return invoiceTotalamount;

    }


    /*@Transactional
    protected void deleteInvoicesByUserId(User user) {

        user.getInvoiceList().stream().forEach(invoice->repository.deleteByInvoiceId(invoice.getId()));

    }*/

    @Transactional
    protected void deleteMobileInvoicesByUserId(User user) {

        List<Invoice> invoiceList = user.getInvoiceList();

        Invoice mobileTariffInvoice = null;

        for(Invoice invoice:invoiceList){
            if(invoice.getTariff() instanceof MobileTariff){
                mobileTariffInvoice = invoice;
            }
        }

        repository.deleteMobileInvoiceByInvoiceId(mobileTariffInvoice.getId());


    }

    @Transactional
    protected void deleteHomeInternetInvoiceByUserId(User user) {

        List<Invoice> invoiceList = user.getInvoiceList();

        Invoice homeInternetInvoice = null;

        for(Invoice invoice:invoiceList){
            if(invoice.getTariff() instanceof HomeInternet){
                homeInternetInvoice = invoice;
            }
        }

        repository.deleteHomeInternetInvoiceByInvoiceId(homeInternetInvoice.getId());

    }

}
