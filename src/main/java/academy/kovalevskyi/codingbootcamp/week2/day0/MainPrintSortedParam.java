package academy.kovalevskyi.codingbootcamp.week2.day0;

public class MainPrintSortedParam {

  public static void main(String... args) {
    if (args.length == 0) {
      System.out.println("Please specify at least one argument!");  
    }
    String temp;
      for (int i = 0; i < args.length; i++) {
        for (int j = i + 1; j < args.length; j++) {
          if (args[i].compareTo(args[j]) > 0) {
            temp = args[i];
            args[i] = args[j];
            args[j] = temp;
          }
        }
      }
    for (int i = 0; i < args.length; i++) {    
      System.out.println(args[i]);  
    }
  }
}
