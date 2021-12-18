package academy.kovalevskyi.javadeepdive.week1.day2;

import academy.kovalevskyi.javadeepdive.week0.day0.StdBufferedReader;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpRequest.Builder;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpResponse.ResponseStatus;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class HttpRequestToResponse {

  Socket socket;
  OutputStream out;
  StdBufferedReader in;
  private final List<HttpRequestsHandler> list;
  private HttpRequest request;
  HttpResponse httpResponse;

  public HttpRequestToResponse(Socket socket, List<HttpRequestsHandler> list) {
    this.socket = socket;
    this.list = list;
    this.list.add(error404);
    this.list.add(helloHandler);
  }

  HttpRequestsHandler helloHandler = new HttpRequestsHandler() {
    final String htmlBody = "<h1>hi</h1>";

    @Override
    public String path() {
      return "/hello";
    }

    @Override
    public HttpMethod method() {
      return HttpMethod.GET;
    }

    @Override
    public HttpResponse process(HttpRequest request) {
      return new HttpResponse.Builder().status(ResponseStatus.OK_200).body(htmlBody).build();
    }
  };
  HttpRequestsHandler rootHandler = new HttpRequestsHandler() {


    @Override
    public String path() {
      return "/";
    }

    @Override
    public HttpMethod method() {
      return HttpMethod.GET;
    }

    @Override
    public HttpResponse process(HttpRequest request) {
      final String htmlBody = "";
      return new HttpResponse.Builder().status(ResponseStatus.OK_200).body(htmlBody).build();
    }
  };
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
      String htmlBody =
          "<h1>ERROR 404 </h1><h2>" + request.path() + " endPoint not found</h2>";

      return new HttpResponse.Builder()
          .status(ResponseStatus.ERROR_404)
          .body(htmlBody).build();
    }
  };


  public void parseRequest() {
    try {
      this.out = socket.getOutputStream();
      this.in = new StdBufferedReader(new InputStreamReader(socket.getInputStream()));
      request = new Builder()
          .path(new String(in.readLine()).split(" ")[1]).build();
      System.out.println(request.path());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void executeRequest() {
    parseRequest();
    for (HttpRequestsHandler item : this.list) {
      if (item.path().equals(request.path())) {
        System.out.println(item.path() + " " + request.path());
        httpResponse = item.process(request);
        break;
      } else {
        httpResponse = error404.process(request);
      }
    }
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
}

