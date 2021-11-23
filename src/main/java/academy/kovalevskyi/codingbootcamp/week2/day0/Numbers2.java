package academy.kovalevskyi.codingbootcamp.week2.day0;

import academy.kovalevskyi.codingbootcamp.week0.day4.Numbers1;
import academy.kovalevskyi.codingbootcamp.week1.day0.NumberUtils;

public class Numbers2 extends Numbers1 {
  public static char[][] generateTriplets() {
    // n элементы  Cmn = n! / (n-m)!* m!
    int n = 10;
    int m = 3;
//    int combCount = (int) NumberUtils.getFactorial(n) / (NumberUtils.getFactorial(n - m) * NumberUtils.getFactorial(m)); 
    return null;
  }

  public static char[][] generateTuples() {
    char[][] charArr = new char[10000][5];
    fillByZero(charArr);
    int count = 0;
    for (int i = 0; i < charArr.length; i++) {
      charArr[i] = fillRowsByDigitsWithSpace(charArr[i], count);
      count++;
    }
    return charArr;
  }

  public static char[][] generateTuples(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException();
    }    
    if (amount == 0) {
      return new char[][]{};
    }
    int size = (int) NumberUtils.power(10, amount) ;
    char[][] charArr = new char[size][amount];
    fillByZero(charArr);
    int count = 0;
    for (int i = 0; i < charArr.length; i++) {
      charArr[i] = fillRowsByDigits(charArr[i], count);
      count++;
    }
    return charArr;
  }

  public static char[] fillRowsByDigitsWithSpace(char[] row, int digit) {
    char[] digitChar = convertToCharArray(digit);
    System.arraycopy(digitChar, 0, row, row.length - digitChar.length,digitChar.length );
    row[0] = row[1];
    row[1] = row[2];
    row[2] = ' ';
    return row;
  }

    
  public static void fillByZero(char[][] tuple) {
    for (int i = 0; i < tuple.length; i++) {
      for (int j = 0; j < tuple[i].length; j++) {
        tuple[i][j] = '0';
      }
    }
  }

  public static char[] fillRowsByDigits(char[] row, int digit) {
    char[] digitChar = Numbers1.convertToCharArray(digit);
    System.arraycopy(digitChar, 0, row, row.length - digitChar.length,digitChar.length );
    return row;
  }
}
