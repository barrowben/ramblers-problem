
/**
  * RunRamblersAStar.java
  *
  * Main class for running branch-and-bound search
  * Once the search has been run, please check /.log for results (csv format)
  * A full solution path can be output by calling RunSearch instead of RunSearchE (line 58)
  *
  * Ben Barrow 2021
  *
*/

import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;

public class RunRamblersAStar {
  public static void main(String[] arg) {

    // Create PrintWriter object for output
    PrintWriter output = null;

    // Change the strategy here
    String strategy = "aStar";

    // Create TerrainMap object
    TerrainMap tMap = new TerrainMap("diablo.pgm");

    // Write the csv column headers for logging
    try {
        FileWriter file = new FileWriter(".log/" + strategy + "_output.csv", true);
        output = new PrintWriter(file, true);
        output.append("Start Y,Start X,Goal Y,Goal X,Efficiency,Execution Time");
        output.close();
    } catch (Exception e) {
        System.out.println(e.getStackTrace());
    }

    // Run the experiment 1000 times with random start and goal nodes
    for (int i = 0; i<100; i++) {     
        int maxX = tMap.getWidth();
        int maxY = tMap.getDepth();
        Random random = new Random();

        // Get start time to work out execution time when search complete
        long startTime = System.currentTimeMillis();

        // Randomise the start and goal nodes and get initial height
        int randomYOrigin = random.nextInt(maxY);
        int randomXOrigin = random.nextInt(maxX);
        int randomYGoal = random.nextInt(maxY);
        int randomXGoal = random.nextInt(maxX);
        int initialHeight = tMap.getHeight();

        // Start the search
        RamblersSearch rSearcher = new RamblersSearch(tMap, randomYGoal , randomXGoal);
        SearchState initState = new RamblersState(initialHeight, randomYOrigin, randomXOrigin, 0, 0);
        float res_bb = rSearcher.runSearchE(initState, strategy);
        long endTime = System.currentTimeMillis();
        System.out.print("Efficiency: ");
        System.out.println(res_bb);
        System.out.println("Elapsed time: " + (endTime - startTime) + " milliseconds");

        // Log the output to a textfile
        try {
            FileWriter file = new FileWriter(".log/" + strategy + "_output.csv", true);
            output = new PrintWriter(file, true);
            output.append("\n");
            output.append(randomYOrigin + ",");
            output.append(randomXOrigin + ",");
            output.append(randomYGoal + ",");
            output.append(randomXGoal + ",");
            output.append(String.valueOf(res_bb) + ",");
            output.append(String.valueOf(endTime - startTime));
            output.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
  }
}
