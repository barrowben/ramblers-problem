
/**
  * RunMapSearch.java
  *
  *
  * Ben Barrow
  *
*/

import java.util.*;

public class RunRamblersBB {
  public static void main(String[] arg) {

    TerrainMap tMap = new TerrainMap("tmc.pgm");

    RamblersSearch rSearcher = new RamblersSearch(tMap, 4 , 4); // value = 70
    SearchState initState = new RamblersState(150, 0, 0, 0);

    String res_bb = rSearcher.runSearch(initState, "branchAndBound");
    System.out.println(res_bb);
    // String[] USACities = { "Albany", "Atlanta", "Augusta", "Austin", "Bismarck", "Boise", "Boston", "Chicago", "Dallas",
    //     "Denver", "Detroit", "Helena", "Indianapolis", "Jefferson City", "Las Vegas", "Los Angeles", "Memphis", "Miami",
    //     "New Orleans", "New York", "Oklahoma City", "Phoenix", "Pierre", "Salem", "Salt Lake City", "San Francisco",
    //     "Santa Fe", "Seattle", "Tallahassee", "Washington DC" };

    // System.out.println(mapUSA.toString());
    // System.out.println(mapUSA.getLinks("Start"));

    // for (String city: USACities) {
    //         MapSearch searcher = new MapSearch(mapUSA, city);
    //         SearchState initState = (SearchState) new MapState("Denver", 0, 0);

    //         String res_bb = searcher.runSearch(initState, "branchAndBound");
    //         // System.out.println("Start: Denver | Target: " + city);
    //         System.out.println(res_bb);
    // }
    // add your solution code here ....
  }
}
