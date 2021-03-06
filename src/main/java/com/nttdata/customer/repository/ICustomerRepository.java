package com.nttdata.customer.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.customer.entity.Customer;

import reactor.core.publisher.Flux;

public interface ICustomerRepository extends ReactiveMongoRepository<Customer, String>{
  
  Flux<Customer> findByDocumentNumber(String documentNumber);

}
