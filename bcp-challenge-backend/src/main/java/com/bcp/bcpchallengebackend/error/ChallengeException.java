package com.bcp.bcpchallengebackend.error;

import com.bcp.bcpchallengebackend.enums.ErrorEnums;
import io.reactivex.Single;
import lombok.Getter;

@Getter
public class ChallengeException extends RuntimeException {

  private static final long serialVersionUID = 808308342277631087L;

  private final ErrorEnums error;

  public ChallengeException(ErrorEnums error) {
    super(error.getMessage());
    this.error = error;
  }

  public ChallengeException(ErrorEnums error, Throwable throwable) {
    super(error.getMessage(), throwable);
    this.error = error;
  }

  public <T> Single<T> toSingle() {
    return Single.error(this);
  }

  public static ChallengeException createNotFound() {
    return new ChallengeException(ErrorEnums.NOT_FOUND);
  }

  public static ChallengeException createNotFound(Throwable parent) {
    return new ChallengeException(ErrorEnums.NOT_FOUND, parent);
  }

}
