package academy.kovalevskyi.javadeepdive.week0.day0;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class StdBufferedReader implements Closeable {
  private static final int TRANSFER_BUFFER_SIZE = 8192;
  Reader reader;

  public static final char[] NEW_LINE = new char[]{};
  char[] cBuf = NEW_LINE;
  int charCount = 0;
  int buffReadCounter = 0;
  int bufferSize;
  int fromPastLength;
  int lineOutPutCount = 0;
  boolean endByNew = false;
  boolean eof = false;

  public static void main(String[]args) throws IOException {

  }

  public StdBufferedReader(Reader reader, int bufferSize) {
    if (reader == null) {
      throw new NullPointerException();
    }
    if (bufferSize < 2) {
      throw new IllegalArgumentException();
    }
    this.reader = reader;
    this.bufferSize = bufferSize;
  }

  public StdBufferedReader(Reader reader) {
    this(reader, TRANSFER_BUFFER_SIZE);
  }

  public boolean hasNext() throws IOException {
    if (!cBufEmpty()) {
      return true;
    }
    if (eof && endByNew) {
      return false;
    }
    return reader.ready();
  }

  public char[] readLine() throws IOException {
    char[] accum;
    fromPastLength = 0;
    if (buffReadCounter != -1) {
      accum = fillBuffer();
      if (accum == null) {
        return null;
      }
      accum = testForNewLine(accum);
      lineOutPutCount++;
      return accum;
    }
    if (endByNew) {
      endByNew = false;
      return null;
    }
    return null;
  }

  public char[] fillBuffer() throws IOException {
    if(cBufEmpty() && !eof) {
      cBuf = new char[bufferSize];
      charCount = reader.read(cBuf, 0, bufferSize);

      if (charCount < bufferSize && buffReadCounter == 0) { // первое чтение и полное
        eof = true;
        if(cBuf[0] == (char) 0) {
          buffReadCounter = -1;
          return null;
        }
        cBuf = arrayCopyReduceRight(cBuf,charCount);
        if(cBuf[charCount - 1] == '\n') {
          endByNew = true;
        }
        return cBuf;
      }

      if (charCount < bufferSize && buffReadCounter > 0 && charCount != -1) { // последнее чтение
        eof = true;
        cBuf = arrayCopyReduceRight(cBuf,charCount);
        if(cBuf[charCount - 1] == '\n') {
            endByNew = true;
        }
        return cBuf;
      }
      buffReadCounter++;

      if (charCount == -1) {
        eof = true;
        buffReadCounter =-1;
        return NEW_LINE;
      }
      cBuf = arrayCopyReduceRight(cBuf, charCount);
      return cBuf;
    }
    return cBuf;
  }

  public char[] testForNewLine(char[] accum) throws IOException {
    for (int i = 0; i < cBuf.length; i++) {
      if (cBuf[i] == '\r') { // удаляем \r
        accum = accum.length > cBuf.length ? arrayCopyReduceRight(accum, accum.length - cBuf.length + i) : arrayCopyAndRemove(accum, i);
        cBuf = arrayCopyAndRemove(cBuf, i);
        break;
      }
    }

    for (int i = 0; i < cBuf.length; i++) {
      if (cBuf[i] == '\n') {
          for (int j = 0; j < accum.length; j++) { //accum.length - cBuf.length
            if (accum[j] == '\n') {
              accum = arrayCopyReduceRight(accum, j);
            }
          }

        if ((i == cBuf.length - 1) && eof){
          endByNew = true;
        }
        cBuf = arrayCopyReduceLeft(cBuf, i);
        if (cBufEmpty() && eof) {
          buffReadCounter = -1;
        }
        return accum;
      }
    }
    cBuf = new char[bufferSize];
    fromPastLength = charCount > 0 ? fromPastLength + charCount : 0;
    if (!eof) {
        accum = testForNewLine(arrayCopyAndExtend(accum, fillBuffer()));
      }
    return accum;
  }
  public boolean cBufEmpty() {
    for (char c : cBuf) {
      if (c != (char) 0) {
        return false;
      }
    }
    return true;
  }

  public void close() throws IOException {
    reader.close();
  }

  public void print(char[] arr, String name) {
  System.out.printf("%s %s / charCount %s / bufferRCount %s / fromPast %s / output %s / eof %s / endByNew %s\n",
        name, arr.length, charCount, buffReadCounter, fromPastLength, lineOutPutCount, eof, endByNew);
    for (char c : arr) {
      String toPrint = (c == (char) 10) ? "/n" : (c == (char) 13) ? "/r" : String.valueOf(c);
      System.out.printf("'%s' ", toPrint);
    }
    System.out.println();
  }

  public static char[] arrayCopyAndExtend(char[] sourceToExtend, char[] partToPlus) {
    char[] newCharLine = new char[sourceToExtend.length + partToPlus.length];
    System.arraycopy(sourceToExtend,0, newCharLine,0,sourceToExtend.length);
    System.arraycopy(partToPlus,0, newCharLine,sourceToExtend.length,partToPlus.length);
    return newCharLine;
  }
  public static char[] arrayCopyReduceRight(char[] sourceToReduce, int length) {
    char[] newCharLine = new char[length];
    System.arraycopy(sourceToReduce, 0, newCharLine, 0, length);
    return newCharLine;
  }
  public static char[] arrayCopyReduceLeft(char[] sourceToReduce, int index) {
    char[] newCharLine = new char[sourceToReduce.length - (index + 1)];
    System.arraycopy(sourceToReduce, index + 1, newCharLine, 0, newCharLine.length);
    return newCharLine;
  }
  public static char[] arrayCopyAndRemove(char[] sourceToChange, int index) {
    return arrayCopyAndExtend(arrayCopyReduceRight(sourceToChange, index), arrayCopyReduceLeft(sourceToChange,index));
  }

}