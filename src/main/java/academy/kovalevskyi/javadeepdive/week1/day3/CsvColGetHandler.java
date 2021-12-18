package academy.kovalevskyi.javadeepdive.week1.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import academy.kovalevskyi.javadeepdive.week0.day3.RequestException;
import academy.kovalevskyi.javadeepdive.week0.day3.SelectRequest;
import academy.kovalevskyi.javadeepdive.week1.day2.ContentType;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpMethod;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpRequest;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpRequestsHandler;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpResponse;

public class CsvColGetHandler implements HttpRequestsHandler {

  String selectBy;
  Csv csv;
  String path;


  public CsvColGetHandler(Csv csv, String selectBy, String path) {
    this.selectBy = selectBy;
    this.csv = csv;
    this.path = path;
  }

  @Override
  public String path() {
    return "/names";
  }

  @Override
  public HttpMethod method() {
    return HttpMethod.GET;
  }

  @Override
  public HttpResponse process(HttpRequest request) {
    StringBuilder jsonResult = new StringBuilder();
    try {
      var result = new SelectRequest.Builder()
          .from(csv).select(new String[]{selectBy})
          .build()
          .execute();
      jsonResult.append("[");
      for (String[] strings : result) {
        for (String string : strings) {
          jsonResult.append("\"")
              .append(string)
              .append("\"")
              .append(",");
        }
      }
      jsonResult
          .deleteCharAt(jsonResult.length() - 1)
          .append("]");
    } catch (RequestException e) {
      e.printStackTrace();
    }
    System.out.println(jsonResult);

    return new HttpResponse.Builder()
        .contentType(ContentType.APPLICATION_JSON)
        .body(jsonResult.toString())
        .build();
  }
}
