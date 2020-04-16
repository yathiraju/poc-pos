package com.pos.service;

import com.pos.dto.InvoiceDTO;
import com.pos.entity.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<Invoice> getAllInvoices();

    Optional<Invoice> getInvoice(long invoiceId);

    Invoice createInvoice(InvoiceDTO invoiceDTO);

    void updateInvoice(Invoice invoice);

    void deleteInvoice(long invoiceId);
}