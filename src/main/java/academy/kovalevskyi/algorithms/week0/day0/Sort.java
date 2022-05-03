package academy.kovalevskyi.algorithms.week0.day0;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public interface Sort {
  default <T extends Comparable<? super T>> void sort(final T[] target) {
    sort(target, T::compareTo);
  }

  default <T> void sort(final T[] target, final Comparator<T> comparator) {
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

  default <T> T[] createSortedArray(final T[] target, final Comparator<T> comparator) {
    T[] result = Arrays.copyOf(target, target.length);
    sort(result, comparator);
    return result;
  }

  default String complexityBest() {
    return "N";
  }

  default String complexityAverage() {
    return "N^2";
  }

  default String complexityWorst() {
    return "N^2";
  }

  default String spaceComplexityWorst() {
    return "N";
  }
}