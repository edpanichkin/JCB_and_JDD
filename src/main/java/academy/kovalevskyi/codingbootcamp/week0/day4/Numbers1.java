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
  
  public static char[] convertToCharArray(int number) {
    int remainder;
    int numberCore = number;
    int numOfDigits = numOfDigits(number);
    if (number == 0) {
      return new char[]{'0'};
    }
    char[] digitsChar = new char[numOfDigits];
    for (int i = numOfDigits - 1; i >= 0; i--) {
      remainder = ((number % 10) > 0) ? (number % 10) : (-1 * (number % 10)); 
      digitsChar[i] = (char) (remainder + 48);
      number /= 10;
    }
    return (numberCore > 0) ? digitsChar : addMinusToCharArr(digitsChar);
  }

  private static char[] addMinusToCharArr(char[] charArr) {
    char[] minusCharArr = new char[charArr.length + 1];
    minusCharArr[0] = (char) 45;
    for (int i = 0; i < charArr.length; i++) {
      minusCharArr[i + 1] = charArr[i];
    }
    return minusCharArr;
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
