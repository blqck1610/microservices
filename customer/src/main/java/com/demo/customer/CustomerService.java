package com.demo.customer;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;
    public void registerCustomer(@NotNull CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder().name(customerRegistrationRequest.name()).email(customerRegistrationRequest.email()).build();

        // todo: check existing
        // todo: check if fraudster
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerId}", FraudCheckResponse.class, customer.getId());

        // todo: send notification
        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraud()) {
            throw new IllegalStateException("Fraudster");
        }
    }


}
