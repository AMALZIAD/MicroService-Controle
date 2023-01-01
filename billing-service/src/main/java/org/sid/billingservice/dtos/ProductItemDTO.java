package org.sid.billingservice.dtos;


import lombok.Data;

@Data
public class ProductItemDTO {
    private Long id;
    private Long productId;
    private int quantity;
    private double price;
    private double discount;
    private double  amount;
}
