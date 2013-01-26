package simulation;

public class WallRepulsion extends CenterOfMass {
	public static final int TOP_WALL = 1;
	public static final int RIGHT_WALL = 2;
	public static final int BOTTOM_WALL = 3;
	public static final int LEFT_WALL = 4;
	
	private int myWallID;
	
	public WallRepulsion(int wallID, double Magnitude, double Exponent) {
		super(Magnitude, Exponent);
		myWallID = wallID;
	}
	
	public int getWall()
	{
		return myWallID;
	}

}
