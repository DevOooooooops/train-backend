package app.cashquest.api.endpoint.rest.security.exception;

import static app.cashquest.api.endpoint.rest.security.exception.ApiException.ExceptionType.CLIENT_EXCEPTION;

public class ForbiddenException extends ApiException {
  public ForbiddenException(String message) {
    super(CLIENT_EXCEPTION, message);
  }

  public ForbiddenException() {
    super(CLIENT_EXCEPTION, "Access is denied");
  }
}
