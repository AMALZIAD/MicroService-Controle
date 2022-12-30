package org.sid.customerservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(types=Customer.class,name ="fullcustomer")
public interface CustomerProjection {
    public Long getId();
    public String getName();
    public String getEmail();
}
