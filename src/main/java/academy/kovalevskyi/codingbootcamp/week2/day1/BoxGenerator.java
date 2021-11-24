package academy.kovalevskyi.codingbootcamp.week2.day1;

public class BoxGenerator {
    
  public static void main(String... args) {
    if (args.length != 4) { 
      System.out.println("####################################################");
      System.out.println("Please provide 4 input arguments, current amount: " + args.length);
      System.out.println("####################################################");
      return;
    }
    DrawBox box = new DrawBox(args[0], args[1], args[2], args[3]);
    box.drawBox(); 
  }
}
