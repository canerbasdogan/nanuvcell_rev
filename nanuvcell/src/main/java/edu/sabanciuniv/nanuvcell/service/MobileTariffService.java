package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.HomeInternetDto;
import edu.sabanciuniv.nanuvcell.dto.MobileTariffDto;
import edu.sabanciuniv.nanuvcell.exception.MobileTariffNotFoundException;
import edu.sabanciuniv.nanuvcell.model.MobileTariff;
import edu.sabanciuniv.nanuvcell.repository.MobileTariffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MobileTariffService {

    private final MobileTariffRepository repository;

    public MobileTariffService(MobileTariffRepository repository) {
        this.repository = repository;
    }

    protected MobileTariff findMobileTariffById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new MobileTariffNotFoundException("Mobile Tariff not found by id : " + id));
    }

    public List<MobileTariffDto> findAllMobileTariff() {
        return repository.findAll()
                .stream()
                .map(MobileTariffDto::convert)
                .collect(Collectors.toList());
    }
}
