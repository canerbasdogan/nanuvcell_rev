package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.*;
import edu.sabanciuniv.nanuvcell.exception.AuthenticationException;
import edu.sabanciuniv.nanuvcell.exception.HomeInternetNotFoundException;
import edu.sabanciuniv.nanuvcell.exception.MobileTariffNotFoundException;
import edu.sabanciuniv.nanuvcell.exception.UserNotFoundException;
import edu.sabanciuniv.nanuvcell.model.HomeInternet;
import edu.sabanciuniv.nanuvcell.model.MobileTariff;
import edu.sabanciuniv.nanuvcell.model.Roles;
import edu.sabanciuniv.nanuvcell.model.User;
import edu.sabanciuniv.nanuvcell.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final HomeInternetService homeInternetService;
    private final MobileTariffService mobileTariffService;
    private final RemainingUseService remainingUseService;

    public UserService(UserRepository repository,
                       HomeInternetService homeInternetService,
                       MobileTariffService mobileTariffService,
                       RemainingUseService remainingUseService) {
        this.repository = repository;
        this.homeInternetService = homeInternetService;
        this.mobileTariffService = mobileTariffService;
        this.remainingUseService = remainingUseService;
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
            throw new HomeInternetNotFoundException("User have already home internet !!!");
        }

        if (!user.getRole().equals(homeInternet.getRole())) {
            throw new HomeInternetNotFoundException("User is " + user.getRole() + " but he want + " + homeInternet.getRole() + " tariff");
        }

        user.setHomeInternet(homeInternet);

        repository.save(user);
    }

    public void registerUserToTHeMobileTariff(RegisterTariffRequest request) {
        MobileTariff mobileTariff = mobileTariffService.findMobileTariffById(request.tariffId());

        User user = findUserByGivenId(request.userId());

        if (user.getMobileTariff() != null) {
            throw new MobileTariffNotFoundException("User have already mobile tariff !!!");
        }

        if (!user.getRole().equals(mobileTariff.getRole())) {
            throw new HomeInternetNotFoundException("User is " + user.getRole() + " but he want + " + mobileTariff.getRole() + " tariff");
        }

        user.setMobileTariff(mobileTariff);

        repository.save(user);

        remainingUseService.createRemainingUse(user, mobileTariff);
    }

    public void createUser(CreateUserRequest request) {

        if (emailInUse(request.email())) {
            throw new AuthenticationException("Email already in use!!!");
        }

        User user = User.builder()
                .name(request.name())
                .surName(request.surName())
                .gender(request.gender())
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .email(request.email())
                .password(request.password())
                .role(request.role())
                .build();

        repository.save(user);
    }

    public void deleteUserById(Long id) {
        User user = findUserByGivenId(id);
        repository.deleteById(id);
    }
}
