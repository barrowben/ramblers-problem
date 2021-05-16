
/**
  * RunMapSearch.java
  *
  *
  * Ben Barrow
  *
*/

import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;

public class RunRamblersBB {
  public static void main(String[] arg) {

    PrintWriter output = null;
    String strategy = "branchAndBound";

    TerrainMap tMap = new TerrainMap("tmc.pgm");

      try {
          FileWriter file = new FileWriter(".log/" + strategy + "_output.csv", true);
          output = new PrintWriter(file, true);
          output.append("Start Y,Start X,Goal Y,Goal X,Efficiency");
          output.close();
      } catch (Exception e) {
          e.getStackTrace();
      }

    // Run the experiment 5 times with random start and goal nodes
    for (int i = 0; i<5; i++) {     
        int min = 0;
        int max = tMap.getWidth();
        Random random = new Random();
        int randomYOrigin = random.nextInt(max + min);
        int randomXOrigin = random.nextInt(max + min);
        int randomYGoal = random.nextInt(max + min);
        int randomXGoal = random.nextInt(max + min);
        int initialHeight = tMap.getHeight();

        RamblersSearch rSearcher = new RamblersSearch(tMap, randomYGoal , randomXGoal);

        SearchState initState = new RamblersState(initialHeight, randomYOrigin, randomXOrigin, 0);
        // System.out.println(initState);

        float res_bb = rSearcher.runSearchE(initState, strategy);
        System.out.println(res_bb);

        // String fileName = ".log/output_" + randomYOrigin + "-" + randomXOrigin  + "_" + randomYGoal  + "-" + randomXGoal + ".csv";

        // Log the console output to a textfile
        try {
            FileWriter file = new FileWriter(".log/" + strategy + "_output.csv", true);
            output = new PrintWriter(file, true);
            output.append("\n");
            output.append(randomYOrigin + ",");
            output.append(randomXOrigin + ",");
            output.append(randomYGoal + ",");
            output.append(randomXGoal + ",");
            output.append(String.valueOf(res_bb));
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
  }
}
