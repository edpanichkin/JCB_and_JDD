package academy.kovalevskyi.codingbootcamp.week1.day0;

public class NumberUtils {

  public static long getFactorial(final int number) {
    if (number > 20 || number < 0) {
      throw new IllegalArgumentException();
    }
    if (number == 0) {
      return 1;
    }
    long factorial = 1;
    for (int i = 1; i <= number; i++) {
      factorial *= i; 
    }
    return factorial;
  }

  public static long factorialRecursive(int number) {
    if (number > 20 || number < 0) {
      throw new IllegalArgumentException();
    }
    if (number <= 1) {
      return 1;
    } else {
      return number * factorialRecursive(number - 1);
    }
  } 

  public static double power(int base, int power) {
    if (power == 0) {
      return 1;
    }
    double powerNum = base;
    boolean isNegative = power < 0;
    if (isNegative) {
      power *= -1;
    }
    while (power > 1) {
      powerNum *= base;
      power--;
    }
    return isNegative ? 1 / powerNum : powerNum;  
  }
  
  public static double powerRecursive(int base, int power) {
    return 0;
  }  

  public static int fibRecursive(int index) {
    return 0;    
  }

  public static int[] fibSequence(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException();
    }
    int[] fibSequence;
    if (length == 1) {
      fibSequence = new int[length];
      fibSequence[0] = 1;
      return fibSequence;
    }
    if (length == 2) {
      fibSequence = new int[length];
      fibSequence[0] = 1;
      fibSequence[1] = 1;
      return fibSequence;
    }
    fibSequence = new int[length];
    fibSequence[0] = 1;
    fibSequence[1] = 1;
    for (int i = 2; i < length; i++) {
      fibSequence[i] = fibSequence[i - 1] + fibSequence[i - 2];
    }
    return fibSequence;
  }
}
