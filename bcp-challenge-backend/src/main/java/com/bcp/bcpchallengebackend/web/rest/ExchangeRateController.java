package com.bcp.bcpchallengebackend.web.rest;

import com.bcp.bcpchallengebackend.web.request.ExchangeRateRequest;
import com.bcp.bcpchallengebackend.web.response.ExchangeRateResponse;
import com.bcp.bcpchallengebackend.web.response.ResponseClient;
import com.bcp.bcpchallengebackend.web.service.ExchangeRateService;
import io.reactivex.Single;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${application.rest.exchange-rate}")
@Api(value = "Challenge controller for exchange rate operations", tags = "Exchange rate controller")
public class ExchangeRateController {

  private final ExchangeRateService exchangeRateService;

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
      MediaType.APPLICATION_STREAM_JSON_VALUE})
  @ApiOperation(
      value = "Returns the exchange rate applying the database configuration.",
      response = ExchangeRateResponse.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successful operation"),
      @ApiResponse(code = 404, message = "Exchange rate not found", response = ResponseClient.class),
      @ApiResponse(code = 500, message = "An error occurred", response = ResponseClient.class)
  })
  public Single<ExchangeRateResponse> exchangeRate(ExchangeRateRequest request) {
    log.info("Input request: {}", request);
    return exchangeRateService.applyExchangeRate(request);
  }

}
