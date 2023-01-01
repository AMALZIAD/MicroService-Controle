package org.sid.billingservice.dtos;

import org.sid.billingservice.enums.BillStatus;

import java.util.Date;

public class BillDTO {
    private Long id ;
    private Date billdate;
    private BillStatus status;
    private Long customerId;
    private double total;
}
