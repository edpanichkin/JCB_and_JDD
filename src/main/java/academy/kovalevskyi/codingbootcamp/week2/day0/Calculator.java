package academy.kovalevskyi.codingbootcamp.week2.day0;

public class Calculator {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Please provide 3 input arguments, example: 2 + 3");
      return;
    }
    Long a = Long.parseLong(args[0]);
    Long b = Long.parseLong(args[2]);
    String op = args[1];
    if (b == 0 && ((op == "%") || (op == "/"))) { 
      System.out.println("Division by zero is impossible!");
      return;
    }
    Number res = switch (op) {
      case "/"  ->  (double) a / b;
      case "+" -> (a + b);
      case "-" -> (a - b);
      case "*" -> a * b;
      case "%" -> a % b;
      default -> throw new IllegalArgumentException();
    };
    System.out.println("result: " + res);
  }
}
