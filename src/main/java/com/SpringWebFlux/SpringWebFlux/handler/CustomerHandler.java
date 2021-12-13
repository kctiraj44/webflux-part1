package com.SpringWebFlux.SpringWebFlux.handler;

import com.SpringWebFlux.SpringWebFlux.dao.CustomerDao;
import com.SpringWebFlux.SpringWebFlux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        Flux<Customer> customersList = customerDao.getCustomersList();
        return ServerResponse.ok().body(customersList, Customer.class);

    }


    public Mono<ServerResponse> findCustomer(ServerRequest request) {
        Integer customerId = Integer.valueOf(request.pathVariable("input"));
        Mono<Customer> monoCustomer = customerDao.getCustomersList().filter(c -> c.getId() == customerId).next();//take(1).single();
        return ServerResponse.ok().body(monoCustomer, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return  ServerResponse.ok().body(saveResponse,String.class);
    }
}