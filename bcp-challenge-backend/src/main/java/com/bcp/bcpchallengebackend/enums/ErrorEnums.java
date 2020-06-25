package com.bcp.bcpchallengebackend.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorEnums {

  NOT_FOUND("E001", "The exchange rate values was not found!", HttpStatus.NOT_FOUND),
  UNKNOWN("E999", "Unknown error.", HttpStatus.INTERNAL_SERVER_ERROR);

  private final String code;
  private final String message;
  private final HttpStatus httpCode;

}
