package com.nttdata.customer.controller.dto;

import com.nttdata.customer.controller.entity.enums.ESubType;

import lombok.Data;

@Data
public class SubType {
  
  private String id;
  private ESubType value;

}
