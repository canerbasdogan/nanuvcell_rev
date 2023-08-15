package edu.sabanciuniv.nanuvcell.exception;

import edu.sabanciuniv.nanuvcell.model.MobileTariff;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AdslNotFoundException.class)
    public ResponseEntity<GenericException> adslNotFoundException(AdslNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AnnouncementNotFoundException.class)
    public ResponseEntity<GenericException> announcementNotFoundException(AnnouncementNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TariffNotFoundException.class)
    public ResponseEntity<GenericException> tariffNotFoundException(TariffNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GenericException> authenticationNotFoundException(AuthenticationException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CampaignNotFoundException.class)
    public ResponseEntity<GenericException> campaignNotFoundException(CampaignNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FiberNotFoundException.class)
    public ResponseEntity<GenericException> fiberNotFoundException(FiberNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HomeInternetNotFoundException.class)
    public ResponseEntity<GenericException> homeInternetNotFoundException(HomeInternetNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvoicelessMobileNotFoundException.class)
    public ResponseEntity<GenericException> invoicelessMobileNotFoundException(InvoicelessMobileNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvoiceMobileNotFoundException.class)
    public ResponseEntity<GenericException> invoiceMobileNotFoundException(InvoiceMobileNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MobileTariffNotFoundException.class)
    public ResponseEntity<GenericException> mobileTariffNotFoundException(MobileTariffNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RemainingUseNotFoundException.class)
    public ResponseEntity<GenericException> remainingUseNotFoundException(RemainingUseNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<GenericException> storeNotFoundException(StoreNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GenericException> userNotFoundException(UserNotFoundException exception) {
        GenericException genericException = GenericException.builder()
                .message(exception.getMessage())
                .time(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(genericException, HttpStatus.NOT_FOUND);
    }
}
