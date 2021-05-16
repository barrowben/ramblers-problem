
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

    for (int i = 0; i<10; i++) {
        RamblersSearch rSearcher = new RamblersSearch(tMap, i , i); // value = 150
        SearchState initState = new RamblersState(23, 0, 0, 0);
        System.out.println(initState);

        String res_bb = rSearcher.runSearch(initState, "branchAndBound");
        System.out.println(res_bb);

        String fileName = "output_" + i + ".txt";

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
