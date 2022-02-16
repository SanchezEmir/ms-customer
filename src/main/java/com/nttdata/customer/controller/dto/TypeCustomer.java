package com.nttdata.customer.controller.dto;

import com.nttdata.customer.controller.entity.enums.ETypeCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeCustomer {
  
  private String id;
  private ETypeCustomer value;
  private SubType subType;

}
