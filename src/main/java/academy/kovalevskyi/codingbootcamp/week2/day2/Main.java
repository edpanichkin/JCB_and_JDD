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
  public static void main(String[] args) throws InterruptedException {
    parts = Integer.parseInt(args[0]);
    times = new int[args.length - 1];
    for (int i = 0; i < args.length - 1; i++) {
      times[i] = Integer.parseInt(args[i+1]);
    }
    partsDone = 0;
    averageTime = Arrays.stream(times).average().getAsDouble();
    maxTime = Arrays.stream(times).max().getAsInt();
    start = System.currentTimeMillis();
    printTaskProgress();
    while(partsDone < parts) {
      partsDone++;
      Thread.sleep(1000 * randomTimeFromArgs());
      printTaskProgress();
    }
    printTaskProgress();
    System.out.println("\n\n");
  }

  public static void printTaskProgress() {
    percentDone = (partsDone * 100) / parts;
    cls();
    System.out.print(percentageString()
            + progressLine() + status() + estimatedTime());
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

  public static String status() {
    return parts > 9 ? (partsDone < 10 ? (" " + partsDone + " / " + parts + " ") : (partsDone + " / " + parts + " "))
            : (partsDone + " / " + parts + " ");
  }

  public static int randomTimeFromArgs() {
    return times[new Random().nextInt(times.length)];
  }

  public static String estimatedTime() {
    long current = System.currentTimeMillis();
    int deltaTime = (int) (current - start)/1000;
    int eta = (int) (averageTime * (parts - partsDone) - deltaTime) <= 0 ? 0 : (int) (averageTime * parts - deltaTime);    
    eta = eta > (int) averageTime * (parts - partsDone) && percentDone > 80 ? (int) averageTime * (parts - partsDone) : eta;
    int hours = eta / 3600;
    int minutes = (eta % 3600) / 60;
    int seconds = eta % 60;
//    System.out.print(minutes + " - m - " + seconds + "s " + eta + "delta "+ deltaTime); 
    return String.format("ETA: %02d:%02d:%02d", hours, minutes, seconds);
  }

  public static String percentageString() {
    int percentDone = (partsDone * 100) / parts;
    return percentDone < 10 ? "  " + percentDone + "% " :
            (percentDone < 100 ? " " + percentDone + "% " : percentDone + "% ");
  }

  public static void cls() {
   System.out.print("\r");
  }
}
