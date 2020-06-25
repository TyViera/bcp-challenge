Feature: The app respond with the value applying the exchange rate

  Background:
    Given The following active exchange rates:
      | homeCurrency | targetCurrency | rate   |
      | USD          | USD            | 1      |
      | USD          | PEN            | 0.28   |
      | PEN          | USD            | 3.5    |
      | PEN          | PEN            | 1      |
      | USD          | GBP            | 0.8044 |
      | GBP          | USD            | 1.2432 |

  @skip #Use this tag if you want to skip the execution of this scenario/feature
  Scenario: User make a call and there is no exchange rate, then the app responds error not found
    When user consumes for the exchange rate api with 90 as amount, "GBP" as home currency, "PEN" as target currency
    Then the app responds with 404 http code

  Scenario Outline: User make a call and the app responds successfully
    When user consumes for the exchange rate api with <amount> as amount, "<home_currency>" as home currency, "<target_currency>" as target currency
    Then the app responds with 200 http code
    And the user receives the following values as exchange rate:
      | amount          | <amount>           |
      | exchangedAmount | <exchanged_amount> |
      | homeCurrency    | <home_currency>    |
      | targetCurrency  | <target_currency>  |
      | rate            | <rate>             |
    Examples:
      | amount | home_currency | target_currency | rate   | exchanged_amount |
      #| 1       | PEN           | USD             | 3.5    | 3.5              |
      #| 10      | PEN           | USD             | 3.5    | 35               |
      #| 1       | USD           | PEN             | 0.28   | 0.28             |
      #| 11623   | USD           | PEN             | 0.28   | 3254.44          |
      #| 8198123 | USD           | USD             | 1      | 8198123          |
      | 33.333 | GBP           | USD             | 1.2432 | 41.4396          |
