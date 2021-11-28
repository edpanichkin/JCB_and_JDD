package academy.kovalevskyi.codingbootcamp.week2.day3.view;

public class TableView {
  public static final char X_SIGN = 'x';
  public static final char O_SIGN = 'o';

  char[][] table = {{'-', '>', ' ', '0', ' ', ' ', ' ', '1', ' ', ' ', ' ', '2', ' '},
                    {'0', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '}, // 1,1  0,5  0,9
                    {' ', ' ', ' ', '-', ' ', '+', ' ', '-', ' ', '+', ' ', '-', ' '}, //
                    {'1', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '}, // 3,1  2,5  2,9
                    {' ', ' ', ' ', '-', ' ', '+', ' ', '-', ' ', '+', ' ', '-', ' '}, //
                    {'2', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '}};// 5,1  4,5  4,9

  public TableView() {
  }

  public void drawTableArray() {
    for (char[] chars : table) {
      for (int j = 0; j < chars.length; j++) {
        System.out.print(chars[j]);
      }
      System.out.println();
    }
  }

  public void putSymbol(int x, int y, char sign) {
    int puX = switch (x) {
      case 0 -> 3;
      case 1 -> 7;
      case 2 -> 11;
      default -> throw new IllegalArgumentException();
    };
    int puY = switch (y) {
      case 0 -> 1;
      case 1 -> 3;
      case 2 -> 5;
      default -> throw new IllegalArgumentException();
    };
    table[puY][puX] = sign;
  }

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
