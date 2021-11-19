package academy.kovalevskyi.codingbootcamp.week1.day4;

import academy.kovalevskyi.codingbootcamp.week1.day2.StdString1;
import academy.kovalevskyi.codingbootcamp.week1.day2.StringUtils;

public class StdString2 extends StdString1 {
  public StdString2(char[] value) {
    super(value);
  }

  public StdString2() {
  }

  public StdString2(StdString2 that) {
    super(that);
  }

  public StdString2 toAsciiLowerCase() {
    char[] lowerChars = new char[length()];
    StringUtils.makeLowercase(value).getChars(0, length(), lowerChars, 0);
    return new StdString2(lowerChars);
  }

  public StdString2 toAsciiUpperCase() {
    char[] upperChars = new char[length()];
    StringUtils.makeUppercase(value).getChars(0, length(), upperChars, 0);
    return new StdString2(upperChars);
  }

  public StdString2 subString(int from) {
    return subString(from, length());
  }

  public StdString2 subString(int from, int to) {
    if (from > to) {
      throw new IllegalArgumentException();
    }
    if ((from > length()) || (to > length())) {
      throw new IndexOutOfBoundsException();
    }
    char[] result = new char[(to - from)];
    System.arraycopy(value, from, result, 0, result.length);
    return new StdString2(result);
  }

  public StdString2 concat(StdString2... that) {
    StringBuilder sb = new StringBuilder();
    sb.append(this.value);
    for (int i = 0; i < that.length; i++) {
      if (that[i].length() != 0) {
        sb.append(that[i].value);
      }
    }
    return new StdString2(sb.toString().toCharArray());
  } 

  public StdString2[] split(char separator) {
    int[] count = new int[value.length];
    int index = 0;
    for (int i = 0; i < value.length; i++) {
      if (value[i] != separator) {
        count[index]++;
      } else {
        index++;
      }
    }
    index = (value[length() - 1] != separator) ? ++index : --index;
    StdString2[] stdResult = new StdString2[index];
    int pos = 0;
    for (int i = 0; i < stdResult.length; i++) {
      stdResult[i] = subString(pos, pos + count[i]);
      pos += count[i] + 1;
    }
    return stdResult;
  }

  public StdString2 trim() {
    if (length() == 0) {
      return new StdString2();
    }    
    int leftSpace = 0;
    int rightSpace = 0;
    while (value[leftSpace] == (char) 32) {
      leftSpace++;
      if (leftSpace == length()) {
        return new StdString2();
      }
    }
    while (value[length() - 1 - rightSpace] == (char) 32) {
      rightSpace++;
    }
    char[] trimChars = new char[length() - (leftSpace + rightSpace)];
    System.arraycopy(value, leftSpace, trimChars, 0, trimChars.length);
    return new StdString2(trimChars);
  }

  public StdString2 removeCharacter(char toRemove) {
    int count = 0;
    for (int i = 0; i < value.length; i++) {
      if (value[i] == toRemove) {
        count++;
      }
    }
    char[] result = new char[length() - count];
    int charIndex = 0;
    for (int i = 0; i < value.length; i++) {
      if (value[i] != toRemove) {
        result[charIndex] = value[i];
        charIndex++; 
      }
    }
    return new StdString2(result);
  }
}
