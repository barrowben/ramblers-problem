/*
*	State in a map search
*	Ben Barrow 2021
*/

import java.util.*;

public class RamblersState extends SearchState {

    // height of terrain & coordinates
    private int localHeight;
    private int yCoord;  
    private int xCoord;

    // constructor
    public RamblersState(int lHeight, int y, int x, int elc, int erm) {
        this.localHeight = lHeight;
        this.yCoord = y;
        this.xCoord = x;
        this.localCost = elc;
        this.estRemCost = erm;
    }

    // accessor
    public int getHeight() {
        return localHeight;
    }

    public int getYCoord() {
        return yCoord;
    }

    public int getXCoord() {
        return xCoord;
    }

// goalPredicate - needs to know info about current search instance e.g. the height of the current tile
    public boolean goalPredicate(Search rSearcher) {
        RamblersSearch rSearch = (RamblersSearch) rSearcher;

        // Get the goalState
        int goalY = rSearch.getGoalY();
        int goalX = rSearch.getGoalX();

        // Check if thisState is same as goalState
        return (yCoord == goalY && xCoord == goalX);
    }

    // getSuccessors
    public ArrayList<SearchState> getSuccessors(Search searcher) {
        RamblersSearch rSearcher = (RamblersSearch) searcher;
        TerrainMap terrainMap = rSearcher.getTMap();
        int maxX = terrainMap.getWidth();
        int maxY = terrainMap.getDepth();
        int[][] terrainMapArray = terrainMap.getTmap();
        
        // Get the average height of the map
        int sum = 0;
        int count = 0;
        double averageHeight = 0.0;
        for (int i=0;i<maxX;i++) {
            for (int j=0;j<maxY;j++) {
                sum += terrainMapArray[j][i];
                count++;
            }
            averageHeight = sum/count;
        }

        // Get goal coordinates
        int goalY = rSearcher.getGoalY();
        int goalX = rSearcher.getGoalX();
        int goalHeight = terrainMapArray[goalY][goalX];

        ArrayList<SearchState> succs = new ArrayList<SearchState>();

        // If Y Coordinate isn't 0, there is a successor node to the north
        if (yCoord != 0) {
            int neighbourHeight = terrainMapArray[yCoord-1][xCoord];
            int cost = (neighbourHeight > localHeight) ? (1 + neighbourHeight - localHeight) : 1;
            // A* : Euclidean Distance
            // estRemCost = (int) Math.sqrt((Math.pow((goalY - yCoord-1), 2) + Math.pow((goalX - xCoord), 2)));

            // A* : Manhattan Distance
            int estRemCost = (int) (Math.abs(goalY - yCoord-1) + Math.abs(goalX - xCoord));

            // // Height diff between local and goal 
            estRemCost += Math.abs(neighbourHeight - goalHeight);

            // Height diff between average height and neighbourHeight
            // If the neighbourHeight is lower than averageHeight, estRemCost will be reduced
            // If neightbourHeight is higher than averageHeight estRemCost will increase
            // estRemCost += (int) (averageHeight - neighbourHeight);

            succs.add((SearchState) new RamblersState(neighbourHeight, yCoord - 1, xCoord, cost, estRemCost));
        }

        // If Y Coordinate isn't the max, there is a successor node to the south
        if(yCoord < maxY - 1) {
            int neighbourHeight = terrainMapArray[yCoord+1][xCoord];
            int cost = (neighbourHeight > localHeight) ? (1 + neighbourHeight - localHeight) : 1;
            // A* : Euclidean Distance
            // int estRemCost = (int) Math.sqrt((Math.pow((goalY - yCoord+1), 2) + Math.pow((goalX - xCoord), 2)));

            // // A* : Manhattan Distance
            int estRemCost = (int) (Math.abs(goalY - yCoord+1) + Math.abs(goalX - xCoord));

            // // Height diff between local and goal 
            estRemCost += Math.abs(neighbourHeight - goalHeight);

            // Height diff between average height and neighbourHeight
            // If the neighbourHeight is lower than averageHeight, estRemCost will be reduced
            // If neightbourHeight is higher than averageHeight estRemCost will increase
            // estRemCost += (int) (averageHeight - neighbourHeight);

            succs.add((SearchState) new RamblersState(neighbourHeight, yCoord + 1, xCoord, cost, estRemCost));
        }

        // If X Coordinate isn't 0, there is a successor node to the west
        if(xCoord != 0) {
            int neighbourHeight = terrainMapArray[yCoord][xCoord-1];
            int cost = (neighbourHeight > localHeight) ? (1 + neighbourHeight - localHeight) : 1;
            // A* : Euclidean Distance
            // int estRemCost = (int) Math.sqrt((Math.pow((goalY - yCoord), 2) + Math.pow((goalX - xCoord-1), 2)));

            // A* : Manhattan Distance
            int estRemCost = (int) (Math.abs(goalY - yCoord) + Math.abs(goalX - xCoord-1));

            // // Height diff between local and goal 
            estRemCost += Math.abs(neighbourHeight - goalHeight);

            // Height diff between average height and neighbourHeight
            // If the neighbourHeight is lower than averageHeight, estRemCost will be reduced
            // If neightbourHeight is higher than averageHeight estRemCost will increase
            // estRemCost += (int) (averageHeight - neighbourHeight);

            succs.add((SearchState) new RamblersState(neighbourHeight, yCoord, xCoord - 1, cost, estRemCost));
        }

        // If X Coordinate isn't the max, there is a successor node to the east
        if(xCoord < maxX - 1) {
            int neighbourHeight = terrainMapArray[yCoord][xCoord+1];
            int cost = (neighbourHeight > localHeight) ? (1 + neighbourHeight - localHeight) : 1;
            // A* : Euclidean Distance
            // int estRemCost = (int) Math.sqrt((Math.pow((goalY - yCoord), 2) + Math.pow((goalX - xCoord+1), 2)));

            // A* : Manhattan Distance
            int estRemCost = (int) (Math.abs(goalY - yCoord) + Math.abs(goalX - xCoord+1));

            // // Height diff between local and goal (use in addition to Manhatten or Euclidean)
            estRemCost += Math.abs(neighbourHeight - goalHeight);

            // Height diff between average height and neighbourHeight
            // If the neighbourHeight is lower than averageHeight, estRemCost will be reduced
            // If neightbourHeight is higher than averageHeight estRemCost will increase
            // estRemCost += (int) (averageHeight - neighbourHeight);

            succs.add((SearchState) new RamblersState(neighbourHeight, yCoord, xCoord + 1, cost, estRemCost));
        }

        return succs;
    }
    
    // sameState
    public boolean sameState(SearchState s2) {
        RamblersState rs2 = (RamblersState) s2;
        return (this.getYCoord() == rs2.getYCoord() && this.getXCoord() == rs2.getXCoord());
    }

    // toString
    public String toString() {
        return ("Y: " + yCoord + "\n"
        + "X: " + xCoord + "\n"
        + "Height: " + localHeight);
    }
}
