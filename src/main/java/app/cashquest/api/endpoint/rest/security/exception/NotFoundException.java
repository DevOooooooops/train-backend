package app.cashquest.api.endpoint.rest.security.exception;

public class NotFoundException extends ApiException {
  public NotFoundException(String message) {
    super(ExceptionType.CLIENT_EXCEPTION, message);
  }
}
