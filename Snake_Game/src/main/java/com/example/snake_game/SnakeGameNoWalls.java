package com.example.snake_game;

import Direction.Direction;
import Fruit.Fruit;
import Snake.Snake;
import SnakeBackground.SnakeBackground;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SnakeGameNoWalls extends Application implements Runnable {
    private SnakeBackground background;
    private Snake snake;
    private Fruit fruit;
    private GraphicsContext gc;
    private boolean gameOver;
    private Direction currentDirection = Direction.DOWN;
    private int score = 0;
    private boolean gamePaused = false;
    private static int cycleCount = Animation.INDEFINITE;


    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Snake");
        background = new SnakeBackground();
        Group root = new Group();
        Canvas canvas = new Canvas(background.getWidth(), background.getHeight());
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(false);   //false??
        stage.show();
        gc = canvas.getGraphicsContext2D();
        Timeline timeLine = new Timeline(new KeyFrame(Duration.millis(130), e -> {
            try {
                run(gc);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }),new KeyFrame(Duration.millis(5)));

        timeLine.setCycleCount(cycleCount);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (!currentDirection.equals(Direction.LEFT)) {
                        currentDirection = Direction.RIGHT;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (!currentDirection.equals(Direction.RIGHT)) {
                        currentDirection = Direction.LEFT;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (!currentDirection.equals(Direction.DOWN)) {
                        currentDirection = Direction.UP;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (!currentDirection.equals(Direction.UP)) {
                        currentDirection = Direction.DOWN;
                    }

                } else if (code == KeyCode.SPACE) {
                    pauseGame(timeLine);
                } else if (gameOver && code == KeyCode.ENTER) {
                    try {
                        restart();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (code == KeyCode.ESCAPE) {
                    stage.close();
                    gameOver = false;
                }
            }
        });
        snake = new Snake();
        fruit= new Fruit();
        snake.initiateSnakeBody();

        start:
        while (true){
            fruit.produceFruit();
            for (Point snake : snake.getSnakeBody()){
                if (snake.getX()==fruit.getFruitX() && snake.getY()==fruit.getFruitY()){
                    continue start;
                }

            }
            break;
        }

        timeLine.play();


    }

    public void run(GraphicsContext gc) throws IOException {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", background.getWidth() / 3.5, background.getHeight() / 2);
            return;
        }
        background.drawBackground(gc);
        fruit.drawFruit(gc);
        snake.drawSnake(gc);





        drawScore();
        for (int i = snake.getSnakeBody().size() - 1; i >= 1; i--) {
            snake.getSnakeBody().get(i).x = snake.getSnakeBody().get(i - 1).x;
            snake.getSnakeBody().get(i).y = snake.getSnakeBody().get(i - 1).y;
        }


        switch (currentDirection) {
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;

        }
        gameOver();
        eatFruit();
        if (gameOver) {
            run(gc);
        }
        if (snake.getSnakeHead().x < 0) {
            snake.getSnakeHead().x = (int) (background.getColumns() - 1);
        }
        if (snake.getSnakeHead().x > background.getColumns() - 1) {
            snake.getSnakeHead().x = 0;
        }
        if (snake.getSnakeHead().y < 0) {
            snake.getSnakeHead().y = (int) (background.getRows() - 1);
        }
        if (snake.getSnakeHead().y > background.getRows() - 1) {
            snake.getSnakeHead().y = 0;
        }



    }










    public void moveRight() {
        snake.getSnakeHead().x++;
    }

    public void moveLeft() {
        snake.getSnakeHead().x--;
    }

    public void moveUp() {
        snake.getSnakeHead().y--;
    }

    public void moveDown() {
        snake.getSnakeHead().y++;
    }

    public void gameOver() {
        Point snakeHead = snake.getSnakeHead();
        for (int i = 1; i < snake.getSnakeBody().size(); i++) {
            if (snakeHead.x == snake.getSnakeBody().get(i).getX() && snakeHead.getY() == snake.getSnakeBody().get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }

    public void eatFruit() throws IOException {
        if (snake.getSnakeHead().getX() == fruit.getFruitX() && snake.getSnakeHead().getY() == fruit.getFruitY()) {
            snake.getSnakeBody().add(new Point(-1, -1));
            start:
            while (true){
                fruit.produceFruit();
                for (Point snake : snake.getSnakeBody()){
                    if (snake.getX()==fruit.getFruitX() && snake.getY()==fruit.getFruitY()){
                        continue start;
                    }

                }
                break;
            }
            score += 5;
        }
    }

    public void drawScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + score, 10, 35);

    }


    public void restart() throws FileNotFoundException {
        gameOver = false;
        currentDirection = Direction.RIGHT;
        snake.getSnakeBody().clear();
        for (int i = 0; i < 5; i++) {
            snake.getSnakeBody().add(new Point(4, (int) (background.getRows() / 2)));
        }
        fruit.produceFruit();
        snake.setSnakeHead(snake.getSnakeBody().get(0));
        cycleCount += 1;
    }

    public void pauseGame(Timeline timeline) {
        if (!gamePaused) {
            timeline.pause();
            gamePaused = true;
        } else {
            timeline.play();
            gamePaused = false;
        }
    }

    public static void main(String[] args) {

        launch();
        // JSONObject obj= new JSONObject();


    }

    @Override
    public void run() {
        Stage stage = new Stage();
        try {
            if (gameOver) {
                gameOver = false;
                currentDirection = Direction.RIGHT;
                snake.getSnakeBody().clear();
                cycleCount += 1;
            }
            start(stage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

