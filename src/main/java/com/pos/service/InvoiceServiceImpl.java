package com.pos.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pos.dto.InvoiceDTO;
import com.pos.dto.StockDTO;
import com.pos.entity.Invoice;
import com.pos.entity.Product;
import com.pos.entity.Stock;
import com.pos.repository.InvoiceRepository;
import com.pos.repository.ProductRepository;
import com.pos.repository.StockRepository;

@Transactional
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private StockService stockService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    StockRepository stockRepository;

    @Override
    public Optional<Invoice> getInvoice(long invoiceId) {
        return invoiceRepository.findById(invoiceId);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(InvoiceDTO invoiceDTO) {

        Invoice invoice = new Invoice();

        Product product = productRepository.findById(invoiceDTO.getProductId()).get();
        Stock stock = stockRepository.findById(invoiceDTO.getProductId()).get();
        Long productId = product.getId();
        invoice.setAccountNumber(invoiceDTO.getAccountNumber());
        invoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
        invoice.setUniquePrice(stock.getSalePrice());

        if (stockRepository.totalQuantityInStock(productId) < invoiceDTO.getInvoiceQuantiy()) {
            throw new IllegalArgumentException("Quantity not enough in stock. Enter less amount.");

        } else {
            invoice.setInvoiceQuantiy(invoiceDTO.getInvoiceQuantiy());

            StockDTO stockDTO = new StockDTO();

            Long bg1, bg2;
            bg1 = invoiceDTO.getInvoiceQuantiy();
            bg2 = -Math.abs(bg1);

            stockDTO.setStockEntryDate(stock.getStockEntryDate());
            stockDTO.setStockExpireDate(stock.getStockExpireDate());
            stockDTO.setQuantiy(bg2);
            stockDTO.setProductId(productId);
            stockDTO.setPurchasePrice(stock.getPurchasePrice());
            stockDTO.setSalePrice(stock.getSalePrice());

            stockService.createStock(stockDTO);
        }

        BigDecimal salePrice = stock.getSalePrice();
        BigDecimal invoiceQuantiy = new BigDecimal(invoiceDTO.getInvoiceQuantiy());
        BigDecimal totalPrice = BigDecimal.ZERO;
        totalPrice = totalPrice.add(salePrice.multiply(invoiceQuantiy));

        invoice.setTotalPrice(totalPrice);
        invoice.setStock(stock);

        invoiceRepository.save(invoice);

        return invoice;
    }

    @Override
    public void updateInvoice(Invoice invoice) {

        Invoice invoiceById = getInvoice(invoice.getId()).get();
        
        invoiceById.setAccountNumber(invoice.getAccountNumber());
        invoiceById.setInvoiceDate(invoice.getInvoiceDate());
        invoiceById.setUniquePrice(invoice.getUniquePrice());
        invoiceById.setInvoiceQuantiy(invoice.getInvoiceQuantiy());

        BigDecimal salePrice = invoice.getUniquePrice();
        BigDecimal invoiceQuantiy = new BigDecimal(invoice.getInvoiceQuantiy());
        BigDecimal totalPrice = BigDecimal.ZERO;
        totalPrice = totalPrice.add(salePrice.multiply(invoiceQuantiy));

        invoiceById.setTotalPrice(totalPrice);

        invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(long invoiceId) {
        invoiceRepository.deleteById(invoiceId);
    }
}
