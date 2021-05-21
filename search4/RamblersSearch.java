/*
* 	RamblersSearch.java
*
*   Ben Barrow 2021
*/

import java.util.*;

public class RamblersSearch extends Search {

  private TerrainMap tMap; // map we're searching
  private int goalY; // goal y coordinate
  private int goalX; // goal x coordinate 

  // accessors
  public TerrainMap getTMap() {
    return tMap;
  }

  public int getGoalY() {
    return goalY;
  }

  public int getGoalX() {
    return goalX;
  }

  // constructor
  public RamblersSearch(TerrainMap t, int y, int x) {
    this.tMap = t;
    this.goalY = y;
    this.goalX = x;
  }
}
