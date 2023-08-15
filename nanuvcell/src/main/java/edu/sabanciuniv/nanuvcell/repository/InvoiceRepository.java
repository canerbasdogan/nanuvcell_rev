package edu.sabanciuniv.nanuvcell.repository;

import edu.sabanciuniv.nanuvcell.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
