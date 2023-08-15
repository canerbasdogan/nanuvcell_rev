package edu.sabanciuniv.nanuvcell;

import edu.sabanciuniv.nanuvcell.dto.RegisterTariffRequest;
import edu.sabanciuniv.nanuvcell.dto.UserDto;
import edu.sabanciuniv.nanuvcell.model.*;
import edu.sabanciuniv.nanuvcell.repository.AdslRepostiroy;
import edu.sabanciuniv.nanuvcell.repository.InvoicelessMobileRepository;
import edu.sabanciuniv.nanuvcell.repository.RemainingUseRepository;
import edu.sabanciuniv.nanuvcell.repository.UserRepository;
import edu.sabanciuniv.nanuvcell.service.AdslService;
import edu.sabanciuniv.nanuvcell.service.HomeInternetService;
import edu.sabanciuniv.nanuvcell.service.RemainingUseService;
import edu.sabanciuniv.nanuvcell.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class  NanuvcellApplication {

	public static void main(String[] args) {
		SpringApplication.run(NanuvcellApplication.class, args);
	}

}
