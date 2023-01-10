package org.sid.inventoryservice;

import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repositoeries.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    //@Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
        restConfiguration.exposeIdsFor(Product.class);
        return args -> {
            productRepository.save(new Product(null,"DELL",1000.23,10.00));
            productRepository.save(new Product(null,"ASUS",2000.23,20.00));
            productRepository.save(new Product(null,"ACER",3000.23,30.00));
            productRepository.save(new Product(null,"HP",4000.23,40.00));
            productRepository.findAll().forEach(product -> {
                System.out.println(product.toString());
            });
        };
    }
}
