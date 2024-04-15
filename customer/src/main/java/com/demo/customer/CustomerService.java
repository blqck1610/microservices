package com.demo.customer;

import com.clients.fraud.FraudCheckResponse;
import com.clients.fraud.FraudClient;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public void registerCustomer(@NotNull CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder().name(customerRegistrationRequest.name()).email(customerRegistrationRequest.email()).build();

        // todo: check existing
        // todo: check if fraudster
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraud(customer.getId());

        // todo: send notification
        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraud()) {
            throw new IllegalStateException("Fraudster");
        }
    }


}
