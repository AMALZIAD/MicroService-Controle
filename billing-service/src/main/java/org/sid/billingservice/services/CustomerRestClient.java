package org.sid.billingservice.services;

import org.sid.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping(value = "/customers/{id}?projection=fullcustomer",headers = { })
    public Customer customerById(@PathVariable Long id);
    @GetMapping("/customers?projection=fullcustomer")
    public PagedModel<Customer> allCustomers();

}
