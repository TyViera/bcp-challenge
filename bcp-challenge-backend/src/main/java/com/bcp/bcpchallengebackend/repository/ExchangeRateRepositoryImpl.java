package com.bcp.bcpchallengebackend.repository;

import com.bcp.bcpchallengebackend.repository.framework.ExchangeRateFramework;
import com.bcp.bcpchallengebackend.service.repository.ExchangeRateRepository;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

  private final ExchangeRateFramework exchangeRateFramework;

  @Override
  public Maybe<BigDecimal> getActiveRate(String homeCurrency, String targetCurrency) {
    return Maybe.defer(() -> {
      var rate = this.exchangeRateFramework
          .findExchangeRateByCurrencies(homeCurrency, targetCurrency);
      return rate.map(Maybe::just).orElse(Maybe.empty());
    }).subscribeOn(Schedulers.io());
  }

}
