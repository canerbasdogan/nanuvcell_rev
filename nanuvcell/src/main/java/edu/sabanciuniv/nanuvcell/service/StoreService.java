package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.StoreDto;
import edu.sabanciuniv.nanuvcell.exception.StoreNotFoundException;
import edu.sabanciuniv.nanuvcell.model.Store;
import edu.sabanciuniv.nanuvcell.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository repository;

    public StoreService(StoreRepository repository) {
        this.repository = repository;
    }

    public List<StoreDto> findAllStore() {
        return repository.findAll()
                .stream()
                .map(StoreDto::convert)
                .collect(Collectors.toList());
    }

    public StoreDto findStoreById(int id) {
        Store store = findStoreByGivenId(id);

        return StoreDto.convert(store);
    }

    private Store findStoreByGivenId(int id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new StoreNotFoundException("Store not found by id : " + id));
    }
}
