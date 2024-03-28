package com.demo.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = Customer.builder()
                .name(customerRegistrationRequest.name())
                .email(customerRegistrationRequest.email())
                .build();
    }


}
