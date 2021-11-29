package academy.kovalevskyi.codingbootcamp.week2.day2;

import java.util.Arrays;
import java.util.Random;

public class Main {
  public static int parts;
  public static int[] times;
  public static int partsDone;
  public static double averageTime;
  public static int maxTime;
  public static int percentDone;
  public static long start;
  public static int eta;

  public static void main(String[] args) throws InterruptedException {
    if(args[0])
    parts = Integer.parseInt(args[0]);
    times = new int[args.length - 1];
    for (int i = 0; i < args.length - 1; i++) {
      times[i] = Integer.parseInt(args[i+1]);
    }
    partsDone = 0;
    averageTime = Arrays.stream(times).average().getAsDouble();
    maxTime = Arrays.stream(times).max().getAsInt();
    eta = (int) (maxTime * parts * 0.8);
    printTaskProgress();
    start = System.currentTimeMillis();
    while(partsDone < parts) {
      partsDone++;
      Thread.sleep(1000 * randomTimeFromArgs());
      printTaskProgress();
    }
    printTaskProgress();
    System.out.println("\n");  
}

  public static void printTaskProgress() {
    percentDone = (partsDone * 100) / parts;
    clearString();
    System.out.print(percentageString()
            + progressLine() + forPrintPartsDone() + estimatedTime());
  }

  public static String progressLine() {
    String progLine = "[";
    if (percentDone == 0) {
      for (int i = 1; i <= 100; i++) {
        progLine += " ";
      }
    } else if (percentDone == 100) {
      for (int i = 1; i <=  percentDone; i++) {
        progLine += "=";
      }
    } else {
      for (int i = 1; i <  percentDone; i++) {
        progLine += "=";
      }
      progLine += ">";
      for (int i = percentDone + 1; i <= 100; i++) {
        progLine += " ";
      }
    }
    progLine += "] ";
    return progLine;
  }

  public static String forPrintPartsDone() {
    return parts > 9 ? (partsDone < 10 ? (" " + partsDone + " / " + parts + " ") : (partsDone + " / " + parts + " "))
            : (partsDone + " / " + parts + " ");
  }

  public static int randomTimeFromArgs() {
    return times[new Random().nextInt(times.length)];
  }

  public static String estimatedTime() {
    long current = System.currentTimeMillis();
    int deltaTime = (int) (current - start)/1000;
    if (percentDone != 0) {    
      eta = percentDone < 70 ? (eta - deltaTime) : (parts - partsDone) > 0 ? (1 + eta - eta / (parts - partsDone)) : 0;
    }
    start = current;
    int hours = eta / 3600;
    int minutes = (eta % 3600) / 60;
    int seconds = eta % 60;
    return String.format("ETA: %02d:%02d:%02d", hours, minutes, seconds);
  }

  public static String percentageString() {
    int percentDone = (partsDone * 100) / parts;
    return percentDone < 10 ? "  " + percentDone + "% " :
            (percentDone < 100 ? " " + percentDone + "% " : percentDone + "% ");
  }

  public static void clearString() {
   System.out.print("\r");
  }
}
