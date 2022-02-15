package com.nttdata.customer.controller.dto;

import com.nttdata.customer.controller.entity.enums.ETypeCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeCustomer {
  
  private String id;
  private ETypeCustomer value;
  private SubType subType;

}
