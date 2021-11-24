package academy.kovalevskyi.codingbootcamp.week2.day1;

public class BoxGenerator {
    
  public static void main(String... args) {
    if (args.length !=4) { 
      System.out.println("####################################################");
      System.out.println("Please provide 4 input arguments, current amount: " + args.length);
      System.out.println("####################################################");
      return;
    }
    DrawBox box = new DrawBox(args[0], args[1], args[2], args[3]);
    box.drawBox(); 
    
 /*   Integer width = Integer.parseInt(args[0]);
    Integer height = Integer.parseInt(args[1]);
    char corner = args[3].charAt(0);
    char symb = args[2].charAt(0);
    
    if((width < 0 || height < 0) || (args[2].length() > 1 || args[3].length() > 1)) {
      throw new IllegalArgumentException();
    }
    if (width == 0 || height == 0) {
      return;    
    } 

    drawFloor(corner,symb, width);
    if (height > 2) {
      for (int i = 1; i<= height -2; i++) {
        drawSpace(symb, width);
      }
    }
    if (height >1) {
      drawFloor(corner, symb, width);
    }
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
            System.out.print(wall);
        }
        if (pos == 1) {
            System.out.print(wall + "\n");
        }
    }
    public static void drawFloor(char corner, char floor, int width) {
      if (width == 1) {
        drawCorner(corner, -1);
        System.out.print("\n");
        return;
      } else if (width == 2) {
        drawCorner(corner, -1);
        drawCorner(corner, 1);
        return;
      }
      drawCorner(corner, -1);
      for (int i = 1; i <= width - 2; i++) {
        System.out.print(floor);
      }
      drawCorner(corner, 1);
    }

  public static void drawSpace(char wall, int width) {
    if (width == 1) {
      drawWall(wall, -1);
      System.out.print("\n");
      return;
    }
    if (width == 2) {
      drawWall(wall, -1);
      drawWall(wall, 1);
      return;
    }
    drawWall(wall, -1);
    for (int i = 1; i <= width - 2; i++) {      
      System.out.print(" ");
    }
    drawWall(wall, 1);
  }
*/
}

}
