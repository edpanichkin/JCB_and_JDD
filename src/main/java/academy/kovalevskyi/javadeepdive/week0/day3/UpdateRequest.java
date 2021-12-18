package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;
import java.util.ArrayList;

public class UpdateRequest extends AbstractRequest<Csv> {
  Selector where;
  Selector update;

  private UpdateRequest(Csv target, Selector whereSelector, Selector updateToSelector) {
    super(target);
    this.where = whereSelector;
    this.update = updateToSelector;
  }

  protected Csv execute() throws RequestException {
    if (this.csv == null) {
      throw new RequestException();
    }
    ArrayList<Integer> columnIndexToUpdate = findIndexInRows(csv, where);
    int columnIndexOfValueToChange = indexOfValue(csv.headers(), update.fieldName());
    String[][] newValue = new String[csv.values().length][];
    System.arraycopy(csv.values(), 0, newValue, 0, csv.values().length);
    int counter = 0;
    for (int i = 0; i < csv.values().length; i++) {
      if (i == columnIndexToUpdate.get(counter)) {
        newValue[i][columnIndexOfValueToChange] = update.value();
        counter++;
      }
    }
    return new Csv.Builder()
            .header(csv.headers())
            .values(newValue)
            .build();
  }

  public static class Builder {
    Selector where;
    Selector update;
    Csv csv;

    public Builder where(Selector selector) {
      this.where = selector;
      return this;
    }

    public Builder update(Selector updateSelector) {
      this.update = updateSelector;
      return this;
    }

    public Builder from(Csv csv) {
      this.csv = csv;
      return this;
    }

    public UpdateRequest build() {
      return new UpdateRequest(csv, where, update);
    }
  }
}