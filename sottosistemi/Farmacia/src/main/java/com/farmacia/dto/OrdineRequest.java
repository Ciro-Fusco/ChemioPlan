package com.farmacia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdineRequest {
  private String codiceFarmaco;
  private Integer quantita;
}
