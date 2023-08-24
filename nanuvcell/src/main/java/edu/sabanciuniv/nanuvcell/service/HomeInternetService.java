package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.AdslDto;
import edu.sabanciuniv.nanuvcell.dto.HomeInternetDto;
import edu.sabanciuniv.nanuvcell.dto.RegisterTariffRequest;
import edu.sabanciuniv.nanuvcell.exception.HomeInternetNotFoundException;
import edu.sabanciuniv.nanuvcell.model.HomeInternet;
import edu.sabanciuniv.nanuvcell.model.User;
import edu.sabanciuniv.nanuvcell.repository.HomeInternetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeInternetService {

    private final HomeInternetRepository repository;

    public HomeInternetService(HomeInternetRepository repository) {
        this.repository = repository;
    }

    protected HomeInternet findHomeInternetById(RegisterTariffRequest request) {
        return repository.findById(request.tariffId())
                .orElseThrow(
                        () -> new HomeInternetNotFoundException("Home Internet not found by id : " + request.tariffId()));
    }

    public List<HomeInternetDto> findAllHomeInternet() {
        return repository.findAll()
                .stream()
                .map(HomeInternetDto::convert)
                .collect(Collectors.toList());
    }
}
