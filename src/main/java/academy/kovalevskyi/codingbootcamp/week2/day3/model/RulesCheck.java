package academy.kovalevskyi.codingbootcamp.week2.day3.model;

public class RulesCheck {
  public static int[][] toCheckArray  = new int[][]{};

  public static boolean checkWin(FieldPoints field, int flag) {
    boolean h = checkHorizontal(field, flag);
    boolean v = checkVertical(field, flag);
    boolean d = checkDiagonal(field, flag);
    return h || v || d;
  }

  public static boolean checkHorizontal(FieldPoints field, int flag) {
    toCheckArray = field.intTable;
    for (int i = 0; i < toCheckArray.length; i++) {
      int checkCount = 0;
      for (int j = 0; j < toCheckArray[i].length - 1; j++) {
        if (flag == toCheckArray[i][j] && flag == toCheckArray[i][j + 1]) {
          checkCount++;
          if (checkCount == 2) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static boolean checkVertical(FieldPoints field, int flag) {
    toCheckArray = field.intTable;
    int checkCount = 0;
    for (int i = 0; i < toCheckArray.length; i++) {
      for (int j = 0; j < toCheckArray[j].length - 1; j++) {
        if (flag == toCheckArray[j][i] && flag == toCheckArray[j + 1][i]) {
          checkCount++;
          if (checkCount == 2) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static boolean checkDiagonal(FieldPoints field, int flag) {
    int[][] toCheckArray = field.intTable;
    int checkCount = 0;
    for (int i = 0; i < toCheckArray.length - 1; i++) {
      if (flag == toCheckArray[i][i] && flag == toCheckArray[i + 1][i + 1]) {
        checkCount++;
        if (checkCount == 2) {
          return true;
        }
      }
    }
    checkCount = 0;
    for (int i = 0; i < toCheckArray.length - 1; i++) {
      if (flag == toCheckArray[toCheckArray.length - 1 - i][i]
              && flag == toCheckArray[toCheckArray.length - 1 - i - 1][i + 1]) {
        checkCount++;
        if (checkCount == 2) {
          return true;
        }
      }
    }
    return false;
  }

  public static boolean checkDraw(FieldPoints field) {
    toCheckArray = field.intTable;
    for (int i = 0; i < toCheckArray.length; i++) {
      for (int j = 0; j < toCheckArray[i].length; j++) {
        if (toCheckArray[i][j] == 0) {
          return false;
        }
      }
    }
    return true;
  }
}
