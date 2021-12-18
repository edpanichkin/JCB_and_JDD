package academy.kovalevskyi.javadeepdive.week1.day2;

public record HttpResponse(ResponseStatus status, ContentType contentType, String
    body, HttpVersion httpVersion) {

  public static final HttpResponse ERROR_404 =
      new Builder().status(ResponseStatus.ERROR_404).build();
  public static final HttpResponse OK_200 =
      new Builder().status(ResponseStatus.OK_200).build();
  public static final HttpResponse ERROR_500 =
      new Builder().status(ResponseStatus.ERROR_500).build();


  public static class Builder {

    private ResponseStatus status = ResponseStatus.OK_200;
    private ContentType contentType = ContentType.TEXT_HTML;
    private String body;
    private HttpVersion version = HttpVersion.HTTP_1_1;

    public Builder status(final ResponseStatus status) {
      this.status = status;
      return this;
    }

    public Builder contentType(final ContentType contentType) {
      this.contentType = contentType;
      return this;
    }

    public Builder body(final String body) {
      this.body = body;
      return this;
    }

    public Builder httpVersion(final HttpVersion version) {
      this.version = version;
      return this;
    }

    public HttpResponse build() {
      return new HttpResponse(this.status, this.contentType, this.body, this.version);
    }
  }

  public enum ResponseStatus {
    OK_200(200, "OK"),
    ERROR_404(404, "NOT FOUND"),
    ERROR_500(500, "SERVER ERROR");

    final int statusCode;
    final String statusMessage;

    ResponseStatus(int statusCode, String statusMessage) {
      this.statusCode = statusCode;
      this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
      return statusCode;
    }

    public String getStatusMessage() {
      return statusMessage;
    }

    @Override
    public String toString() {
      return this.statusCode + " " + this.statusMessage + "\r\n";
    }
  }

  @Override
  public String toString() {

    return httpVersion.toString() + status + contentType
        + "Content-Length: " + (body == null ? 0 : body.length() + 4)
        + "\r\n\r\n" + (body == null ? "" : body)
        + "\r\n\r\n";
  }
}
