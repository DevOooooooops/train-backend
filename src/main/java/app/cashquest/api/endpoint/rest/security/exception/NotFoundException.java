package app.cashquest.api.endpoint.rest.security.exception;

import static app.cashquest.api.endpoint.rest.security.exception.ApiException.ExceptionType.CLIENT_EXCEPTION;

public class NotFoundException extends ApiException {
  public NotFoundException(String message) {
    super(CLIENT_EXCEPTION, message);
  }
}
