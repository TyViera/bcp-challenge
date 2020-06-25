package com.bcp.bcpchallengebackend.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class Rate {

  private String homeCurrency;
  private String targetCurrency;
  private BigDecimal rate;

}
