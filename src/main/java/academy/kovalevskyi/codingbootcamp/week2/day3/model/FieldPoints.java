package academy.kovalevskyi.codingbootcamp.week2.day3.model;

import academy.kovalevskyi.codingbootcamp.week2.day3.view.TableView;

public class FieldPoints {

  public int[][] intTable = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

  public int getElementInIntTable(int x, int y) {
    return intTable[y][x];
  }

  public void putPoint(int x, int y, char sign) {
    if (getElementInIntTable(x, y) == 0) {
      intTable[y][x] = switch (sign) {
        case TableView.X_SIGN -> 1;
        case TableView.O_SIGN -> -1;
        default -> throw new IllegalArgumentException();
      };
    }
  }

  public int signToIntFlag(char sign) {
    return switch (sign) {
      case TableView.X_SIGN -> 1;
      case TableView.O_SIGN -> -1;
      default -> throw new IllegalArgumentException();
    };
  }

  public boolean ifFieldBusy(int x, int y) {
    return (intTable[y][x] == -1 || intTable[y][x] == 1);
  } 
}
