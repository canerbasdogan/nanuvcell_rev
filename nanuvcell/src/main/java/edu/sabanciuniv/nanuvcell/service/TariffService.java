package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.exception.TariffNotFoundException;
import edu.sabanciuniv.nanuvcell.model.Tariff;
import edu.sabanciuniv.nanuvcell.repository.TariffRepository;
import org.springframework.stereotype.Service;

@Service
public class TariffService {

    private final TariffRepository repository;

    public TariffService(TariffRepository repository) {
        this.repository = repository;
    }

    protected Tariff findTariffById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new TariffNotFoundException("Tariff not found by id : " + id));
    }
}
