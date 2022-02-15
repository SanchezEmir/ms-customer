package com.nttdata.customer.controller.service;

import com.nttdata.customer.controller.dto.TypeCustomer;
import com.nttdata.customer.controller.entity.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {
  
  public Flux<Customer> findAll();

  public Mono<Customer> findById(String id);

  public Mono<Customer> create(Customer customer);

  public Mono<Customer> update(Customer customer);

  public Mono<Boolean> delete(String id);

  public Mono<Customer> findByDocumentNumber(String documentNumber);
  
  public Mono<TypeCustomer> findTypeCustomer(String id);
  
}
