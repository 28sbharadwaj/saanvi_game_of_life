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
        this.cellState = CellState.DEAD;
        this.fillColor = 255;
    }

    public void applyRules(Cell[][] cells){}

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
        if(cellState == CellState.ALIVE){
            cellState = CellState.DEAD;
        } else {
            cellState = CellState.ALIVE;
        }
    }

    private int countLiveNeighbors(Cell[][] cells){}
}


