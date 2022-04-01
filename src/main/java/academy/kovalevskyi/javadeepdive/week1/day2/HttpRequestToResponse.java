package academy.kovalevskyi.javadeepdive.week1.day2;

import academy.kovalevskyi.javadeepdive.week0.day0.StdBufferedReader;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpRequest.Builder;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpResponse.ResponseStatus;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class HttpRequestToResponse {

  public Socket socket;
  OutputStream out;
  StdBufferedReader in;
  public CopyOnWriteArrayList<HttpRequestsHandler> list = new CopyOnWriteArrayList<>();
  private HttpRequest request;
  public HttpResponse httpResponse;

  public HttpRequestToResponse(Socket socket, CopyOnWriteArrayList<HttpRequestsHandler> list) {
    this.socket = socket;
    this.list.add(error404);
    if (list != null) {
      this.list.addAll(list);
    }
  }

  public void executeRequest() {
    parseRequest();
    searchHandler();
    try {
      out.write(httpResponse.toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void parseRequest() {
    try {
      this.out = socket.getOutputStream();
      this.in = new StdBufferedReader(new InputStreamReader(socket.getInputStream()));
      var firstHeaders = new String(in.readLine()).split(" ");
      ArrayList<String> list = new ArrayList<>();
      while (in.hasNext()) {
        list.add(new String(in.readLine()));
      }
      var body = "";
      var contLenStr = list.get(0).split(":");
      if (contLenStr[0].equals("Content-Length")) {
        for (String l : list) {
          if (l.length() == Integer.parseInt(contLenStr[1].strip())) {
            body = l;
            System.out.println("IN BODY PARSING" + l);
          }
        }
      }
      request = new Builder()
          .method(HttpMethod.valueOf(firstHeaders[0]))
          .body(body)
          .path(firstHeaders[1])
          .build();
      System.out.println("PATH: " + request.path() + " METHOD: " + request.httpMethod() + "\nBODY "
          + request.body());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void searchHandler() {
    for (HttpRequestsHandler item : this.list) {
      if (item.path().equals(request.path()) && item.method().equals(request.httpMethod())) {
        System.out.println(item.path() + " " + request.path());
        httpResponse = item.process(request);
        break;
      } else {
        httpResponse = error404.process(request);
      }
    }
  }

  HttpRequestsHandler error404 = new HttpRequestsHandler() {
    @Override
    public String path() {
      return "/404";
    }

    @Override
    public HttpMethod method() {
      return HttpMethod.GET;
    }

    @Override
    public HttpResponse process(HttpRequest request) {
      String htmlBody = "<h1>ERROR 404 </h1><h2>" + request.path() + " endPoint not found</h2>";

      return new HttpResponse.Builder().status(ResponseStatus.ERROR_404).body(htmlBody).build();
    }
  };
}

