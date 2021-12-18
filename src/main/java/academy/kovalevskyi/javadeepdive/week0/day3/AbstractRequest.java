package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractRequest<T> {

  protected Csv csv;

  protected AbstractRequest(Csv target) {
    this.csv = target;
  }

  protected abstract T execute() throws RequestException;

  protected int indexOfValue(String[] array, String searchValue) {
    return Arrays.asList(array).indexOf(searchValue);
  }

  protected ArrayList<Integer> findIndexInRows(Csv csv, Selector selector) {
    ArrayList<Integer> indexList = new ArrayList<>();
    if (selector == null) {
      for (int i = 0; i < csv.values().length; i++) {
        indexList.add(i);
      }
      return indexList;
    }
    String columnName = selector.fieldName();
    String value = selector.value();
    int columnIndex = indexOfValue(csv.headers(), columnName);
    if (columnIndex == -1) {
      return null;
    }

    for (int i = 0; i < csv.values().length; i++) {
      if (csv.values()[i][columnIndex].equals(value)) {
        indexList.add(i);
      }
    }
    return indexList;
  }
}
