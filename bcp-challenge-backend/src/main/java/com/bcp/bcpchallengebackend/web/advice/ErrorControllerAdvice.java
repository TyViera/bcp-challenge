package com.bcp.bcpchallengebackend.web.advice;

import com.bcp.bcpchallengebackend.enums.ErrorEnums;
import com.bcp.bcpchallengebackend.error.ChallengeException;
import com.bcp.bcpchallengebackend.web.response.ResponseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Component
@RestControllerAdvice(annotations = RestController.class)
public class ErrorControllerAdvice {

  @ExceptionHandler(ChallengeException.class)
  public ResponseEntity<ResponseClient> handleChallengeException(
      ChallengeException challengeException) {
    log.debug("handle challenge exception", challengeException);
    return new ResponseEntity<>(getResponseFromError(challengeException.getError()),
        challengeException.getError().getHttpCode());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseClient> handleException(Exception exception) {
    log.debug("handle unexpected error", exception);
    return new ResponseEntity<>(getResponseFromError(ErrorEnums.UNKNOWN),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseClient getResponseFromError(ErrorEnums error) {
    return new ResponseClient(error.getCode(), error.getMessage());
  }

}
