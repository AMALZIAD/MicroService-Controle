package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository ){

        return args -> {
            //restConfiguration.exposeIdsFor(Customer.class);,
            //                            RepositoryRestConfiguration restConfiguration
            customerRepository.save(new Customer(null,"mouad","med@gmail.com"));
            customerRepository.save(new Customer(null,"salima","salima@gmail.com"));
            customerRepository.save(new Customer(null,"hind","hind@gmail.com"));
            customerRepository.findAll().forEach(customer -> {
                System.out.println(customer.toString());
            });

        };
    }
}
