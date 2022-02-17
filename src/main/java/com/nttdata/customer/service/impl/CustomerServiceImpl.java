package com.nttdata.customer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.customer.dto.TypeCustomer;
import com.nttdata.customer.entity.Customer;
import com.nttdata.customer.repository.ICustomerRepository;
import com.nttdata.customer.service.ICustomerService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService {

  private final WebClient webClient;
  private final ReactiveCircuitBreaker reactiveCircuitBreaker;
  
  @Value("${config.base.typecustomer}")
  private String urlTypecustomer;
  // private final String urlTypecustomer =
  // "http://api-gateway:8090/api/profile/typecustomer/find/{id} ";

  public CustomerServiceImpl(
      ReactiveResilience4JCircuitBreakerFactory circuitBreakerFactory) {
    this.webClient = WebClient.builder().baseUrl(this.urlTypecustomer).build();
    this.reactiveCircuitBreaker = circuitBreakerFactory.create("typeCustomer");
  }

  @Autowired
  private ICustomerRepository repo;

  @Override
  public Flux<Customer> findAll() {
    return repo.findAll();
  }

  @Override
  public Mono<Customer> findById(String id) {
    return repo.findById(id);
  }

  @Override
  public Mono<Customer> create(Customer customer) {
    // return dao.save(customer);
    return repo.findByDocumentNumber(customer.getDocumentNumber()).collectList()
        .flatMap(c -> {
          if (c.isEmpty()) {
            log.info("customer recibido");
            customer.setNames(customer.getNames().toUpperCase());
            customer.setGender(customer.getGender().toUpperCase());
            customer.setAddress(customer.getAddress().toUpperCase());
            return repo.save(customer);
          } else {
            return Mono.just(new Customer());
          }
        });
  }

  @Override
  public Mono<Customer> update(Customer customer) {
    return repo.findById(customer.getId()).flatMap(c -> {
      return repo.save(customer);
    });
  }

  @Override
  public Mono<Boolean> delete(String id) {
    return repo.findById(id)
        .flatMap(dc -> repo.delete(dc).then(Mono.just(Boolean.TRUE)))
        .defaultIfEmpty(Boolean.FALSE);
  }

  @Override
  public Mono<Customer> findByDocumentNumber(String documentNumber) {
    return repo.findByDocumentNumber(documentNumber).next();
  }

  @Override
  public Mono<TypeCustomer> findTypeCustomer(String id) {
    log.info("Buscando typeCustomer...");
    return reactiveCircuitBreaker.run(webClient.get().uri(this.urlTypecustomer, id)
        .accept(MediaType.APPLICATION_JSON)
        .retrieve().bodyToMono(TypeCustomer.class),
        throwable -> {
          return this.getDefaultTypeCustomer();
        });
  }
  
  public Mono<TypeCustomer> getDefaultTypeCustomer() {
    log.info("la peticion no respondio");
    return Mono.just(new TypeCustomer("0", null, null));
  }

}
