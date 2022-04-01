package academy.kovalevskyi.javadeepdive.week2.day1;

import academy.kovalevskyi.javadeepdive.week1.day2.ConcurrentHttpServerWithPath;
import academy.kovalevskyi.javadeepdive.week1.day2.ContentType;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpMethod;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpRequest;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpRequestToResponse;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpRequestsHandler;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpResponse;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpResponse.Builder;
import academy.kovalevskyi.javadeepdive.week1.day2.HttpResponse.ResponseStatus;
import academy.kovalevskyi.javadeepdive.week2.day0.JsonHelper;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

public class RestServer extends ConcurrentHttpServerWithPath {

  String packagePath;
  List<Object> controllers = new ArrayList<>();
  ConcurrentHashMap<Class<?>, Object> classSetConcurrentHashMap = new ConcurrentHashMap<>();

  public RestServer(String packagePath) {
    this.packagePath = packagePath;
  }

  @Override
  public void run() {
    initialize();
    System.out.println("______________________________");
    System.out.println(classSetConcurrentHashMap.toString());
    System.out.println("______________________________");
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

  public void initialize() {

    Set<Class<?>> annotatedControllerClass = new Reflections(packagePath).getTypesAnnotatedWith(
        Controller.class);

    for (Class<?> controllerClass : annotatedControllerClass) {
      Object instance = new Object();
      try {
        instance = controllerClass.getDeclaredConstructor().newInstance();
        controllers.add(instance);
      } catch (Exception e) {
        e.printStackTrace();
      }

      Reflections reflections = new Reflections((controllerClass), new MethodAnnotationsScanner());
      var pathGet = reflections.getMethodsAnnotatedWith(Get.class);
      var pathPost = reflections.getMethodsAnnotatedWith(Post.class);
//
//      classSetConcurrentHashMap.put(Get.class, pathGet);

      try {
        classSetConcurrentHashMap.put(controllerClass,
            controllerClass.getDeclaredConstructor().newInstance());
      } catch (InstantiationException | IllegalAccessException
          | InvocationTargetException | NoSuchMethodException e) {
        e.printStackTrace();
      }

      for (Method method : pathGet) {
        System.out.println(method.getName());
        //Object obj = classSetConcurrentHashMap.get(0);
        var get = method.getDeclaredAnnotation(Path.class).value();
        String body = null;
        try {
          body = JsonHelper.toJsonString(method.invoke(instance));
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        }
        String finalBody = body;
        super.addHandler(new HttpRequestsHandler() {
          @Override
          public String path() {
            return get;
          }

          @Override
          public HttpMethod method() {
            return HttpMethod.GET;
          }

          @Override
          public HttpResponse process(HttpRequest request) {
            return new Builder()
                .body(finalBody)
                .contentType(ContentType.APPLICATION_JSON)
                .status(ResponseStatus.OK_200).build();
          }
        });
      }

      for (Method m : pathPost) {
        var post = m.getDeclaredAnnotation(Path.class).value();
        Object finalInstance = instance;
        super.addHandler(new HttpRequestsHandler() {

          @Override
          public String path() {
            return post;
          }

          @Override
          public HttpMethod method() {
            return HttpMethod.POST;
          }

          @Override
          public HttpResponse process(HttpRequest request) {
            try {
              Object[] args = new Object[m.getParameterCount()];
              Parameter[] params = m.getParameters();
              int i = 0;
              for (Parameter parameter : params) {
                args[i] = JsonHelper.fromJsonString(request.body().orElse(""),
                    parameter.getType());
                i++;
              }
              System.out.println(controllers.toString());
              System.out.println("TRY TO POST");
              m.invoke(controllers.get(0), args);
            } catch (Exception e) {
              e.printStackTrace();
            }

            return new Builder().status(ResponseStatus.OK_200)
                .contentType(ContentType.TEXT_HTML).build();
          }
        });
      }


    }
  }

  public static void main(String[] args) {
    RestServer restServer = new RestServer("academy.kovalevskyi.javadeepdive");
    restServer.start();
  }

}