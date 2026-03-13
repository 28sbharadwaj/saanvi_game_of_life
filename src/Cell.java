public class Cell {

    private final int x;
    private final int y;
    private final int size;
    private final int row;
    private final int column;
    private CellState cellState;
    private MooreRules rules;


    public Cell(int x, int y, int size, int row, int column, CellState cellState, MooreRules rules) {

        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    /**
     * figures out what the cell should do next based on neighbors
     * if the cell is already in a temporary state it does nothing
     * otherwise it counts live neighbors and uses rules to set will_die or will_revive
     */
    public void applyRules(Cell[][] cells){
        if(cellState != CellState.ALIVE && cellState != CellState.DEAD){
            return;
        }


        int liveNeighbors = countLiveNeighbors(cells);
        cellState = rules.applyRules(cellState, liveNeighbors);
    }


    /**
     * updates the cell from temporary state to final state
     * will_die becomes dead and will_revive becomes alive
     * basically moves the cell to its next generation
     */
    public void evolve() {
        if (cellState == CellState.WILL_DIE) {
            cellState = CellState.DEAD;
        } else if (cellState == CellState.WILL_REVIVE) {
            cellState = CellState.ALIVE;
        }
    }

    /**
     * shows the cell on the canvas
     * black if alive or will_die white if dead or will_survive
     * draws a rectangle at the cell's x and y with its size
     */
    public void display(){
        if(cellState == CellState.ALIVE || cellState == CellState.WILL_DIE){
            Main.app.fill(0);
        } else {
            Main.app.fill(255);
        }
        Main.app.stroke(0);

        Main.app.rect(x, y, size, size);
    }


    /**
     * toggles the cell between alive and dead when you click it
     * if it's alive or will_die it becomes dead
     * if it's dead or will_revive it becomes alive
     */
    public void handleClick(){
        if(cellState == CellState.ALIVE || cellState == CellState.WILL_DIE){
            cellState = CellState.DEAD;
        } else {
            cellState = CellState.ALIVE;
        }
    }

    private int countLiveNeighbors(Cell[][] cells){
        int count = 0;
        int rows = cells.length;
        int columns = cells[0].length;

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int r = row + dr;
                int c = column + dc;
                if (r >= 0 && r < rows && c >= 0 && c < columns) {
                    CellState neighborState = cells[r][c].cellState;
                    if (neighborState == CellState.ALIVE || neighborState == CellState.WILL_DIE) {
                        count++;
                    }
                }
            }

        }
        return count;
    }

}


