package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import java.util.ArrayList;
import java.util.Objects;

public class SelectRequest extends AbstractRequest<String[][]> {

  Selector selector;
  String[] columns;

  public SelectRequest(Csv target, Selector selector, String[] columns) {
    super(target);
    this.selector = selector;
    this.columns = columns;
  }

  @Override
  public String[][] execute() throws RequestException {

    ArrayList<Integer> rowIndexes = findIndexInRows(csv, selector); // какие строчки менять (0,2)
    ArrayList<Integer> columnIndexToOut = new ArrayList<>();
    for (String column : columns) {
      System.out.println();
      columnIndexToOut.add(indexOfValue(csv.headers(), column)); // индекс столбцов для вывода value

    }
    String[][] result = new String[rowIndexes.size()][columns.length];

    int rowCount = 0;
    for (int i = 0; i < csv.values().length; i++) {
      if (i == rowIndexes.get(rowCount)) {
        for (int j = 0; j < columnIndexToOut.size(); j++) {
          result[rowCount][j] = csv.values()[i][columnIndexToOut.get(j)];
        }
      }
      rowCount++;
    }
    return result;
  }

  public static class Builder {

    Csv csv;
    Selector where;
    String[] columns;

    public Builder where(Selector selector) {
      this.where = selector;
      return this;
    }

    public Builder select(String[] columns) {
      this.columns = columns;
      return this;
    }

    public Builder from(Csv csv) {
      this.csv = csv;
      return this;
    }

    public SelectRequest build() {
      Objects.nonNull(csv);
      return new SelectRequest(csv, where, columns);
    }
  }
}
