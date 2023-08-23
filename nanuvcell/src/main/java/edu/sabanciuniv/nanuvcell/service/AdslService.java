package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.AdslDto;
import edu.sabanciuniv.nanuvcell.dto.CreateAdslRequest;
import edu.sabanciuniv.nanuvcell.exception.AdslNotFoundException;
import edu.sabanciuniv.nanuvcell.model.Adsl;
import edu.sabanciuniv.nanuvcell.repository.AdslRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdslService {

    private final AdslRepository repository;

    public AdslService(AdslRepository repository) {
        this.repository = repository;
    }

    public void createAdsl(CreateAdslRequest request) {

        DateTimeFormatter formatter
                = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        Adsl adsl = Adsl.builder()
                .tariffName(request.tariffName())
                .internetSpeed(request.internetSpeed())
                //.tariffStartDate(LocalDateTime.parse(request.tariffStartDate(), formatter))
                //.tariffEndDate(LocalDateTime.parse(request.tariffEndDate(), formatter))
                .role(request.role())
                .internetQuota(request.internetQuota())
                .uploadSpeed(request.uploadSpeed())
                .downloadSpeed(request.downloadSpeed())
                .tariffPrice(request.tariffPrice())
                .build();

        repository.save(adsl);
    }

    public void createAdslList(List<CreateAdslRequest> requestList) {

        for (CreateAdslRequest request:requestList){
            Adsl adsl = Adsl.builder()
                    .tariffName(request.tariffName())
                    .internetSpeed(request.internetSpeed())
                    .role(request.role())
                    .internetQuota(request.internetQuota())
                    .uploadSpeed(request.uploadSpeed())
                    .downloadSpeed(request.downloadSpeed())
                    .tariffPrice(request.tariffPrice())
                    .build();

            repository.save(adsl);
        }

    }

    public List<AdslDto> findAllAdsl() {
        return repository.findAll()
                .stream()
                .map(AdslDto::convert)
                .collect(Collectors.toList());
    }

    protected Adsl findAdslById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new AdslNotFoundException("Adsl not found by id : " + id));
    }

    public AdslDto findAdslByGivenId(Long id) {
        Adsl adsl = findAdslById(id);

        return AdslDto.convert(adsl);
    }
}
