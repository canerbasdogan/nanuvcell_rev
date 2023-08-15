package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.UserDto;
import edu.sabanciuniv.nanuvcell.exception.UserNotFoundException;
import edu.sabanciuniv.nanuvcell.model.User;
import edu.sabanciuniv.nanuvcell.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserFindService {

    private final UserRepository repository;

    public UserFindService(UserRepository repository) {
        this.repository = repository;
    }

    
}
