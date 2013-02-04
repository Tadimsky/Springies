package physics;

import java.awt.Dimension;
import java.util.Scanner;

import simulation.Mass;
import util.Sprite;
import util.Vector;

public class WallRepulsion extends Force {
    public static final int TOP_WALL = 1;
    public static final int RIGHT_WALL = 2;
    public static final int BOTTOM_WALL = 3;
    public static final int LEFT_WALL = 4;

    private double myAngle = 0;

    private int myWallID;

    public WallRepulsion(int wallID, double Magnitude, double Exponent) {
        super(Magnitude, Exponent);
        myWallID = wallID;		
        myAngle = getAngle();
    }

    private double getAngle()
    {		
        switch (myWallID)
        {
            case TOP_WALL: 	myAngle = Sprite.DOWN_DIRECTION;
            break;
            case LEFT_WALL: 	myAngle = Sprite.RIGHT_DIRECTION;
            break;
            case RIGHT_WALL: 	myAngle = Sprite.LEFT_DIRECTION;
            break;
            case BOTTOM_WALL: 	myAngle = Sprite.UP_DIRECTION;
            break;
        }
        return myAngle;
    }

    private double getDistance(Mass m, Dimension bounds)
    {        
        double distance = 0;
        switch (myWallID)
        {
            case TOP_WALL: 	distance = m.getY();
            break;
            case LEFT_WALL: 	distance = m.getX();
            break;
            case RIGHT_WALL: 	distance = bounds.getWidth() - m.getX();
            break;
            case BOTTOM_WALL: 	distance = bounds.getHeight() - m.getY();
            break;
        }	
        return distance;
    }

    public int getWall()
    {
        return myWallID;
    }

    public Vector getForce(Mass m, Dimension bounds)
    {
        double distance = getDistance(m, bounds);
        return super.getForce(myAngle, distance);				
    }

    public static WallRepulsion createEntity(Scanner s)
    {
        int wallid = s.nextInt();
        double magnitude = s.nextDouble();
        double exponent = s.nextDouble();
        return new WallRepulsion(wallid, magnitude, exponent);
    }
}
