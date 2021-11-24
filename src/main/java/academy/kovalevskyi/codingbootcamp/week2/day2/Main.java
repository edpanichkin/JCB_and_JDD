package academy.kovalevskyi.codingbootcamp.week2.day2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {

  public static void main(String[] args) throws InterruptedException, IOException {
    cls();   
    int parts = Integer.parseInt(args[0]);
    int partsDone = 0;
    int[] times = new int[args.length - 1];
    for (int i = 0; i < args.length - 1; i++) {
      times[i] = Integer.parseInt(args[i+1]);
    }

    print(partsDone, parts, times);    
    while(partsDone < parts) {
      partsDone++;
      Thread.sleep(500 * randomTimeFromArgs(times));
      cls();
      print(partsDone, parts, times);    
    }
    cls();
    print(partsDone, parts, times);    
    System.out.println("");  
  }

  public static void print(int partsDone, int parts, int[] times) {
    int percentDone = (partsDone * 100) / parts;
  
    System.out.print(percentageString(partsDone, parts)
      + progressLine(percentDone) + status(partsDone, parts) + maxTime(partsDone, parts, times));
    System.out.println();
  }

  public static String progressLine(int percent) {
    String progLine = "[";
    if (percent == 0) {
      for (int i = 1; i <= 100; i++) {
        progLine += " ";
      }
    } else if (percent == 100) {
      for (int i = 1; i <=  percent; i++) {
        progLine += "=";
      }
    } else {
      for (int i = 1; i <  percent; i++) {
        progLine += "=";
      }
      progLine += (char) 187; 
      for (int i = percent + 1; i <= 100; i++) {
        progLine += " ";
      }
    }
    progLine += "] ";  
    return progLine;
  }

  public static String status(int partsDone, int parts) {
    return parts > 9 ? (partsDone < 10 ? " " + partsDone + " / " + parts + " ": partsDone + " / " + parts + " ") : partsDone + " / " + parts + " "; 
  }

  public static int randomTimeFromArgs(int[] arr) {
    return arr[new Random().nextInt(arr.length)];
  }

  public static String  maxTime(int parts,int partsDone, int[] times) {
    int max = Arrays.stream(times)
            .max()
            .getAsInt();
System.out.println(max);
    String eta = " ETA: " + String.valueOf((parts - partsDone) * max);
    return eta;
  }

  public static String percentageString(int partsDone, int parts) {
    int percentDone = (partsDone * 100) / parts;
    return percentDone < 10 ? "  " + percentDone + "% " : (percentDone < 100 ? " " + percentDone + "% " : percentDone + "% ");
  }

  public static void cls() {
    System.out.print("\033[H\033[2J");  
    System.out.flush();
  }
}
