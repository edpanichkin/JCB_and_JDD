package academy.kovalevskyi.javadeepdive.week0.day2;

public class InvalidCsvLineLengthException extends RuntimeException {
  public InvalidCsvLineLengthException() {
  }

  public InvalidCsvLineLengthException(String message) {
    super(message);
  }

  public InvalidCsvLineLengthException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidCsvLineLengthException(Throwable cause) {
    super(cause);
  }
}
