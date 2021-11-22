package academy.kovalevskyi.codingbootcamp.week2.day0;

import academy.kovalevskyi.codingbootcamp.week0.day4.Numbers1;
import academy.kovalevskyi.codingbootcamp.week1.day0.NumberUtils;

public class Numbers2 extends Numbers1 {
  public static char[][] generateTriplets() {
    return null;
  }

  public static char[][] generateTuples() {
    return null;
  }

  public static char[][] generateTuples(int amount) {
    int size =(int) NumberUtils.power(10, amount - 1) ;
    char[][] tuples = new char[size][amount];
    for (int i = 0; i < tuples.length; i++) {
      tuples[i][0] = (char)(i + 48);
      for (int j = 0; j < 10; j++) {
        tuples[i][j] =(char) (j + 48);
        System.out.println(tuples);
      }
    }
    return tuples;
  }
}
