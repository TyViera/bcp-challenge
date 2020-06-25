package com.bcp.bcpchallengebackend.service;

import com.bcp.bcpchallengebackend.error.ChallengeException;
import com.bcp.bcpchallengebackend.service.repository.ExchangeRateRepository;
import com.bcp.bcpchallengebackend.web.request.ExchangeRateRequest;
import com.bcp.bcpchallengebackend.web.response.ExchangeRateResponse;
import com.bcp.bcpchallengebackend.web.service.ExchangeRateService;
import io.reactivex.Single;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

  private final ExchangeRateRepository exchangeRateRepository;

  @Override
  public Single<ExchangeRateResponse> applyExchangeRate(ExchangeRateRequest request) {
    return exchangeRateRepository
        .getActiveRate(request.getHomeCurrency(), request.getTargetCurrency())
        .map(actualRate -> ExchangeRateResponse.builder()
            .amount(request.getAmount())
            .exchangedAmount(request.getAmount().multiply(actualRate))
            .homeCurrency(request.getHomeCurrency())
            .rate(actualRate)
            .targetCurrency(request.getTargetCurrency())
            .build())
        .flatMapSingle(Single::just)
        .onErrorResumeNext(this::nextError);
  }

  private <T> Single<T> nextError(Throwable err) {
    return Single.defer(() -> {
      Throwable outError = err;
      if (err instanceof NoSuchElementException) {
        outError = ChallengeException.createNotFound(err);
      }
      return Single.error(outError);
    });
  }

}
