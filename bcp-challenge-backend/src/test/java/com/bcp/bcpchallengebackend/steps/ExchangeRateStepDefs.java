package com.bcp.bcpchallengebackend.steps;

import com.bcp.bcpchallengebackend.framework.entity.ExchangeRateEntity;
import com.bcp.bcpchallengebackend.util.TestUtils;
import com.bcp.bcpchallengebackend.web.request.ExchangeRateRequest;
import com.bcp.bcpchallengebackend.web.response.ExchangeRateResponse;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;

@Slf4j
public class ExchangeRateStepDefs {

  static ValidatableResponse validatableResponse;

  @PersistenceContext
  private EntityManager entityManager;

  @LocalServerPort
  private int port;

  @Value("${application.rest.exchange-rate}")
  private String exchangeRateUri;

  @Before
  public void setUpFeature() {
    log.info("adding login filters...");
    RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    RestAssured.port = port;
  }

  @Transactional
  @Given("The following active exchange rates:")
  public void theFollowingActiveExchangeRates(List<Map<String, Object>> rates) {
    rates.forEach(rateConfig -> {
      var entity = TestUtils.parseMapToObject(rateConfig, ExchangeRateEntity.class);
      log.info("saving... {}", entity);
      this.entityManager.persist(entity);
    });
  }

  @When("user consumes for the exchange rate api with {double} as amount, {string} as home currency, {string} as target currency")
  public void userConsumesForTheExchangeRateApiWithAsAmountAsHomeCurrencyAsTargetCurrency(
      double amount, String homeCurrency, String targetCurrency) {

    var exchangeRateRequest = ExchangeRateRequest.builder()
        .amount(new BigDecimal(amount))
        .homeCurrency(homeCurrency)
        .targetCurrency(targetCurrency)
        .build();

    validatableResponse = RestAssured
        .given()
        .queryParams(TestUtils.parseObjectToMap(exchangeRateRequest))
        .when()
        .get(exchangeRateUri)
        .then();
  }

  @Then("the app responds with {int} http code")
  public void theAppRespondsWithNoErrors(int statusCode) {
    validatableResponse.assertThat().statusCode(statusCode);
  }

  @And("the user receives the following values as exchange rate:")
  public void theUserReceivesTheFollowingValuesAsExchangeRate(Map<String, Object> stepRate) {
    var expectedRatesResponse = TestUtils.parseMapToObject(stepRate, ExchangeRateResponse.class);
    var responseFromService = validatableResponse.extract().response()
        .as(ExchangeRateResponse.class);

    MatcherAssert.assertThat(responseFromService, Matchers.notNullValue());
    MatcherAssert.assertThat(responseFromService.getHomeCurrency(), Matchers.notNullValue());
    MatcherAssert.assertThat(responseFromService.getTargetCurrency(), Matchers.notNullValue());
    MatcherAssert.assertThat(responseFromService.getRate(), Matchers.notNullValue());
    MatcherAssert.assertThat(responseFromService.getAmount(), Matchers.notNullValue());
    MatcherAssert.assertThat(responseFromService.getExchangedAmount(), Matchers.notNullValue());

    MatcherAssert
        .assertThat("Home currency is not the expected!", expectedRatesResponse.getHomeCurrency(),
            Matchers.comparesEqualTo(responseFromService.getHomeCurrency()));

    MatcherAssert.assertThat("Target currency is not the expected!",
        expectedRatesResponse.getTargetCurrency(),
        Matchers.comparesEqualTo(responseFromService.getTargetCurrency()));

    MatcherAssert.assertThat("Rate is not the expected!", expectedRatesResponse.getRate(),
        Matchers.comparesEqualTo(responseFromService.getRate()));

    MatcherAssert.assertThat("Amount is not the expected!", expectedRatesResponse.getAmount(),
        Matchers.comparesEqualTo(responseFromService.getAmount()));

    MatcherAssert.assertThat("Exchanged amount is not the expected!",
        expectedRatesResponse.getExchangedAmount(),
        Matchers.comparesEqualTo(responseFromService.getExchangedAmount()));


  }

}
