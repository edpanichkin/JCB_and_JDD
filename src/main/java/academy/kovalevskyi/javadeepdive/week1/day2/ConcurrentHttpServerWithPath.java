package academy.kovalevskyi.javadeepdive.week1.day2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHttpServerWithPath extends Thread {

  public boolean live = true;
  public ServerSocket serverSocket;
  public ExecutorService executorService = Executors.newFixedThreadPool(10);
  public CopyOnWriteArrayList<HttpRequestsHandler> handlers = new CopyOnWriteArrayList<>();


  public ConcurrentHttpServerWithPath() {
    try {
      this.serverSocket = new ServerSocket(8080);
    } catch (IOException e) {
      //e.printStackTrace();
    }
  }

  public void addHandler(HttpRequestsHandler handler) {
    handlers.add(handler);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Print stop for close connections");
    var httpServer = new ConcurrentHttpServerWithPath();
    new Thread(httpServer).start();
    for (; ; ) {
      if ("stop".equals(scanner.nextLine())) {
        httpServer.stopServer();
        scanner.close();
        break;
      } else {
        System.out.println("Wrong input. To stop - type stop");
      }
    }
  }

  public void stopServer() {
    live = false;

    try {
      serverSocket.close();
      executorService.shutdown();
    } catch (IOException e) {
      //e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (isLive()) {
      try {
        Socket socket = serverSocket.accept();
        executorService.execute(() -> new HttpRequestToResponse(socket, handlers).executeRequest());

      } catch (SocketException ignored) {
        System.out.println("ALL CONNECTIONS CLOSED");
      } catch (IOException e) {
        // e.printStackTrace();
      }
    }
  }

  public boolean isLive() {
    return live;
    //   return !serverSocket.isClosed();
  }
}
