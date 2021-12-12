package academy.kovalevskyi.javadeepdive.week0.day0;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StdBufferedReader implements Closeable {

  private static final int TRANSFER_BUFFER_SIZE = 8192;
  Reader reader;
  public static final char[] NEW_LINE = new char[]{};
  char[] charBuffer = NEW_LINE;
  int charCount = 0;
  int buffReadCounter = 0;
  int bufferSize;
  boolean endByNewline = false;
  boolean eof = false;

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
    if (!isBufferEmpty()) {
      return true;
    }
    return reader.ready();
  }

  public char[] readLine() throws IOException {
    char[] buffCache;
    if (buffReadCounter != -1) {
      buffCache = fillBuffer();
      if (buffCache == null) {
        return null;
      }
      buffCache = testForNewLine(buffCache);
      // lineOutPutCount++;
      return buffCache;
    }
    if (endByNewline) {
      endByNewline = false;
      return null;
    }
    return null;
  }

  public char[] fillBuffer() throws IOException {
    if (isBufferEmpty() && !eof) {
      charBuffer = new char[bufferSize];
      charCount = reader.read(charBuffer, 0, bufferSize);
      if (charCount < bufferSize && buffReadCounter == 0) { // первое чтение и полное
        eof = true;
        if (charBuffer[0] == (char) 0) {
          buffReadCounter = -1;
          return null;
        }
        charBuffer = arrayNewLengthReduceRight(charBuffer, charCount);
        if (charBuffer[charCount - 1] == '\n') {
          endByNewline = true;
        }
        return charBuffer;
      }
      if (charCount < bufferSize && buffReadCounter > 0 && charCount != -1) { // последнее чтение
        eof = true;
        charBuffer = arrayNewLengthReduceRight(charBuffer, charCount);
        if (charBuffer[charCount - 1] == '\n') {
          endByNewline = true;
        }
        return charBuffer;
      }
      if (charCount == -1) {
        eof = true;
        buffReadCounter = -1;
        return NEW_LINE;
      }
      buffReadCounter++;
      charBuffer = arrayNewLengthReduceRight(charBuffer, charCount);
      return charBuffer;
    }
    return charBuffer;
  }

  public char[] testForNewLine(char[] buffCache) throws IOException {
    for (int i = 0; i < charBuffer.length; i++) {
      if (charBuffer[i] == '\r') { // удаляем \r
        buffCache = buffCache.length > charBuffer.length
                ? arrayNewLengthReduceRight(buffCache, buffCache.length - charBuffer.length + i)
                : arrayRemoveCharAtIndex(buffCache, i);
        charBuffer = arrayRemoveCharAtIndex(charBuffer, i);
        break;
      }
    }

    for (int i = 0; i < charBuffer.length; i++) {
      if (charBuffer[i] == '\n') {
        for (int j = 0; j < buffCache.length; j++) {
          if (buffCache[j] == '\n') {
            buffCache = arrayNewLengthReduceRight(buffCache, j);
          }
        }
        if ((i == charBuffer.length - 1) && eof) {
          endByNewline = true;
        }
        charBuffer = arrayReduceLeftToIndex(charBuffer, i);
        if (isBufferEmpty() && eof) {
          buffReadCounter = -1;
        }
        return buffCache;
      }
    }
    charBuffer = NEW_LINE;
    if (!eof) {
      buffCache = testForNewLine(arrayCopyAndExtend(buffCache, fillBuffer()));
    }
    return buffCache;
  }

  public boolean isBufferEmpty() {
    for (char c : charBuffer) {
      if (c != (char) 0) {
        return false;
      }
    }
    return true;
  }

  public void close() throws IOException {
    reader.close();
  }

  public static char[] arrayCopyAndExtend(char[] sourceToExtend, char[] partToPlus) {
    char[] newCharLine = new char[sourceToExtend.length + partToPlus.length];
    System.arraycopy(sourceToExtend, 0, newCharLine, 0, sourceToExtend.length);
    System.arraycopy(partToPlus, 0, newCharLine, sourceToExtend.length, partToPlus.length);
    return newCharLine;
  }

  public static char[] arrayNewLengthReduceRight(char[] sourceToReduce, int length) {
    char[] newCharLine = new char[length];
    System.arraycopy(sourceToReduce, 0, newCharLine, 0, length);
    return newCharLine;
  }

  public static char[] arrayReduceLeftToIndex(char[] sourceToReduce, int index) {
    char[] newCharLine = new char[sourceToReduce.length - (index + 1)];
    System.arraycopy(sourceToReduce, index + 1, newCharLine, 0, newCharLine.length);
    return newCharLine;
  }

  public static char[] arrayRemoveCharAtIndex(char[] sourceToChange, int index) {
    return arrayCopyAndExtend(arrayNewLengthReduceRight(sourceToChange, index),
            arrayReduceLeftToIndex(sourceToChange, index));
  }

  public Stream<String> lines() {
    Iterator<String> iter = new Iterator<>() {
      String nextLine = null;

      @Override
      public boolean hasNext() {
        if (nextLine != null) {
          return true;
        } else {
          try {
            nextLine = Arrays.toString(readLine());
            return (nextLine != null);
          } catch (IOException e) {
            throw new UncheckedIOException(e);
          }
        }
      }

      @Override
      public String next() {
        if (nextLine != null || hasNext()) {
          String line = nextLine;
          nextLine = null;
          return line;
        } else {
          throw new NoSuchElementException();
        }
      }
    };
    return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
            iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
  }
}