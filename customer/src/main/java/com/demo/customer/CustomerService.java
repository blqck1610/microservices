package com.demo.customer;

import com.clients.fraud.FraudCheckResponse;
import com.clients.fraud.FraudClient;
import com.clients.notification.NotificationClient;
import com.clients.notification.NotificationRequest;
import io.micrometer.observation.annotation.Observed;
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

    private final NotificationClient notificationClient;

    @Observed(name = "user.name",
            contextualName = "customer->service",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public void registerCustomer(@NotNull CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder().name(customerRegistrationRequest.name()).email(customerRegistrationRequest.email()).build();

        // todo: check existing
        // todo: check if fraudster
        customerRepository.saveAndFlush(customer);


        FraudCheckResponse fraudCheckResponse = fraudClient.isFraud(customer.getId());
        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraud()) {
            throw new IllegalStateException("Fraudster");
        }
        // todo: send notification
        notificationClient.sendNotification(NotificationRequest.builder()
                .customerEmail(customer.getEmail())
                .customerId(customer.getId())
                .message(String.format("hi %s, welcome", customer.getName()))
                .build());

    }


}
