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
    if (power > 0) {
      return base * powerRecursive(base, power - 1);    
    } 
    if (power < 0) {
      return 1 / powerRecursive(base, -1 * (power)); 
    }
    return 1;
  }  

  public static int fibRecursive(int index) {
    if (index < 0) {
      throw new IllegalArgumentException();
    }
    if (index == 0) {
      return 0;
    } else if (index == 1) {
      return 1;
    } else  {
      return fibRecursive(index - 1) + fibRecursive(index - 2);
    }
  }

  public static int[] fibSequence(int length) {
    if (length < 0) {
      throw new IllegalArgumentException();
    }
    if (length == 0) {
      return new int[0];
    }
    int[] fibSequence;
    if (length == 1) {
      fibSequence = new int[length];
      fibSequence[0] = 1;
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

  public static int sqrt(int target) {
    if (target <= 0) {
      throw new IllegalArgumentException();
    }
    for (int i = 1; i <= 46340; i++) {
      if (power(i, 2) == target) {
        return i;
      }
    }
    return -1;
  }

  public static boolean isPrime(int target) {
    System.out.println(target);    
    if (target == 1) {
      return false;
    }
    if (target <= 0 || target > Integer.MAX_VALUE) {
      throw new IllegalArgumentException();
    }
    for (int i = 2; i < target; i++) {
      if (target % i == 0 && i < target) {
        return false;
      } 
    }
    return true; 
  }

  public static int findNextPrime(int target) {
    if (target < 0) {
      throw new IllegalArgumentException();
    }
    if (target == 0) {
      return 2;  
    }
    if (isPrime(target)) {
      return target;
    }
    return findNextPrime(target + 1);
  }

  public static void sort(int[] target) {
    if (target == null) {
      return;
    }
    boolean isSorted = false;
    int buf;
    while (!isSorted) {
      isSorted = true;
      for (int i = 0; i < target.length - 1; i++) {
        if (target[i] > target[i + 1]) {
          isSorted = false;
          buf = target[i];
          target[i] = target[i + 1];
          target[i + 1] = buf;
        }
      }
    }
  }
}
