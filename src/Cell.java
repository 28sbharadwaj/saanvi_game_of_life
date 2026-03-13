public class Cell {

    private final int x;
    private final int y;
    private final int size;
    private final int row;
    private final int column;
    private CellState cellState;
    private MooreRules rules;


    public Cell(int x, int y, int size, int row, int column){

        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    public void applyRules(Cell[][] cells){
        int liveNeighbors = countLiveNeighbors(cells);

        CellState nextState = rules.applyRules(cellState, liveNeighbors);
        cellState = nextState;

    public void evolve(){}

    public void display(){
        if(cellState == CellState.ALIVE || cellState == CellState.WILL_DIE){
            Main.app.fill(0);
        } else {
            Main.app.fill(255);
        }
        Main.app.stroke(0);

        Main.app.rect(x, y, size, size);
    }

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


