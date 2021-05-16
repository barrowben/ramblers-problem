/*
* 	RamblersSearch.java
*/

import java.util.*;

public class RamblersSearch extends Search {

  private TerrainMap tMap; // map we're searching
  private int goalY; // goal y coordinate
  private int goalX; // goal x coordinate 

  public TerrainMap getTMap() {
    return tMap;
  }

  public int getGoalY() {
    return goalY;
  }

  public int getGoalX() {
    return goalX;
  }

  public RamblersSearch(TerrainMap t, int y, int x) {
    this.tMap = t;
    this.goalY = y;
    this.goalX = x;
  }
}
