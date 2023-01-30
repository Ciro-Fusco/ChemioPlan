package com.example.FrontEnd.FrontEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FarmacoDosaggio {
  private String codiceFarmaco;
  private Double dosaggio;
}
