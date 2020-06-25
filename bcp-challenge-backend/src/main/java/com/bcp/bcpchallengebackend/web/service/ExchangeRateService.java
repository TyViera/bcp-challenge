package com.bcp.bcpchallengebackend.web.service;

import com.bcp.bcpchallengebackend.web.request.ExchangeRateRequest;
import com.bcp.bcpchallengebackend.web.response.ExchangeRateResponse;
import io.reactivex.Single;

public interface ExchangeRateService {

  Single<ExchangeRateResponse> applyExchangeRate(ExchangeRateRequest request);

}
