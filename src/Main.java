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

    public void settings(){
        size(NUM_COLUMNS * CELL_SIZE, NUM_ROWS * CELL_SIZE);
        pixelDensity(1);
        int test = 1;
    }

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
    }

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
    }

    public void mouseClicked(){
        int col = mouseX / CELL_SIZE;
        int row = mouseY / CELL_SIZE;

        if(row >= 0 && row < NUM_ROWS && col >= 0 && col < NUM_COLUMNS){
            c[row][col].handleClick();

    }

    public void evolve() {
        for (int r = 1; r < NUM_ROWS - 1; r++) {
            for (int col = 1; col < NUM_COLUMNS - 1; col++) {
                c[r][col].evolve();
            }
        }
    }
}