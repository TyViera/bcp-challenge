package com.bcp.bcpchallengebackend.framework.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ExchangeRateEntity implements Serializable {

  private static final long serialVersionUID = 3443492316959514291L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 3)
  private String homeCurrency;

  @Column(length = 3)
  private String targetCurrency;

  @Column(precision = 12, scale = 5)
  private BigDecimal rate;

}
