package com.bcp.bcpchallengebackend.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MoneySerializer extends JsonSerializer<BigDecimal> {

  @Value("${application.config.money-size}")
  private Integer moneySize;

  @Override
  public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider provider)
      throws IOException {
    jsonGenerator.writeNumber(value.setScale(this.moneySize, RoundingMode.HALF_UP).toString());
  }

}