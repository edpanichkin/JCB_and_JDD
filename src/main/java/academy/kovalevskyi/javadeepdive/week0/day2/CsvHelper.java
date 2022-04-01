package academy.kovalevskyi.javadeepdive.week0.day2;

import academy.kovalevskyi.javadeepdive.week0.day0.StdBufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

public class CsvHelper {

  public static final char DELIMITER = ',';

  public static Csv parseCsvFrom(Reader reader) throws IOException {
    String[][] data =  rawDataFromReader(reader, DELIMITER);
    return new Csv(null, data);
  }

  public static Csv parseCsvFrom(Reader reader,
                                 boolean withHeader, char delimiter) throws IOException {
    String[][] data =  rawDataFromReader(reader, delimiter);
    if (withHeader && data != null) {
      String[] headers = data[0];
      String[][] values = new String[data.length - 1][];
      System.arraycopy(data, 1, values, 0, data.length - 1);
      return new Csv.Builder()
              .header(headers)
              .values(values)
              .build();
    }
    return new Csv.Builder().build();
  }

  public static void writeCsvTo(Writer writer, Csv csv, char delimiter) throws IOException {
    StringBuilder csvToWrite = new StringBuilder();
    String[] headers = csv.headers();
    String[][] values = csv.values();

    for (int i = 0; i < headers.length; i++) {
      csvToWrite.append((i < headers.length - 1)
              ? headers[i] + delimiter : headers[i] + System.lineSeparator());
    }
    for (int i = 0; i < values.length; i++) {
      for (int j = 0; j < values[i].length; j++) {
        csvToWrite.append((j < values[j].length - 1) ? values[i][j] + delimiter : values[i][j]);
      }
      csvToWrite.append(i < values.length - 1 ? System.lineSeparator() : "");
    }
    writer.write(csvToWrite.toString());
    writer.close();
  }

  public static String[][] rawDataFromReader(Reader reader, char delimiter) throws IOException {
    ArrayList<String> linesWithValues = new ArrayList<>();
    StdBufferedReader stdBufferedReader = new StdBufferedReader(reader);
    while (stdBufferedReader.hasNext()) {
      String lineToAdd = String.valueOf(stdBufferedReader.readLine());
      linesWithValues.add(lineToAdd);
    }
    stdBufferedReader.close();
    String[][] values = new String[linesWithValues.size()][];
    if (values.length == 0) {
      return null;
    }
    for (int i = 0; i < values.length; i++) {
      values[i] = stringAnalyzer(linesWithValues.get(i), delimiter);
    }
    return values;
  }
  public static String[] stringAnalyzer(String line, char delimiter) {
    boolean quotesOpen = false;
    ArrayList<String> analyzedStrings = new ArrayList<>();
    char[] charArr = line.toCharArray();
    StringBuilder buff = new StringBuilder();
    for (int i = 0; i < charArr.length; i++) {

      if (charArr[i] == (char) 34) {
        if (quotesOpen && (i + 1) != charArr.length && charArr[i + 1] != delimiter) {
          buff.append(charArr[i]);
        }
        quotesOpen = !quotesOpen;
        continue;
      }
      if (charArr[i] == delimiter && !quotesOpen) {
        analyzedStrings.add(buff.toString());
        buff = new StringBuilder();
        continue;
      }
      buff.append(charArr[i]);
    }
    analyzedStrings.add(buff.toString());
    return analyzedStrings.toArray(new String[0]);
  }
}