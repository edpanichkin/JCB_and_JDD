package academy.kovalevskyi.codingbootcamp.week1.day2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

public class StdString1 implements Iterable<Character> {
  public final char[] value;

  public StdString1(char[] value) {
    this.value = value.clone();
  }

  public StdString1() {
    this.value = new char[]{};
  }

  public StdString1(StdString1 stdString1) {
    this.value = stdString1.value.clone();
  }

  public StdString1 append(StdString1 that) {
    int totalLength = this.length() + that.length();
    char[] sumArr = new char[totalLength];
    for (int i = 0; i < this.length(); i++) {
      sumArr[i] = this.value[i];
    }
    for (int i = 0; i < that.length(); i++) {
      sumArr[i + this.length()] = that.value[i];
    }
    return new StdString1(sumArr);
  }

  public int length() {
    return this.value.length;
  }

  public char[] toCharArray() {
    return this.value;
  }

  public char charAt(int index) {
    return this.value[index];
  }

  public int indexOf(char target) {
    for (int i = 0; i < this.length(); i++) {
      if (target == this.value[i]) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public boolean equals(final Object otherObj) {
    if (this == otherObj) {
      return true;
    }
    if (otherObj == null || getClass() != otherObj.getClass()) {
      return false;
    }
    StdString1 that = (StdString1) otherObj;
    if (this.length() != that.length()) {
      return false;
    }
    for (int i = 0; i < that.length(); i++) {
      if (this.value[i] != that.value[i]) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    int sum = 0;
    if (this.length() == 0) {
      return 0;
    }
    for (char c : this.value) {
      sum += (int) c;
    }
    return sum;
  }

  @Override
  public String toString() {
    return String.valueOf(this.value);
  }

  @Override
  public void forEach(final Consumer<? super Character> action) {
    Objects.requireNonNull(action);
    for (Character c : this) {
      action.accept(c);
    }
  }  
  
  @Override
  public Iterator<Character> iterator() {
    return new Iterator<Character>() {
      private int index = -1; 
      @Override
      public boolean hasNext() {
        return index < length() - 1;
      }
      
      @Override
      public Character next() {
        if (!hasNext()) {
          throw new NoSuchElementException();   
        }
        index++;
        return charAt(index);
      }
    };
  }
}
