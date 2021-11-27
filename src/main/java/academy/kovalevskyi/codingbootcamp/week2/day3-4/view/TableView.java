package academy.kovalevskyi.codingbootcamp.week2.day3-4.view;

public class TableView {
  public final static char X_SIGN = 'x';
  public final static char O_SIGN = 'o';

  char[][] table = {{'-', '>', ' ', '0', ' ', ' ', ' ', '1', ' ', ' ', ' ', '2', ' '},
                    {'0', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '}, // 1,1  0,5  0,9
                    {' ', ' ', ' ', '-', ' ', '+', ' ', '-', ' ', '+', ' ', '-', ' '}, //
                    {'1', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '}, // 3,1  2,5  2,9
                    {' ', ' ', ' ', '-', ' ', '+', ' ', '-', ' ', '+', ' ', '-', ' '}, //
                    {'2', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' '}};// 5,1  4,5  4,9

  public TableView() {
  }

   public void drawTableArray() {
    //clearScreen();
    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[i].length; j++) {
        System.out.print(table[i][j]);
      }
      System.out.println();
    }
     //System.out.println("\n");
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

  public void clearScreen() {
    //System.out.print("\r");
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
