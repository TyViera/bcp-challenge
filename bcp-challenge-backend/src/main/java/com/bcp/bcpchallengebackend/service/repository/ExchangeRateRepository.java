package com.bcp.bcpchallengebackend.service.repository;

import io.reactivex.Maybe;
import java.math.BigDecimal;

public interface ExchangeRateRepository {

  Maybe<BigDecimal> getActiveRate(String homeCurrency, String targetCurrency);

}
