package academy.kovalevskyi.javadeepdive.week1.day0;

import academy.kovalevskyi.javadeepdive.week0.day0.StdBufferedReader;
import academy.kovalevskyi.javadeepdive.week1.day0.HttpRequestsHandler.Request.Builder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class HttpRequestsHandler {

  public Socket socket;
  private OutputStream out;
  private StdBufferedReader in;
  private Request request;

  public HttpRequestsHandler(Socket socket) {
    this.socket = socket;
  }

  public void executeRequest() {
    try {
      this.out = socket.getOutputStream();
      parseAndPrintRequest();
      if (request.path.equals("/")) {
        String responseBody = "<b>It works!</b>\r\n\r\n";
        out.write(("HTTP/1.1 200 OK\r\n").getBytes());
        out.write(("Content-Type: text/html\r\n").getBytes());
        out.write(("Content-Length: " + responseBody.length() + "\r\n\r\n").getBytes());
        out.write(responseBody.getBytes());
        out.flush();
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        socket.close();
        out.close();
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void parseAndPrintRequest() throws IOException {
    this.in = new StdBufferedReader(new InputStreamReader(socket.getInputStream()));
    var list = in.lines().toArray(String[]::new);
    int i = 0;
    var host = "";
    while ((list[++i]).contains("Host: ")) {
      host = list[i];
    }
    var str = list[0].split(" ");
    request = new Builder()
        .method(str[0])
        .path(str[1])
        .protocol(str[2])
        .host(host.replace("Host: ", ""))
        .build();
    System.out.printf("""
            Method: %s
            Path: %s
            Protocol: %s
            Host: %s
            __________________________________________

            """,
        request.method, request.path, request.protocol, request.host);
  }

  public record Request(String method, String path, String protocol, String host) {

    public static class Builder {

      String method;
      String path;
      String protocol;
      String host;

      public Builder method(String method) {
        this.method = method;
        return this;
      }

      public Builder path(String path) {
        this.path = path;
        return this;
      }

      public Builder protocol(String protocol) {
        this.protocol = protocol;
        return this;
      }

      public Builder host(String host) {
        this.host = host;
        return this;
      }

      public Request build() {
        return new Request(method, path, protocol, host);
      }
    }
  }
}

