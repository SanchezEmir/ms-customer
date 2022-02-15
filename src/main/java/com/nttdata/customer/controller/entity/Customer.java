package com.nttdata.customer.controller.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.nttdata.customer.controller.dto.TypeCustomer;
import com.nttdata.customer.controller.entity.enums.EDocumentType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
public class Customer {

  @Id
  private String id;
  
  //ruc/dni
  private EDocumentType documentType;
  
  //ruc o dni
  private String documentNumber;
  
  //nombres o razon social
  private String names;
  
  //masculino / feminino
  private String gender;
  
  //telefono
  private String numberphone;
  
  private String address;
  
  private TypeCustomer typeCustomer;
  
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date createdAt;
  
}
