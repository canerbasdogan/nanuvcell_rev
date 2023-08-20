package edu.sabanciuniv.nanuvcell.repository;

import edu.sabanciuniv.nanuvcell.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Modifying
    @Query("DELETE FROM Invoice i WHERE i.id = :id")
    void deleteByInvoiceId(Long id);
}
