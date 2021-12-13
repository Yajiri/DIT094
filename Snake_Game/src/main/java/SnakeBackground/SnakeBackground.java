package SnakeBackground;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeBackground {
    private static final int WIDTH =800;
    private static final int HEIGHT =WIDTH;
    private static final int ROWS=20;
    private static final int COLUMNS =ROWS;
    private static final int SQUARE_SIZE= WIDTH /ROWS;
    private GraphicsContext gc;
    private Canvas canvas;

    public SnakeBackground(){
        this.canvas = new Canvas();
        this.gc = canvas.getGraphicsContext2D();

    }

    public void drawBackground(GraphicsContext gc) {

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }


    public double getWidth(){
        return WIDTH;
    }
    public double getHeight(){
        return HEIGHT;
    }
    public double getRows(){
        return ROWS;
    }
    public double getColumns(){
        return COLUMNS;
    }
    public double getSquareSize(){return SQUARE_SIZE;}
}

