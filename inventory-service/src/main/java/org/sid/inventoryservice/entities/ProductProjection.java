package org.sid.inventoryservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = Product.class, name="fullproduct")
public interface ProductProjection {
    public Long getId();
    public String getName();
    public double getPrice();
    public double getQuantity();
}
