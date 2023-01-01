package org.sid.billingservice.services;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;

import java.util.List;

public interface BillService {
    ProductItem saveProductItem(ProductItem productItem);
    Bill saveBill(Bill bill);
    Bill getBill(Long id);
    List<ProductItem> getProductsItems(Long id);
    List<Bill> getBills();
    List<Bill> getCustomerBills(Long customerId);
}
