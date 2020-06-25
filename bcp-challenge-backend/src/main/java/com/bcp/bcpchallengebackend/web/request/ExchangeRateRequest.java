package com.bcp.bcpchallengebackend.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Exchange rate request")
public class ExchangeRateRequest {

  @ApiModelProperty(required = true, value = "Source currency code.", example = "PEN")
  private String homeCurrency;

  @ApiModelProperty(required = true, value = "Target currency code.", example = "USD")
  private String targetCurrency;

  @ApiModelProperty(value = "Input amount to exchange.", example = "33")
  private BigDecimal amount;

}
