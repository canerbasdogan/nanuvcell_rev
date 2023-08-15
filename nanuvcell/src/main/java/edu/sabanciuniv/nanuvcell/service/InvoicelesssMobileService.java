package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.CreateInvoicelessMobileRequest;
import edu.sabanciuniv.nanuvcell.dto.InvoicelessMobileDto;
import edu.sabanciuniv.nanuvcell.exception.InvoicelessMobileNotFoundException;
import edu.sabanciuniv.nanuvcell.model.InvoicelessMobile;
import edu.sabanciuniv.nanuvcell.repository.InvoicelessMobileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoicelesssMobileService {

    private final InvoicelessMobileRepository repository;

    public InvoicelesssMobileService(InvoicelessMobileRepository repository) {
        this.repository = repository;
    }

    public void createInvoicelessMobile(CreateInvoicelessMobileRequest request) {

        DateTimeFormatter formatter
                = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        InvoicelessMobile invoicelessMobile = InvoicelessMobile.builder()
                .tariffName(request.tariffName())
                .internetQuota(request.internetQuota())
                .tariffPrice(request.tariffPrice())
                .tariffStartDate(LocalDateTime.parse(request.tariffStartDate(), formatter))
                .tariffEndDate(LocalDateTime.parse(request.tariffEndDate()))
                .role(request.role())
                .speakingQuota(request.speakingQuota())
                .messageQuota(request.messageQuota())
                .tlQuota(request.tlQuota())
                .build();

        repository.save(invoicelessMobile);
    }

    public List<InvoicelessMobileDto> findAllInvoicelessMobile() {
        return repository.findAll()
                .stream()
                .map(InvoicelessMobileDto::convert)
                .collect(Collectors.toList());
    }

    protected InvoicelessMobile findInvoicelessMobileById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new InvoicelessMobileNotFoundException("Invoiceless Mobile not found by id : " + id));
    }

    public InvoicelessMobileDto findInvoicelessMobileByGivenId(Long id) {
        InvoicelessMobile invoicelessMobile = findInvoicelessMobileById(id);

        return InvoicelessMobileDto.convert(invoicelessMobile);
    }
}
