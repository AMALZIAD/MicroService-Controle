package org.sid.billingservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.billingservice.dtos.ProductItemDTO;
import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.enums.BillStatus;
import org.sid.billingservice.exceptions.BillNotFoundException;
import org.sid.billingservice.exceptions.CustomerNotFoundException;
import org.sid.billingservice.exceptions.ProductNotFoundException;
import org.sid.billingservice.mappers.BillServiceMapper;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BillServiceImpl implements BillService {
   private BillRepository billRepository;
   private ProductItemRepository productItemRepository;
   private CustomerRestClient customerRestClient;
   private BillServiceMapper mapperDTO;

    @Override
    public ProductItem saveProductItem(ProductItem productItem) {
        return productItemRepository.save(productItem);
    }

    @Override
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill getBill(Long id) {
        Bill bill=billRepository.findById(id).orElse(null);
        if (bill==null) throw new BillNotFoundException("Bill not Found");
        Customer customer=customerRestClient.customerById(bill.getCustomerId());
        bill.setCustomer(customer);
        //product
        bill.setProductsItems(this.getProductsItems(bill.getId()));
        return bill;
    }

    @Override
    public List<ProductItem> getProductsItems(Long id) {
        return billRepository.findById(id).get().getProductsItems();
    }
    @Override
    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    @Override
    public List<Bill> getCustomerBills(Long customerId) {
        Customer customer=customerRestClient.customerById(customerId);
        if(customer==null) throw new CustomerNotFoundException("Customer Not Found");
        return billRepository.findByCustomerId(customerId);
    }
}
