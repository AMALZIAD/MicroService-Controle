package org.sid.billingservice.web;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.services.BillService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class BillRestController {
    private BillService billService;

    public BillRestController(BillService billService ) {
        this.billService = billService;
    }
    /*@GetMapping("/fullbill/{id}")
    public Bill bill(@PathVariable Long id){
        Bill bill=billRepository.findById(id).get();
        Customer customer=customerRestClient.customerById(bill.getCustomerId());
        System.out.println(customer);
        bill.setCustomer(customer);
        //product
        bill.getProductsItems().forEach(pi -> {
            Product product=productRestClient.productById(pi.getProductId());
            pi.setProduct(product);
        });
        return bill;
    }*/

    @GetMapping("/")
    @CrossOrigin("*")
    public List<Bill> bills(){
        return billService.getBills();
    }

    @GetMapping("/fullbill/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Bill bill(@PathVariable Long id){
        Bill bill=billService.getBill(id);
        return bill;
    }
    @GetMapping("/byCustomerId/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    List<Bill> findByCustomerId(@PathVariable Long id){
        return billService.getCustomerBills(id);
    }



}
