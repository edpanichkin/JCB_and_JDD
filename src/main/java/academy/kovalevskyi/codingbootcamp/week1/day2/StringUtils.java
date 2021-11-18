package academy.kovalevskyi.codingbootcamp.week1.day2;

import java.util.stream.IntStream;

public class StringUtils {
  public static boolean isAsciiUppercase(char ch) {
    //A - Z : 65 - 90
    return isAsciiInRange(ch, 65, 90);
  }

  public static boolean isAsciiLowercase(char ch) {
    //a - z : 97 - 122
    return isAsciiInRange(ch, 97, 122);
  }

  public static boolean isAsciiNumeric(char ch) {
    //0 - 9 : 48 - 57
    return isAsciiInRange(ch, 48, 57);
  }

  public static boolean isAsciiAlphabetic(char ch) {
    //A - Z : 65 - 90
    //a - z : 97 - 122
    return isAsciiInRange(ch, 65, 90) || isAsciiInRange(ch, 97, 122);
  }
    
  public static char toAsciiLowercase(char ch) {
    return toAsciiChangeCase(ch, -1);
  }

  public static char toAsciiUppercase(char ch) {
    return toAsciiChangeCase(ch, 1);
  }

  public static char toAsciiChangeCase(char ch, int flag) {
    int intChar = (int) ch;
    asciiCheck(ch);
    if (flag == 1 && isAsciiInRange(ch, 97, 122)) {
      return ch -= (char) 32;
    }
    if (flag == -1 && isAsciiInRange(ch, 65, 90)) {
      return ch += (char) 32;
    }
    return ch;
  }

  public static boolean isAsciiInRange(char ch, int left, int right) {
    asciiCheck(ch);
    int intChar = (int) ch;
    return intChar >= left && intChar <= right;
  }

  public static StringBuilder caseChange(char[] input, int flag) {
    asciiCheck(input);
    for (int i = 0; i < input.length; i++) {
      if (isAsciiInRange(input[i], 65, 90) && flag == -1) {
        input[i] = toAsciiLowercase(input[i]);
      }
      if (isAsciiInRange(input[i], 97, 122) && flag == 1) {
        input[i] = toAsciiUppercase(input[i]);
      }
    }
    return new StringBuilder().append(input);
  }
   
  public static StringBuilder makeUppercase(char[] input) {
    return caseChange(input, 1);
  }

  public static StringBuilder makeLowercase(char[] input) {
    return caseChange(input, -1);
  }

  public static StringBuilder makeCamel(char[] input) {
    return caseChange(input, 0);
  }

  public static boolean isStringAlphaNumerical(char[] input) {
    asciiCheck(input);
    int i = 0;
    while (i < input.length) {
      char c = input[i];
      if (isAsciiInRange(c, 48, 57)
          || isAsciiInRange(c, 65, 90)
          || isAsciiInRange(c, 97, 122)
          || isAsciiInRange(c, 32, 32)) {
        i++;            
      } else {
        return false;
      }
    }
    return true;
  }

  public static char[] concatStrings(char[][] input) {
    if (input.length == 0) {
      throw new IllegalArgumentException();
    }
    char[] a = input[0].clone();
    char[] b = input[1].clone();
    char[] concat = new char[a.length + b.length];
    if (asciiCheck(a) && asciiCheck(b)) {
      System.arraycopy(a, 0, concat, 0, a.length);
      System.arraycopy(b, 0, concat, a.length, b.length);
    }
    return concat;
  }

  public static boolean asciiCheck(char input) {
    if (input > 255 || input < 0) {
      throw new IllegalArgumentException();
    }
    return true;
  }
  
  public static boolean asciiCheck(char[] input) {
    for (int i = 0; i < input.length; i++) {
      asciiCheck(input[i]);
    }
    return true;
  }
}

