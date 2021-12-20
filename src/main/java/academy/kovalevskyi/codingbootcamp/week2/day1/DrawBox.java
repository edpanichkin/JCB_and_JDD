package academy.kovalevskyi.codingbootcamp.week2.day1;

public class DrawBox {
  
  public static int width;
  public static int height;
  public static char corner;
  public static char symb;
  public static String text;

  public DrawBox(String width, String height, String symb, String corner) {
    
    DrawBox.width = Integer.parseInt(width);
    DrawBox.height = Integer.parseInt(height);
    DrawBox.corner = corner.charAt(0);
    DrawBox.symb = symb.charAt(0);
    text = spaceText(DrawBox.width);

    if ((DrawBox.width < 0 || DrawBox.height < 0) || (corner.length() > 1 || symb.length() > 1)) {
      throw new IllegalArgumentException();
    }
  }

  public DrawBox(String text) {
    
    width = text.length() + 4;
    height = 3;
    corner = '/';
    symb = '#';
    DrawBox.text = text;
  }
  
  public DrawBox(String symb, String text) {
    
    width = text.length() + 4;
    height = 3;
    corner = symb.charAt(0);
    DrawBox.symb = symb.charAt(0);
    DrawBox.text = text;
  }
  
  public static void drawBox() {
    if (width == 0 || height == 0) {
      return;    
    } 
    drawFloor(corner, symb, width);
    if (height > 2) {
      for (int i = 1; i <= height - 2; i++) {
        drawSpace(symb, width);
      }
    }
    if (height > 1) {
      drawFloor(corner, symb, width);
    }
  }

  public static void drawTextBox() {
    drawUpTextFloor(corner, symb, width);
    System.out.print(symb + " " + text + " " + symb + "\n");
    drawDownTextFloor(corner, symb, width);
  }

  public static void drawUpTextFloor(char corner, char floor, int width) {
    drawCorner((char) 47, -1);
    for (int i = 1; i <= width - 2; i++) {
      System.out.print(floor);
    }
    drawCorner((char) 92, 1);
  }

  public static void drawDownTextFloor(char corner, char floor, int width) {
    drawCorner((char) 92, -1);
    for (int i = 1; i <= width - 2; i++) {
      System.out.print(floor);
    }
    drawCorner((char) 47, 1);
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
    System.out.print(" " + text + " ");
    drawWall(wall, 1);
  }

  public static String spaceText(int width) {
    String txt = "";   
    for (int i = 1; i <= width - 4; i++) {
      txt += " ";
    }
    return txt;
  }
}
