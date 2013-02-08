package simulation;
import java.awt.Graphics2D;

public class MouseMass extends FixedMass {

    public MouseMass (double x, double y) {
        super(-1, x, y, -Double.MIN_VALUE);       
    }
    
    @Override
    public void paint(Graphics2D pen)
    {
        // do nothing
    }

}
