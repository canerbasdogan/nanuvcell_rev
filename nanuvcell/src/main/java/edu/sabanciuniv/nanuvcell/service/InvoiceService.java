package edu.sabanciuniv.nanuvcell.service;

import edu.sabanciuniv.nanuvcell.dto.CreateInvoiceRequest;
import edu.sabanciuniv.nanuvcell.dto.InvoiceDto;
import edu.sabanciuniv.nanuvcell.model.Invoice;
import edu.sabanciuniv.nanuvcell.model.Tariff;
import edu.sabanciuniv.nanuvcell.model.User;
import edu.sabanciuniv.nanuvcell.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository repository;
    private final UserService userService;
    private final TariffService tariffService;


    public InvoiceService(InvoiceRepository repository, UserService userService, TariffService tariffService) {
        this.repository = repository;
        this.userService = userService;
        this.tariffService = tariffService;
    }

    public List<InvoiceDto> getInvoicesByUserId(Long id) {
        return repository.findAll()
                .stream()
                .filter(i -> {
                    if (i.getUser().getId() == id) {
                        return true;
                    }
                    return false;
                })
                .map(InvoiceDto::convert)
                .collect(Collectors.toList());
    }

    public void createInvoice(CreateInvoiceRequest request) {
        User user = userService.findUserByGivenId(request.userId());
        Tariff tariff = tariffService.findTariffById(request.tariffId());

        Invoice invoice = Invoice.builder()
                .tariff(tariff)
                .user(user)
                .build();

        repository.save(invoice);
    }
}
