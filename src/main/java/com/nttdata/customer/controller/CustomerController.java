package com.nttdata.customer.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.customer.entity.Customer;
import com.nttdata.customer.service.ICustomerService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api(tags = "Customer API")
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/customer")
public class CustomerController {
  
  
  @Autowired
  ICustomerService service;
  
  @GetMapping("/list")
  public Flux<Customer> list(){
      return service.findAll();
  }

  @GetMapping("/find/{id}")
  public Mono<Customer> findById(@PathVariable String id){
      return service.findById(id);
  }

  @GetMapping("/documentNumber/{number}")
  public Mono<Customer> findNumber(@PathVariable String number){
      return service.findByDocumentNumber(number);
  }

  @PostMapping("/create")
  public Mono<ResponseEntity<Customer>> create(@RequestBody Customer customer){
      return service.findTypeCustomer(customer.getTypeCustomer().getId())
              .flatMap(typeCustomer -> {
                          log.info("Enviado a service");
                          customer.setCreatedAt(new Date());
                          customer.setTypeCustomer(typeCustomer);
                          return service.create(customer)
                                  .map(savedCustomer -> new ResponseEntity<>(savedCustomer , HttpStatus.CREATED));
                      }
              ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
  }

  @PutMapping("/update")
  public Mono<ResponseEntity<Customer>> update(@RequestBody Customer customer) {
      return service.findTypeCustomer(customer.getTypeCustomer().getId())
              .flatMap(typeCustomer -> {
                  log.info("updatr customer");
                  return service.update(customer)
                          .map(savedCustomer -> new ResponseEntity<>(savedCustomer, HttpStatus.CREATED));
              })
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/delete/{id}")
  public Mono<ResponseEntity<String>> delete(@PathVariable String id) {
      log.info("eliminando customer");
      return service.delete(id)
              .filter(deleteCustomer -> deleteCustomer)
              .map(deleteCustomer -> new ResponseEntity<>("Customer Deleted", HttpStatus.ACCEPTED))
              .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  
 

}
