public class Cell {

    private final int x;
    private final int y;
    private final int size;
    private final int row;
    private final int column;
    private final CellState cellState;
    private final MooreRules rules;

    public Cell(int x, int y, int size, int row, int column){

        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    public void applyRules(Cell[][] cells){}

    public void evolve(){}

    public void display(){}

    public void handleClick(){}

    private int countLiveNeighbors(Cell[][] cells){}
}


