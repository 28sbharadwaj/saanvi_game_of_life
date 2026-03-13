import processing.core.PApplet;

public class Main extends PApplet{
    public static PApplet app;
    final int NUM_ROWS = 50;
    final int NUM_COLUMNS = 100;
    final int CELL_SIZE = 10;
    private boolean doEvolve = false;

    private Cell[][] c;

    public static void main(String[] args){
        PApplet.main("Main");

    }
/**
 * public void settings sets up the canvas size and pixel density for the canvas
 * the width and height are determined by the variables NUM_COLUMNS, NUM_ROWS, and CELL_SIZE
 * these represent the number of rows, columns and size of each cell
 **/
    public void settings() {
        size(NUM_COLUMNS * CELL_SIZE, NUM_ROWS * CELL_SIZE);
        pixelDensity(1);
        int test = 1;
    }

    /** public void setup: this initializes the canvas and the game grid
     * it also sets up the canvas reference and creates the cells array
     * it initializes all cells as dead, and adds multiple gliders
     * the gliders are in different directions at positions relative to the grid size.
     */

    public void setup(){
        app = this;
        c = new Cell[NUM_ROWS][NUM_COLUMNS];
        for (int r = NUM_ROWS - 1; r >= 0; r--) {
            int col;
            for (col = 0; col < NUM_COLUMNS; col++) {

                int x = col * CELL_SIZE;
                int y = r * CELL_SIZE;

                c[r][col] = new Cell(x, y, CELL_SIZE, r, col, CellState.DEAD, rules);
            }
        }
        addGlider(NUM_ROWS - NUM_ROWS + 1, NUM_COLUMNS - NUM_COLUMNS + 1);
        addGlider(NUM_ROWS / 10, NUM_COLUMNS / 10);
        addGlider(NUM_ROWS / 5, NUM_COLUMNS / 3);
        addGlider(NUM_ROWS * 7 / 10, NUM_COLUMNS * 1 / 2);
        addGlider(NUM_ROWS / 10, NUM_COLUMNS / 5);
        addGlider(NUM_ROWS / 3, NUM_COLUMNS / 4);

        addGliderGoingLeft(NUM_ROWS / 50, NUM_COLUMNS - 10);
        addGliderGoingLeft(NUM_ROWS / 25, NUM_COLUMNS - 20);
        addGliderGoingLeft(NUM_ROWS / 2, NUM_COLUMNS / 3);
        addGliderGoingLeft(NUM_ROWS * 4 / 5, NUM_COLUMNS - 20);
        addGliderGoingLeft(NUM_ROWS * 9 / 10, NUM_COLUMNS - 30);



        addGliderUpRight(NUM_ROWS / 2, NUM_COLUMNS / 3);
        addGliderUpRight(NUM_ROWS * 9 / 10, NUM_COLUMNS / 50);

        addGliderUpLeft(NUM_ROWS * 7 / 10, NUM_COLUMNS / 2);
        addGliderUpLeft(NUM_ROWS * 4 / 5, NUM_COLUMNS / 10);
        addGliderUpLeft(NUM_ROWS / 2, NUM_COLUMNS / 3);

    }

    /** main draw loop for the sketch
     * if doEvolve is true it updates the rules and evolves the middle cells
     * then it always applies rules evolves and shows all cells
     * this way when you click a cell it changes color immediately
     */


    public void draw(){
        background(255);

        if(doEvolve){
            for(int r = 1; r < NUM_ROWS - 1; r++){
                for(int col = 1; col < NUM_COLUMNS - 1; col++){
                    c[r][col].applyRules(c);
                }
            }

            evolve();
        }

        for(int r = 1; r < NUM_ROWS - 1; r++){
            for(int col = 1; col < NUM_COLUMNS - 1; col++){
                c[r][col].applyRules(c);
            }
        }

        for(int r = 0; r < NUM_ROWS; r++){
            for(int col = 0; col < NUM_COLUMNS; col++){
                c[r][col].evolve();
                c[r][col].display();
            }
        }
    }

    /**
     * called when the mouse is clicked
     * figures out which cell you clicked based on mouseX and mouseY
     * also tells that cell to toggle between alive and dead
     */

    public void mouseClicked(){
        int col = mouseX / CELL_SIZE;
        int row = mouseY / CELL_SIZE;

        if(row >= 0 && row < NUM_ROWS && col >= 0 && col < NUM_COLUMNS){
            c[row][col].handleClick();
        }
    }

    public void evolve() {
        for (int r = 1; r < NUM_ROWS - 1; r++) {
            for (int col = 1; col < NUM_COLUMNS - 1; col++) {
                c[r][col].evolve();
            }
        }
    }

    public void keyPressed(){
        doEvolve = !doEvolve;
    }

    public void addGlider(int startRow, int startCol) {
        c[startRow + 0][startCol + 1].cellState = CellState.ALIVE;
        c[startRow + 1][startCol + 2].cellState = CellState.ALIVE;
        c[startRow + 2][startCol + 0].cellState = CellState.ALIVE;
        c[startRow + 2][startCol + 1].cellState = CellState.ALIVE;
        c[startRow + 2][startCol + 2].cellState = CellState.ALIVE;
    }

    public void addGliderGoingLeft(int row, int col) {
        c[row + 0][col + 1].cellState = CellState.ALIVE;
        c[row + 1][col + 0].cellState = CellState.ALIVE;
        c[row + 1][col + 1].cellState = CellState.ALIVE;
        c[row + 1][col + 2].cellState = CellState.ALIVE;
        c[row + 2][col + 0].cellState = CellState.ALIVE;
    }

    public void addGliderUpRight(int row, int col) {
        c[row][col].cellState = CellState.ALIVE;
        c[row][col + 1].cellState = CellState.ALIVE;
        c[row][col + 2].cellState = CellState.ALIVE;
        c[row + 1][col + 2].cellState = CellState.ALIVE;
        c[row + 2][col + 1].cellState = CellState.ALIVE;
    }

    public void addGliderUpLeft(int row, int col) {
        c[row][col + 1].cellState = CellState.ALIVE;
        c[row + 1][col].cellState = CellState.ALIVE;
        c[row + 1][col + 1].cellState = CellState.ALIVE;
        c[row + 1][col + 2].cellState = CellState.ALIVE;
        c[row + 2][col + 2].cellState = CellState.ALIVE;
    }

}