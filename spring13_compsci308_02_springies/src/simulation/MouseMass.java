package simulation;
import java.awt.Graphics2D;
/**
 * a class of invisible fixed mass point,
 * used when mouse is clicked
 * have same properties except for invisible
 * 
 * @author Jonno, Yang
 *
 */
public class MouseMass extends FixedMass {

    /**
     * constructors for mousemass
     * @param x x position for this mass point
     *
     * @param y y position for this mass point
     */
    public MouseMass (double x, double y) {
        super(-1, x, y, -Double.MIN_VALUE);       
    }
    
    @Override
    public void paint(Graphics2D pen) {
        // do nothing
    }

}
