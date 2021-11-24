package academy.kovalevskyi.codingbootcamp.week2.day1;

import academy.kovalevskyi.codingbootcamp.week1.day2.StringUtils;

public class TextPrinter {
    
  public static void main(String... args) {
    if (args.length != 1) { 
      String s = "Please provide only one input argument, current amount: " + args.length;
      System.out.print(StringUtils.makeCamel(s.toCharArray()));
      return;
    }
    DrawBox box = new DrawBox(args[0]);
    box.drawTextBox(); 
  }
}
