
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

    TerrainMap tMap = new TerrainMap("tmc.pgm");

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
        System.out.println(initState);

        String res_bb = rSearcher.runSearch(initState, "branchAndBound");
        System.out.println(res_bb);

        String fileName = ".log/output_" + randomYOrigin + "-" + randomXOrigin  + "_" + randomYGoal  + "-" + randomXGoal + ".txt";

        // Log the console output to a textfile
        try {
            FileWriter file = new FileWriter(fileName);
            PrintWriter output = new PrintWriter(file, true);
            output.append("=================");
            output.append("START EXPERIMENT");
            output.append("=================");
            output.append("\n");
            output.append(res_bb);
            output.append("\n");
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
  }
}
