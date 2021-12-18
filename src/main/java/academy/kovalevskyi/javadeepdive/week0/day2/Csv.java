package academy.kovalevskyi.javadeepdive.week0.day2;

import java.util.Arrays;

public record Csv(String[] headers, String[][] values) {

  public boolean withHeader() {
    return headers != null && headers.length != 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    Csv that = (Csv) o;
    if (withHeader() && !Arrays.equals(this.headers, that.headers)) {
      return false;
    }
    return Arrays.deepEquals(this.values, that.values);
  }

  @Override
  public int hashCode() {
    int result = Arrays.hashCode(headers);
    result = 31 * result + Arrays.hashCode(values);
    return result;
  }

  public static class Builder {

    public String[] header;
    public String[][] values;

    public Builder header(String[] header) {
      this.header = header;
      return this;
    }

    public Builder values(String[][] values) {
      for (String[] value : values) {
        if (values[0].length != value.length && value.length != 0) {
          throw new InvalidCsvLineLengthException();
        }
        if (build().withHeader() && header.length != value.length) {
          throw new InvalidCsvLineLengthException();
        }
      }
      this.values = values;
      return this;
    }

    public Csv build() {
      return new Csv(header, values);
    }
  }
}