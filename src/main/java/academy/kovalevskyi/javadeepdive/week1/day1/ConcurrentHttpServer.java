package academy.kovalevskyi.javadeepdive.week1.day1;

import academy.kovalevskyi.javadeepdive.week1.day0.HttpRequestsHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHttpServer implements Runnable {

  public ServerSocket serverSocket;
  public ExecutorService executorService = Executors.newFixedThreadPool(10);

  public ConcurrentHttpServer() throws IOException {
    serverSocket = new ServerSocket(8080);
  }

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Print stop for close connections");
    var httpServer = new ConcurrentHttpServer();
    new Thread(httpServer).start();
    for (; ; ) {
      if ("stop".equals(scanner.nextLine())) {
        httpServer.stop();
        scanner.close();
        break;
      } else {
        System.out.println("Wrong input. To stop - type stop");
      }
    }
  }

  public void stop() {
    try {
      executorService.shutdownNow();
      serverSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (isLive()) {
      try {
        Socket socket = serverSocket.accept();
        executorService.submit(() -> new HttpRequestsHandler(socket).executeRequest());
      } catch (SocketException ignored) {
        System.out.println("ALL CONNECTIONS CLOSED");
      } catch (IOException e) {
        System.out.println("ALL CLOSED");
      }
    }
  }

  public boolean isLive() {
    return !serverSocket.isClosed();
  }
}
