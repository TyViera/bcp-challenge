package com.bcp.bcpchallengebackend.web.response;

import com.bcp.bcpchallengebackend.web.serializer.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Exchange rate response")
public class ExchangeRateResponse implements Serializable {

  private static final long serialVersionUID = -3599128148078580433L;

  @ApiModelProperty(value = "Source currency code.", example = "PEN")
  private String homeCurrency;

  @ApiModelProperty(value = "Target currency code.", example = "USD")
  private String targetCurrency;

  @ApiModelProperty(value = "Active exchange rate.", example = "3.20")
  private BigDecimal rate;

  @ApiModelProperty(value = "Input amount to exchange.", example = "33")
  private BigDecimal amount;

  @JsonSerialize(using = MoneySerializer.class)
  @ApiModelProperty(value = "Final amount exchanged.", example = "105.6")
  private BigDecimal exchangedAmount;

}
