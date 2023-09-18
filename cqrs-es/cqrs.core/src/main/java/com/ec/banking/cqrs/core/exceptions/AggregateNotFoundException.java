package com.ec.banking.cqrs.core.exceptions;

/**
 * @author edisoncsi on 14/9/23
 * @project banking-account
 */
public class AggregateNotFoundException extends RuntimeException{
    public  AggregateNotFoundException(String message){
        super(message);
    }
}
