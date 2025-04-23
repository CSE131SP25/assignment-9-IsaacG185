package assignment9;

import java.awt.event.KeyEvent;
import edu.princeton.cs.introcs.StdDraw;

public class Game {

    private Snake snake;
    private Food food;
    private PowerUp powerUp;

    public Game() {
        StdDraw.enableDoubleBuffering();
        snake = new Snake();
        food = new Food();
        powerUp = new PowerUp(); // Create the power-up
    }

    public void play() {
        while (true) { 
            int dir = getKeypress();
            if (dir != -1) {
                snake.changeDirection(dir);
            }

            snake.move(); // Move the snake

            // Check if the snake eats food
            if (snake.eatFood(food)) {
                food = new Food(); // Create a new food item after it's eaten
            }

            // Check if the snake eats the power-up
            if (snake.eatPowerUp(powerUp)) {
                powerUp = new PowerUp(); // Create a new power-up after it's eaten
            }

            // Check if snake is inbounds
            if (!snake.isInbounds()) {
                System.out.println("Game Over! The snake hit the edge.");
                break; // End game
            }

            updateDrawing(); // Redraw everything
            StdDraw.pause(50); // Pause for 50ms
        }
    }

    private int getKeypress() {
        if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            return 1;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            return 2;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            return 3;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            return 4;
        } else {
            return -1;
        }
    }

    private void updateDrawing() {
        StdDraw.clear();
        snake.draw(); // Draw the snake
        food.draw(); // Draw the food
        powerUp.draw(); // Draw the power-up
        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}
