package org.sid.billingservice.entities;

import lombok.*;
import org.sid.billingservice.model.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date billdate;
    private Long customerId;
    @OneToMany(mappedBy = "bill")
    private List<ProductItem>productsItems;
    @Transient
    private Customer customer;
}
