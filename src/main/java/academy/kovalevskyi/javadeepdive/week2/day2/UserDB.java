package academy.kovalevskyi.javadeepdive.week2.day2;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import academy.kovalevskyi.javadeepdive.week0.day3.InsertRequest;
import academy.kovalevskyi.javadeepdive.week0.day3.RequestException;
import academy.kovalevskyi.javadeepdive.week0.day3.SelectRequest;
import academy.kovalevskyi.javadeepdive.week0.day3.Selector;
import java.util.Arrays;
import java.util.Optional;

public class UserDB {

  public Csv csv;

  public UserDB(Csv csv) {
    System.out.println("CONSTRUCTOR");
    this.csv = csv;
  }

  public synchronized void addUser(User user) throws RequestException {
    System.out.println("ADD USER");
    this.csv = new InsertRequest.Builder()
        .insert(user.toStringArray())
        .to(this.csv).build()
        .execute();
    printCsv(this.csv);
    //System.out.println(csv.values().length);
  }

  public String[] getUsersMails() throws RequestException {
    System.out.println(csv.values().length);
    //printCsv(csv);
    String[][] answer = (new SelectRequest.Builder())
        .select(new String[]{"mail"})
        .from(this.csv)
        .build()
        .execute();
    String[] usersMails = new String[answer.length];
    for (int i = 0; i < answer.length; i++) {
      for (int j = 0; j < answer[i].length; j++) {
        usersMails[i] = answer[i][j];
      }
    }
    System.out.println("IN GETUSERSMAILS"  + usersMails.length);
    //printCsv(csv);
    return usersMails;
  }

  public Optional<User> getUser(String mail) throws RequestException {
    System.out.println("IN GET USER");
    var result = new SelectRequest.Builder()
        .from(this.csv).select(new String[]{
            "mail", "firstName", "lastName", "password"})
        .where((new Selector.Builder()).fieldName("mail").value(mail)
        .build())
        .build()
        .execute();
    User user = (new User.Builder())
        .mail(result[0][0])
        .firstName(result[0][1])
        .lastName(result[0][2])
        .password(result[0][3]).build();
    System.out.println("USER IN ARRAY" + Arrays.toString(user.toStringArray()));
    System.out.println("RUN GETUSERMAILS");
    System.out.println(Arrays.toString(getUsersMails()));
    return Optional.of(user);
  }

  public void printCsv(Csv csv) {
    System.out.println(Arrays.toString(csv.headers()));
    for (int i = 0; i < csv.values().length; i++) {
      System.out.print(Arrays.toString(csv.values()[i]) + " ");
    }
    //System.out.println();
  }
}
