package simulation;

import java.awt.Graphics2D;

public class InvisibleFixedMass extends FixedMass {

	public InvisibleFixedMass(int id, double x, double y, double mass) {
		super(id, x, y, mass);
		
	}
    @Override
	public void paint(Graphics2D pen){
		// do nothing and override the method
	}
}
