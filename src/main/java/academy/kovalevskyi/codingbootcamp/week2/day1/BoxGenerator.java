package academy.kovalevskyi.codingbootcamp.week2.day1;

public class BoxGenerator {
    
  public static void main(String... args) {
    if (args.length !=4) { 
      System.out.println("####################################################");
      System.out.println("Please provide 4 input arguments, current amount: " + args.length);
      System.out.println("####################################################");
      return;
    }


Integer width = Integer.parseInt(args[0]);
    Integer height = Integer.parseInt(args[1]);
    char corner = args[3].charAt(0);
    char symb = args[2].charAt(0);

    drawCorner(corner, -1);
    drawFloor(symb, width);
    drawCorner(corner, 1);
    for (int i = 1; i<= height -2; i++) {
      drawWall(symb, -1);
      drawSpace(width);
      drawWall(symb, 1);
    }
    drawCorner(corner, -1);
    drawFloor(symb, width);
    drawCorner(corner, 1);
  
  }

    public static void drawCorner(char corner, int pos) {
        if (pos == -1) {
            System.out.print(corner);
        }
        if (pos == 1) {
            System.out.print(corner + "\n");
        }
    }

    public static void drawWall(char wall, int pos) {
        if (pos == -1) {
            System.out.print(wall + " ");
        }
        if (pos == 1) {
            System.out.print(" " + wall + "\n");
        }
    }
    public static void drawFloor(char floor, int width) {
        for (int i = 1; i <= width - 2; i++) {
            System.out.print(floor);
        }
    }

  public static void drawSpace(int width) {
    for (int i = 1; i <= width - 4; i++) {      
      System.out.print(" ");
    }
  }
}


