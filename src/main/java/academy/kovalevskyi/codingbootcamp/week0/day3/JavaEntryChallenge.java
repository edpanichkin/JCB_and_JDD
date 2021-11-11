package academy.kovalevskyi.codingbootcamp.week0.day3;

public class JavaEntryChallenge {
  public static void main(String[] args) {
  }

  public static int sumOfTwoNumbers(int a, int b) {
    return a + b;
  }

  public static int convertMinToSec(int minutes) {
    int secInMin = 60;
    return minutes * secInMin;
  }

  public static void compareTwoNumbers(int a, int b) {
    String compareResult = "Result: numbers are equal";
    if (a != b) {
      compareResult = a < b ? "Result: b is bigger" : "Result: a is bigger";
    }
    System.out.print(compareResult);
  }

  public static void concatTwoStrings(String firstName, String lastName) {
    String concatString = "";
    System.out.print(concatString.concat(firstName).concat(" ").concat(lastName));
  }

  public static int remainder(int a, int b) {
    return a % b;
  }

  public static boolean divisibleBy5(int num) {
    return (num % 5) == 0;
  }

  public static int firstElementOfArray(int[] arr) {
    return arr[0];
  }

  public static int smallestElementOfArray(int[] arr) {
    int min = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < min) {
        min = arr[i];
      }
    }
    return min;
  }

  public static void checkResultOfWork(boolean quick, boolean efficient, boolean reliable) {
    String workResult = "Pick at least two";
    if (quick && reliable && !efficient) {
      workResult = "Result of work: poor, but fast enough";
    } else if (efficient && reliable && !quick) {
      workResult = "Result of work: good, but tired of waiting";
    } else if (quick && efficient && !reliable) {
      workResult = "Result of work: excellent, but pricey";
    } else if (quick && efficient && reliable) {
      workResult = "Result of work: excellent, but you are dreaming";
    }
    System.out.print(workResult);
  }
}
