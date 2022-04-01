package academy.kovalevskyi.javadeepdive.week2.day2;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import academy.kovalevskyi.javadeepdive.week0.day3.RequestException;
import academy.kovalevskyi.javadeepdive.week2.day1.Controller;
import academy.kovalevskyi.javadeepdive.week2.day1.Get;
import academy.kovalevskyi.javadeepdive.week2.day1.Path;
import academy.kovalevskyi.javadeepdive.week2.day1.Post;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class Users {

  public final UserDB db;

  private String[] header() {
    return Arrays.stream(User.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new);
  }

  public Users() {
    System.out.println("IN CONSTRUCTOR DB");
    db = new UserDB(new Csv.Builder().header(this.header())
        .values(new String[0][0]).build());
  }

  @Get
  @Path("/users")
  public String[] users() {
    String[] result = new String[0];
    System.out.println("BEFORE CONTROLLER users()");
    try {
      System.out.println("IN CONTROLLER users() " + db.csv.values().length);
      return db.getUsersMails();
    } catch (RequestException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Get
  @Path("/first")
  public User firstUser() {
    System.out.println("SOMEBODY RUN firstUser");
    String mail;
    Optional<User> user = Optional.empty();
    if(db.csv.values().length > 0) {
      try {
        mail = db.getUsersMails()[0];
        user = db.getUser(mail);
      } catch (RequestException e) {
        e.printStackTrace();
      }
    }
    return user.orElse(null);
  }

  @Post
  @Path("/users")
  public void addUser(User user) {
    System.out.println("WANT TO ADD FROM USERS / POST");
    try {
      db.addUser(user);
      System.out.println("RUN UN ADD GETUSERS");
      System.out.println("IN ADD CONTROLLER users() " + db.csv.values().length);
      db.getUser("viacheslav@kovalevskyi.academy");
    } catch (RequestException e) {
      e.printStackTrace();
    }
  }
}