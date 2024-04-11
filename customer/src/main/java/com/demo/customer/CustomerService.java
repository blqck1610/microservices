package com.demo.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    CustomerRepository customerRepository;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        Customer customer = Customer.builder()
                .name(customerRegistrationRequest.name())
                .email(customerRegistrationRequest.email())
                .build();
        customerRepository.save(customer);
    }


}
