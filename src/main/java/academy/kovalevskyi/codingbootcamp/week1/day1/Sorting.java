package academy.kovalevskyi.codingbootcamp.week1.day1;

import java.util.Comparator;

public class Sorting<T> implements Comparator<T> {

  public static <T> void sort(T[] target, Comparator<T> comparator) {
    if (target == null) {
      throw new NullPointerException();
    }
    boolean isSorted = false;
    T buf;
    while (!isSorted) {
      isSorted = true;
      for (int i = 0; i < target.length - 1; i++) {
        if (comparator.compare((T) target[i], (T) target[i + 1]) > 0) {
          isSorted = false;
          buf = target[i];
          target[i] = target[i + 1];
          target[i + 1] = buf;
        }
      }
    }
  }

  public static <T> void sortReversedOrder(T[] target, Comparator<T> comparator) {
    if (target == null) {
      throw new NullPointerException();
    }
    boolean isSorted = false;
    T buf;
    while (!isSorted) {
      isSorted = true;
      for (int i = 0; i < target.length - 1; i++) {
        if (comparator.compare((T) target[i], (T) target[i + 1]) < 0) {
          isSorted = false;
          buf = target[i];
          target[i] = target[i + 1];
          target[i + 1] = buf;
        }
      }
    }
  }

  @Override
  public int compare(T o1, T o2) {
    return 0;
  }

}
