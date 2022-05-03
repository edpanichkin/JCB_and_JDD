package academy.kovalevskyi.algorithms.week0.day0;

import java.util.Arrays;

public class Tasks {

  public static boolean sameCharactersSorting(String left, String right) {
    if (left.length() != right.length()) {
      return false;
    }
    String[] leftArray = left.split("");
    String[] rightArray = right.split("");
    Sort sort = new Sort() {
    };
    sort.sort(leftArray);
    sort.sort(rightArray);

    return Arrays.equals(leftArray, rightArray);

  }

  public static boolean sameCharactersO1(String left, String right) {
    if (left.length() != right.length()) {
      return false;
    }
//    int leftInt = 0;
//    int rightInt = 0;
//    for (int i = 0; i < left.length(); i++) {
//      leftInt += left.charAt(i) * left.charAt(i);
//      rightInt += right.charAt(i) * right.charAt(i);
//    }
    int[] leftInt = new int[255];
    int[] rightInt = new int[255];

    for (int i = 0; i < left.length(); i++) {
      ++leftInt[left.charAt(i)];
      ++rightInt[right.charAt(i)];
    }
    for (int i = 0; i < leftInt.length; i++) {
      if (leftInt[i] != rightInt[i]) {
        return false;
      }
    }
//    return leftInt == rightInt;
    return true;
  }
}



