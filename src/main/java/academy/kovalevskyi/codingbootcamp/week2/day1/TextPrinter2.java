package academy.kovalevskyi.codingbootcamp.week2.day1;

import academy.kovalevskyi.codingbootcamp.week1.day2.StringUtils;

public class TextPrinter2 {
    
  public static void main(String... args) {
    if (args.length != 2) { 
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.out.println("Please provide 2 input arguments, current amount: " + args.length);
      System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      return;
    }
    DrawBox box = new DrawBox(args[0], args[1]);
    box.drawBox(); 
  }
}
