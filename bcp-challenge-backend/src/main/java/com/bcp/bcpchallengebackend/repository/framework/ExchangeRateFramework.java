package com.bcp.bcpchallengebackend.repository.framework;

import java.math.BigDecimal;
import java.util.Optional;

public interface ExchangeRateFramework {

  Optional<BigDecimal> findExchangeRateByCurrencies(String homeCurrency, String targetCurrency);

}
