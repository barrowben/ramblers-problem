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
    public RamblersState(int lHeight, int y, int x, int lc) {
        this.localHeight = lHeight;
        this.yCoord = y;
        this.xCoord = x;
        this.localCost = lc;
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
        TerrainMap tMap = rSearcher.getTmap();
        int maxX = tMap.getWidth();
        int maxY = tMap.getHeight();

        ArrayList<SearchState> succs = new ArrayList<SearchState>();

        // If Y Coordinate isn't 0, there is a successor tile to the north
        if (yCoord != 0) {
            int neighbourHeight = tMap[yCoord-1][xCoord];
            int cost = (neighbourHeight > localHeight) ? (1 + neighbourHeight - localHeight) : 1;
            succs.add((SearchState) new RamblersState(neighbourHeight, yCoord - 1, xCoord, cost));
        }

        // If Y Coordinate isn't the max, there is a successor tile to the south
        if(yCoord < maxY) {
            int neighbourHeight = tMap[yCoord+1][xCoord];
            int cost = (neighbourHeight > localHeight) ? (1 + neighbourHeight - localHeight) : 1;
            succs.add((SearchState) new RamblersState(neighbourHeight, yCoord + 1, xCoord, cost));
        }

        // If X Coordinate isn't 0, there is a successor tile to the east
        if(xCoord != 0) {
            int neighbourHeight = tMap[yCoord][xCoord+1];
            int cost = (neighbourHeight > localHeight) ? (1 + neighbourHeight - localHeight) : 1;
            succs.add((SearchState) new RamblersState(neighbourHeight, yCoord, xCoord + 1, cost));
        }

        // If X Coordinate isn't the max, there is a successor tile to the west
        if(xCoord < maxX) {
            int neighbourHeight = tMap[yCoord][xCoord-1];
            int cost = (neighbourHeight > localHeight) ? (1 + neighbourHeight - localHeight) : 1;
            succs.add((SearchState) new RamblersState(neighbourHeight, yCoord, xCoord - 1, cost));
        }

        return succs;
    }
    
    // sameState
    public boolean sameState(SearchState s2) {
        RamblerSState rs2 = (RamblersState) s2;
        return (this.getYCoord() == rs2.getYCoord() && this.getXCoord() == rs2.getXCoord());
    }

    // toString
    public String toString() {
        return ("Y: " + yCoord + "\n"
        + "X: " + xCoord + "\n"
        + "Height: " + localHeight);
    }
}
