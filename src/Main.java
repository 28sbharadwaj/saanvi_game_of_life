import processing.core.PApplet;

public class Main extends PApplet{
    public static PApplet app;
    final int NUM_ROWS = 50;
    final int NUM_COLUMNS = 100;
    final int CELL_SIZE = 10;

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
        for(int r = 0; r < NUM_ROWS; r++){
            for(int col = 0; col < NUM_COLUMNS; col++){

                int x = col * CELL_SIZE;
                int y = r * CELL_SIZE;

    }

    public void draw(){

    }

}