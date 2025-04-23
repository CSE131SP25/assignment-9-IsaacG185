package assignment9;

import java.util.LinkedList;

public class Snake {

    public static final double SEGMENT_SIZE = 0.02;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;

    public Snake() {
        segments = new LinkedList<>();
        // Start in the center of the screen
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
        deltaX = 0;
        deltaY = 0;
    }

    public void changeDirection(int direction) {
        if(direction == 1) { //up
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2) { //down
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3) { //left
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4) { //right
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
    }

    public void move() {
        BodySegment head = segments.getFirst();
        double newX = head.getX() + deltaX;
        double newY = head.getY() + deltaY;
        BodySegment newHead = new BodySegment(newX, newY, SEGMENT_SIZE);
        segments.addFirst(newHead);
        segments.removeLast(); // remove the tail segment
    }

    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }

    public BodySegment getHead() {
        return segments.getFirst();
    }

    public boolean eatFood(Food f) {
        // Check if snake's head is on the food
        BodySegment head = segments.getFirst();
        double distance = Math.sqrt(Math.pow(head.getX() - f.getX(), 2) + Math.pow(head.getY() - f.getY(), 2));
        if (distance < SEGMENT_SIZE + Food.FOOD_SIZE) {
            segments.addFirst(new BodySegment(head.getX(), head.getY(), SEGMENT_SIZE)); // Grow snake
            return true;
        }
        return false;
    }

    // Method to eat the power-up and apply its effect
    public boolean eatPowerUp(PowerUp p) {
        if (p.isEatenBy(this)) {
            int effect = p.getEffect();
            if (effect > 0) {
                for (int i = 0; i < 2; i++) {
                    BodySegment tail = segments.getLast();
                    segments.addLast(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE)); // Grow snake by 2
                }
                System.out.println("Nice! Snake grown by 2x");
            } else {
                for (int i = 0; i < 2 && segments.size() > 1; i++) {
                    segments.removeLast(); // Shrink snake by 2
                }
                System.out.println("Oh no! Snake shrunk by 2x");
            }
            return true;
        }
        return false;
    }

    public boolean isInbounds() {
        BodySegment head = segments.getFirst();
        return head.getX() >= 0 && head.getX() <= 1 && head.getY() >= 0 && head.getY() <= 1;
    }
}
