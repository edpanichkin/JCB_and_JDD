package academy.kovalevskyi.javadeepdive.week1.day2;

public enum ContentType {
  TEXT_HTML("text/html"),
  APPLICATION_CSV("application/csv"),
  APPLICATION_JSON("application/json");

  public final String contentTypeValue;

  ContentType(String contentTypeValue) {
    this.contentTypeValue = contentTypeValue;
  }

  @Override
  public String toString() {
    return "Content-Type: " + this.contentTypeValue + "\r\n";
  }
}
