package com.SpringWebFlux.SpringWebFlux.controller;

import com.SpringWebFlux.SpringWebFlux.dto.Customer;
import com.SpringWebFlux.SpringWebFlux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/getCustomer")
    public List<Customer> getAllCustomers(){
        return service.loadAllCustomer();

    }

    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersStream() {
        return service.loadAllCustomersStream();
    }



}
