package app.cashquest.api.endpoint.rest.security.exception;

import static app.cashquest.api.endpoint.rest.security.exception.ApiException.ExceptionType.CLIENT_EXCEPTION;

public class BadRequestException extends ApiException {
  public BadRequestException(String message) {
    super(CLIENT_EXCEPTION, message);
  }
}
