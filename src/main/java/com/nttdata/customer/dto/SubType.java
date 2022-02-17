package com.nttdata.customer.dto;

import com.nttdata.customer.entity.enums.ESubType;

import lombok.Data;

@Data
public class SubType {
  
  private String id;
  private ESubType value;

}
