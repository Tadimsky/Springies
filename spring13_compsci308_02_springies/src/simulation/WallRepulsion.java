package simulation;

import java.awt.Dimension;

import util.Sprite;
import util.Vector;

public class WallRepulsion extends CenterOfMass {
	public static final int TOP_WALL = 1;
	public static final int RIGHT_WALL = 2;
	public static final int BOTTOM_WALL = 3;
	public static final int LEFT_WALL = 4;
	
	private Vector myRepulsion;
	
	private int myWallID;
	
	public WallRepulsion(int wallID, double Magnitude, double Exponent) {
		super(Magnitude, Exponent);
		myWallID = wallID;
		createRepulsion();
	}
	
	private void createRepulsion()
	{
		double angle = 0;
		switch (myWallID)
		{
			case TOP_WALL: 	angle = Sprite.DOWN_DIRECTION;
							break;
			case LEFT_WALL: 	angle = Sprite.RIGHT_DIRECTION;
							break;
			case RIGHT_WALL: 	angle = Sprite.LEFT_DIRECTION;
							break;
			case BOTTOM_WALL: 	angle = Sprite.UP_DIRECTION;
							break;
		}		
		myRepulsion = new Vector(angle, getMagnitude());
	}
	
	public int getWall()
	{
		return myWallID;
	}
	
	public Vector getRepulsionForce(Mass m, double elapsedTime, Dimension bounds)
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
		//System.out.println(distance);
		Vector repel = new Vector(myRepulsion);		
		if (getExponent() == 2)
		{
			double scaleamount = 1 / Math.pow(distance / 100, 2);
			System.out.println(scaleamount);
			repel.scale(scaleamount);
		}
		return repel;		
	}
	
}
