package physics;

import java.awt.Dimension;
import java.util.Scanner;
import simulation.Mass;
import util.Sprite;
import util.Vector;

/**
 * the class of wallRepulsion,default direction is from wall to mass
 * every wall can have its repulsion force
 * can be turned on and off
 * 
 * @author Jonno , Yang
 *
 */
public class WallRepulsion extends Force {
    /**
     * top wall ID =1
     */
    public static final int TOP_WALL = 1;
    /**
     * right wall ID 2
     */
    public static final int RIGHT_WALL = 2;
    /**
     * bottom wall ID 3
     */
    public static final int BOTTOM_WALL = 3;
    /**
     * left wall ID 4
     */
    public static final int LEFT_WALL = 4;

    private double myAngle = 0;

    private int myWallID;

    /**
     * constructor for wall repulsion
     * @param wallID the ID of walls, follow the ID listed above
     * @param magnitude magnitude of the wall repulsion force, propotional
     *        to the force
     * @param exponent for 0.0 a constant, for 2.0 propotion to one over the square distance
     */
    public WallRepulsion (int wallID, double magnitude, double exponent) {
        super(magnitude, exponent);
        myWallID = wallID;
        myAngle = getAngle();
    }

    /**
     * get the angle between mass point and a certain wall
     * @return
     */
    private double getAngle () {
        switch (myWallID) {
            case TOP_WALL:
                myAngle = Sprite.DOWN_DIRECTION;
                break;
            case LEFT_WALL:
                myAngle = Sprite.RIGHT_DIRECTION;
                break;
            case RIGHT_WALL:
                myAngle = Sprite.LEFT_DIRECTION;
                break;
            case BOTTOM_WALL:
                myAngle = Sprite.UP_DIRECTION;
                break;
            default:
                break;
        }
        return myAngle;
    }

    /**
     * get distance between mass point and a certain wall
     * @param m mass point we want to apply force on
     * @param bounds boundaries we thought as walls
     * @return
     */
    private double getDistance (Mass m, Dimension bounds) {
        double distance = 0;
        switch (myWallID) {
            case TOP_WALL:
                distance = m.getY();
                break;
            case LEFT_WALL:
                distance = m.getX();
                break;
            case RIGHT_WALL:
                distance = bounds.getWidth() - m.getX();
                break;
            case BOTTOM_WALL:
                distance = bounds.getHeight() - m.getY();
                break;
            default:
                break;
        }
        return distance;
    }

    /**
     * get wall ID
     * @return
     */
    public int getWall () {
        return myWallID;
    }

    /**
     * get the wall repulsion force based on mass point and 
     * their distance from walls
     * 
     * @param m mass point we want to apply force on
     * @param bounds boundaries we used to calculate the force
     *        when it is not a constant
     * @return
     */
    public Vector getForce (Mass m, Dimension bounds) {
        double distance = getDistance(m, bounds);
        return super.getForce(myAngle, distance);
    }
 
    /**
     * construct wall repulsion by using data obtained from scanner
     * 
     * @param s scanner we used to obtain data
     * @return
     */
    public static WallRepulsion createEntity (Scanner s) {
        int wallid = s.nextInt();
        double magnitude = s.nextDouble();
        double exponent = s.nextDouble();
        return new WallRepulsion(wallid, magnitude, exponent);
    }
}
