package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.CreateInvoiceMobileRequest;
import edu.sabanciuniv.nanuvcell.dto.InvoiceMobileDto;
import edu.sabanciuniv.nanuvcell.exception.InvoiceMobileNotFoundException;
import edu.sabanciuniv.nanuvcell.model.InvoiceMobile;
import edu.sabanciuniv.nanuvcell.model.InvoicelessMobile;
import edu.sabanciuniv.nanuvcell.repository.InvoiceMobileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceMobileService {

    private final InvoiceMobileRepository repository;

    public InvoiceMobileService(InvoiceMobileRepository repository) {
        this.repository = repository;
    }

    public void createInvoiceMobile(CreateInvoiceMobileRequest request) {

        DateTimeFormatter formatter
                = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        InvoiceMobile invoiceMobile = InvoiceMobile.builder()
                .tariffName(request.tariffName())
                .internetQuota(request.internetQuota())
                .tariffPrice(request.tariffPrice())
                .tariffStartDate(LocalDateTime.parse(request.tariffStartDate(), formatter))
                .tariffEndDate(LocalDateTime.parse(request.tariffEndDate(), formatter))
                .role(request.role())
                .speakingQuota(request.speakingQuota())
                .messageQuota(request.messageQuota())
                .limitlessInternet(request.limitlessInternet())
                .build();

        repository.save(invoiceMobile);
    }

    public List<InvoiceMobileDto> findAllInvoiceMobile() {
        return repository.findAll()
                .stream()
                .map(InvoiceMobileDto::convert)
                .collect(Collectors.toList());
    }

    protected InvoiceMobile findInvoiceMobile(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new InvoiceMobileNotFoundException("Invoice Mobile not found by id : " + id));
    }

    public InvoiceMobileDto findInvoiceMobileByGivenId(Long id) {
        InvoiceMobile invoiceMobile = findInvoiceMobile(id);

        return InvoiceMobileDto.convert(invoiceMobile);
    }
}
