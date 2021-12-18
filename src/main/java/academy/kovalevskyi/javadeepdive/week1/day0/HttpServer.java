package academy.kovalevskyi.javadeepdive.week1.day0;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class HttpServer implements Runnable {

  public ServerSocket serverSocket;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Print stop for close connections");
    var httpServer = new HttpServer();
    new Thread(httpServer).start();

    for (; ; ) {
      if ("stop".equals(scanner.nextLine())) {
        httpServer.stop();
        break;
      } else {
        System.out.println("Wrong input. To stop - type stop");
      }
    }
  }

  public HttpServer() {
    try {
      serverSocket = new ServerSocket(8080);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (isLive()) {
      try {
        new HttpRequestsHandler(serverSocket.accept()).executeRequest();
      } catch (IOException e) {
        System.out.println("Server was turned off / Connection was closed");
      }
    }
  }

  public void stop() {
    try {
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public  boolean isLive() {
    return !serverSocket.isClosed();
  }
}
