package academy.kovalevskyi.codingbootcamp.week2.day0;
import academy.kovalevskyi.codingbootcamp.week1.day2.StringUtils;

public class Calculator {

  public static void main(String[] args) throws NumberFormatException {
    if(args.length != 3) {
      System.out.println("Please provide 3 input arguments, example: 2 + 3");
    }
      long a = StringUtils.toInt(args[0].toCharArray());
      long b = StringUtils.toInt(args[2].toCharArray());
    

    switch(args[1]) {
      case "/":
        if (b == 0) {
          System.out.println("Division by zero is impossible!");
          break;
        }
    System.out.println("result: " + (double) a/b);
        break;
      case "+":
        System.out.println("result: " +  (a + b));
        break;
      case "-":
        System.out.println("result: " + (a - b));
        break;
      case "*":
        System.out.println("result: " +  (a * b));
        break;
      case "%":
        if (b == 0) {
          System.out.println("Division by zero is impossible!");
          break;
        }
        System.out.println("result: " +  (a % b));
        break;
    }
  } 
}
