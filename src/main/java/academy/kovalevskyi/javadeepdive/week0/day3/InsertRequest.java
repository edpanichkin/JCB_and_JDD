package academy.kovalevskyi.javadeepdive.week0.day3;

import academy.kovalevskyi.javadeepdive.week0.day2.Csv;

public class InsertRequest extends AbstractRequest<Csv> {

  String[] line;

  public InsertRequest(Csv target, String[] line) {
    super(target);
    this.line = line;
  }

  public Csv execute() throws RequestException {
    if (this.csv.headers() == null) {
      throw new RequestException();
    }
    String[][] newValue = new String[csv.values().length + 1][];
    System.arraycopy(csv.values(), 0, newValue, 0, csv.values().length);
    newValue[newValue.length - 1] = line;
    return new Csv.Builder()
        .header(csv.headers())
        .values(newValue)
        .build();
  }

  public static class Builder {

    String[] line;
    Csv csv;

    public Builder insert(String[] line) {
      this.line = line;
      return this;
    }

    public Builder to(Csv csv) {
      this.csv = csv;
      return this;
    }

    public InsertRequest build() {
      return new InsertRequest(csv, line);
    }
  }
}