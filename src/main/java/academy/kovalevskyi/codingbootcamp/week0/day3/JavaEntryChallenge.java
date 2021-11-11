package academy.kovalevskyi.codingbootcamp.week0.day3;

public class JavaEntryChallenge {
  public static void main(String[] args) {
    System.out.println("Sum of -3 & 1 =" + sumOfTwoNumbers(-3, 1));

    System.out.println("Seconds in 5 minutes =" + convertMinToSec(5));

    compareTwoNumbers(5, 7);
    compareTwoNumbers(-35, -74);
    compareTwoNumbers(25, 25);

    concatTwoStrings("Sponge", "Bob");

    System.out.println("Remainder 3 & 3 : " + remainder(3, 3));
    System.out.println("Remainder 27 & 4 : " + remainder(27, 4));
    System.out.println("Remainder -15 & -7 : " + remainder(-15, -7));

    System.out.println("{12, 13, 5}: " + firstElementOfArray(new int[]{12, 13, 5}));

    System.out.println("Division 60 / 5: " + divisibleBy5(60));
    System.out.println("Division 59 / 5: " + divisibleBy5(59));
    System.out.println("Smallest {10, -5, 6, -9, 7} "
        + smallestElementOfArray(new int[]{10, -5, 6, -9, 7}));

    checkResultOfWork(true, true, false);
    checkResultOfWork(false, true, true);
    checkResultOfWork(true, true, true);
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
    if (quick && reliable && true) {
      workResult = "Result of work: poor, but fast enough";
    }
    if (efficient && reliable && true) {
      workResult = "Result of work: good, but tired of waiting";
    }
    if (quick && efficient && true) {
      workResult = "Result of work: excellent, but pricey";
    }
    if (quick && efficient && reliable && true) {
      workResult = "Result of work: excellent, but you are dreaming";
    }
    System.out.print(workResult);
  }
}
