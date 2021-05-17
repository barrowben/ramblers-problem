
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

    // Create PrintWrite obj for output
    PrintWriter output = null;

    // Change the strategy here
    String strategy = "branchAndBound";

    // Create TerrainMap obj
    TerrainMap tMap = new TerrainMap("tmc.pgm");

    // Write the csv column headers
    try {
        FileWriter file = new FileWriter(".log/" + strategy + "_output.csv", true);
        output = new PrintWriter(file, true);
        output.append("Start Y,Start X,Goal Y,Goal X,Efficiency");
        output.close();
    } catch (Exception e) {
        System.out.println(e.getStackTrace());
    }

    // Run the experiment 100 times with random start and goal nodes
    for (int i = 0; i<100; i++) {     
        int max = tMap.getWidth(); // assuming the pgm is square
        Random random = new Random();

        //randomise the start and goal nodes and get initial height
        int randomYOrigin = random.nextInt(max);
        int randomXOrigin = random.nextInt(max);
        int randomYGoal = random.nextInt(max);
        int randomXGoal = random.nextInt(max);
        int initialHeight = tMap.getHeight();

        // Start the search
        RamblersSearch rSearcher = new RamblersSearch(tMap, randomYGoal , randomXGoal);
        SearchState initState = new RamblersState(initialHeight, randomYOrigin, randomXOrigin, 0);
        float res_bb = rSearcher.runSearchE(initState, strategy);
        System.out.println(res_bb);

        // Log the output to a textfile
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
            System.out.println(e.getStackTrace());
        }
    }
  }
}
