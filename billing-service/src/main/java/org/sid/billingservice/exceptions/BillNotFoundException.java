package org.sid.billingservice.exceptions;

public class BillNotFoundException extends RuntimeException{
    public  BillNotFoundException(String message){
        super(message);
    }
}
