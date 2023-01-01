package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.enums.BillStatus;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.sid.billingservice.services.BillService;
import org.sid.billingservice.services.BillServiceImpl;
import org.sid.billingservice.services.CustomerRestClient;
import org.sid.billingservice.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(BillingServiceApplication.class, args);
    }
    /*@Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){
        return  args -> {
            List<Customer> customers=customerRestClient.allCustomers().getContent().stream().toList();
            List<Product> products=productRestClient.allProducts().getContent().stream().toList();
            Random random=new Random();
            Long customerId=1L;
            Customer customer=customerRestClient.customerById(customerId);
            for (int i =0;i<20; i++) {
                Bill bill=Bill.builder()
                        .customerId(customers.get(random.nextInt(customers.size())).getId())
                        .status(Math.random()>0.5? BillStatus.PAIED:BillStatus.CREATED)
                        .billdate(new Date())
                        .build();
                Bill savedBill=billRepository.save(bill);
                for (int j =0; j< products.size();j++){
                    if(Math.random()>0.70){
                        ProductItem productItem=ProductItem.builder()
                                .bill(savedBill)
                                .productId(products.get(j).getId())
                                .price(products.get(j).getPrice())
                                .quantity(1+random.nextInt(10))
                                .discount(Math.random())
                                .build();
                        productItemRepository.save(productItem);
                    }

                }
            }

        };
    }*/

    //service style
    @Bean
    CommandLineRunner start(BillService billService,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){
        return  args -> {
            List<Customer> customers=customerRestClient.allCustomers().getContent().stream().toList();
            List<Product> products=productRestClient.allProducts().getContent().stream().toList();
            Random random=new Random();
            Long customerId=1L;
            Customer customer=customerRestClient.customerById(customerId);
            for (int i =0;i<20; i++) {
                Bill bill=Bill.builder()
                        .customerId(customers.get(random.nextInt(customers.size())).getId())
                        .status(Math.random()>0.5? BillStatus.PAIED:BillStatus.CREATED)
                        .billdate(new Date())
                        .build();
                Bill savedBill=billService.saveBill(bill);
                // create products items
                for (int j =0; j< products.size();j++){
                    if(Math.random()>0.70){
                        ProductItem productItem=ProductItem.builder()
                                .bill(bill)
                                .productId(products.get(j).getId())
                                .price(products.get(j).getPrice())
                                .quantity(1+random.nextInt(10))
                                .discount(Math.random())
                                .build();
                        billService.saveProductItem(productItem);
                    }
                }
            }
        };
    }
}
