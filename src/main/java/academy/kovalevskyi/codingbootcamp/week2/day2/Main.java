package academy.kovalevskyi.codingbootcamp.week2.day2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {

  public static void main(String[] args) throws InterruptedException, IOException {
    cls();   
// int parts = Integer.getInteger(args[0]);
    int parts = 15;
    int partsDone = 0;
    int[] times = new int[10];
    for (int i = 1; i < 10; i++) {
      times[i] = i;
    }
    for (int i = 0; i < args.length; i++) {
      times[i] = Integer.parseInt(args[i+1]);
    }

    print(partsDone, parts);    
    while(partsDone < parts) {
      partsDone++;
      Thread.sleep(500);
      cls();
       print(partsDone, parts);    
    }
    System.out.println("");  
  }

  public static void print(int partsDone, int parts) {
    int percentDone = (partsDone * 100) / parts;

    System.out.print(percentageString(partsDone, parts)
      + progressLine(percentDone));
    System.out.println();
  }

  public static String progressLine(int percent) {
    String progLine = "[";
    for (int i = 1; i <  percent; i++) {
      progLine += "#";
    }
      progLine += ">";
    for (int i = percent + 1; i <=100; i++) {
      progLine += "-";
    }
    progLine += "] ";
    return progLine;
  }
 
  public static int randomTimeFromArgs(int[] arr) {
    return arr[new Random().nextInt(arr.length)];
  }

  public static int maxTime(int parts, int[] times) {
    int max = Arrays.stream(times)
            .min()
            .getAsInt();
    return parts * max;
  }

  public static String percentageString(int partsDone, int parts) {
    int percentDone = (partsDone * 100) / parts;
    return percentDone < 100 ? " " + percentDone + "% " : percentDone + " % ";
  }

  public static void cls() {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }
}
