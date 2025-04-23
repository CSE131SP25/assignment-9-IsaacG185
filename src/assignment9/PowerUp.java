package assignment9;

import java.awt.Color;
import edu.princeton.cs.introcs.StdDraw;

public class PowerUp {

    public static final double SIZE = 0.02;
    private double x, y;
    
    public PowerUp() {
        // Randomly generate position within the game bounds
        this.x = Math.random() * 0.9 + 0.05; // Between 0.05 and 0.95
        this.y = Math.random() * 0.9 + 0.05; // Between 0.05 and 0.95
    }

    public void draw() {
        StdDraw.setPenColor(new Color(128, 0, 128)); // Purple color
        StdDraw.filledCircle(x, y, SIZE); // Draw the power-up as a circle
    }
    
    // Randomly decide the power-up effect
    public int getEffect() {
        return Math.random() < 0.5 ? 2 : -2; // 50% chance to grow (+2) or shrink (-2)
    }
    
    // fix if the snake head is colliding with the power-up
    public boolean isEatenBy(Snake snake) {
        BodySegment head = snake.getHead();
        double distance = Math.sqrt(Math.pow(head.getX() - x, 2) + Math.pow(head.getY() - y, 2));
        return distance < Snake.SEGMENT_SIZE + SIZE; // Check if snake head collides with power-up
    }
}
