package academy.kovalevskyi.codingbootcamp.week0.day4;

public class Numbers1 {
  public static int[] generateNumbers() {
    int[] int100 = new int[100];
    for (int i = 0; i < 100; i++) {
      int100[i] = i;
    }
    return int100;
  }

  public static int findBiggest(int left, int right) {
    return left >= right ? left : right;
  }
  
  public static int findBiggest(int left, int mid, int right) {
    return findBiggest(mid, findBiggest(left, right));
  }

  public static int findBiggest(int[] numbers) {
    int max = numbers[0];
    for (int i = 1; i < numbers.length; i++) {
      max = max <= numbers[i] ? numbers[i] : max;
    }
    return max;
  }
  
  public static int findIndexOfBiggestNumber(int[] numbers) {
    int max = numbers[0];
    int index = 0;
    for (int i = 1; i < numbers.length; i++) {
      if (max < numbers[i]) {
        max = numbers[i];
        index = i;
      }
    }
    return index;
  }

  public static boolean isNegative(int number) {
    return (number < 0);
  }
  
  public static char[] convertToCharArray(final int number) {
    if (number == 0) {
      return new char[]{'0'};
    }
    if (number == Integer.MIN_VALUE) {
      return new char[]{'-', '2', '1', '4', '7', '4', '8', '3', '6', '4', '8'};
    }
    int remainder;
    int num = number;
    int numOfDigits = numOfDigits(number);
    boolean isNegative = number < 0;
    char[] digitsChar;
    if (isNegative) {
      digitsChar = new char[numOfDigits + 1];
      digitsChar[0] = '-';
      num *= -1;
    } else {
      digitsChar = new char[numOfDigits];
    }
    for (int i = digitsChar.length - 1; (isNegative ? i > 0 : i >= 0); i--) {
      digitsChar[i] = (char) ((num % 10) + 48);
      num /= 10;
    }
    return digitsChar;
  }

  public static int numOfDigits(int number) {
    int numOfDigits = (number == 0) ? 1 : 0;
    while (number != 0) {
      numOfDigits++;
      number /= 10;
    }
    return numOfDigits; 
  }
}
