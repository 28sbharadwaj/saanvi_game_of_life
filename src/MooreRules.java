public class MooreRules {
    private int[] birthNeighbors;
    private int[] survivalNeighbors;

    /**
     * makes a new moore rules object
     * birthNeighbors is the list of neighbor counts that make a dead cell come alive
     * survivalNeighbors is the list of neighbor counts that let an alive cell stay alive
     */

    public MooreRules(int[] birthNeighbors, int[] survivalNeighbors){
        this.birthNeighbors = birthNeighbors;
        this.survivalNeighbors = survivalNeighbors;
    }

    /**
     * checks if a dead cell should come to life
     * looks at how many live neighbors it has
     * if the number matches any in birthNeighbors it returns true
     * otherwise returns false
     */
    public boolean shouldBeBorn(int liveNeighbors){
        for (int n : birthNeighbors){
            if (liveNeighbors == n) {
                return true;
            }
        }
        return false;
    }


    /**
     * checks if an alive cell should stay alive
     * looks at how many live neighbors it has
     * if the number matches any in survivalNeighbors it returns true
     * otherwise it returns false
     */
    public boolean shouldSurvive(int liveNeighbors){
        for (int n : survivalNeighbors){
            if (liveNeighbors == n) return true;
        }
        return false;
    }

    /**
     * figures out what the next state of a cell should be
     * if the cell is dead it checks if it should revive
     * if the cell is alive it checks if it should die
     * returns the new state or keeps the same state if nothing changes
     */
    public CellState applyRules (CellState cellState, int liveNeighbors){
        switch(cellState){
            case DEAD:
                if(shouldBeBorn(liveNeighbors)) return CellState.WILL_REVIVE;
                break;
            case ALIVE:
                if(!shouldSurvive(liveNeighbors)) return CellState.WILL_DIE;
                break;
            default:
                break;
        }
        return cellState;
    }

}
