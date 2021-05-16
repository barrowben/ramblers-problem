
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

    RamblersSearch rSearcher = new RamblersSearch(tMap, 13 , 11); // value = 150
    SearchState initState = new RamblersState(150, 0, 0, 0);
    System.out.println(initState);

    String res_bb = rSearcher.runSearch(initState, "branchAndBound");
    System.out.println(res_bb);

    // Log the console output to a textfile
    try {
        FileWriter file = new FileWriter("output.txt");
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
