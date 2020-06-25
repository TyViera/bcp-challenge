package com.bcp.bcpchallengebackend.framework;

import com.bcp.bcpchallengebackend.framework.entity.ExchangeRateEntity;
import com.bcp.bcpchallengebackend.repository.framework.ExchangeRateFramework;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepositoryJpa extends ExchangeRateFramework,
    CrudRepository<ExchangeRateEntity, Long> {

  @Query("select p.rate from ExchangeRateEntity p where p.homeCurrency = :#{#homeCurrency} and p.targetCurrency = :#{#targetCurrency}")
  Optional<BigDecimal> findExchangeRateByCurrencies(
      @Param("homeCurrency") String homeCurrency,
      @Param("targetCurrency") String targetCurrency);

}
