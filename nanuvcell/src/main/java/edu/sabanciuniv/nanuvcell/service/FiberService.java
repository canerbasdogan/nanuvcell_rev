package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.CreateFiberRequest;
import edu.sabanciuniv.nanuvcell.dto.FiberDto;
import edu.sabanciuniv.nanuvcell.exception.FiberNotFoundException;
import edu.sabanciuniv.nanuvcell.model.Fiber;
import edu.sabanciuniv.nanuvcell.repository.FiberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiberService {

    private final FiberRepository repository;

    public FiberService(FiberRepository repository) {
        this.repository = repository;
    }

    public void createFiber(CreateFiberRequest request) {

        DateTimeFormatter formatter
                = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        Fiber fiber = Fiber.builder()
                .tariffName(request.tariffName())
                .internetQuota(request.internetQuota())
                .tariffPrice(request.tariffPrice())
                //.tariffStartDate(LocalDateTime.parse(request.tariffStartDate(), formatter))
                //.tariffEndDate(LocalDateTime.parse(request.tariffEndDate(), formatter))
                .role(request.role())
                .internetSpeed(request.internetSpeed())
                .fiberInfrastructure(request.fiberInfrastructure())
                .build();

        repository.save(fiber);
    }

    public List<FiberDto> findAllFiber() {
        return repository.findAll()
                .stream()
                .map(FiberDto::convert)
                .collect(Collectors.toList());
    }

    protected Fiber findFiberById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new FiberNotFoundException("Fiber not found by id : " + id));
    }

    public FiberDto findFiberByGivenId(Long id) {
        Fiber fiber = findFiberById(id);

        return FiberDto.convert(fiber);
    }
}
