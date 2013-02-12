package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;


/**
 * an interface that has update and paint method, which
 * will cover all the five types of objects in this system
 * including mass, fixed mass, mousemss, springies, muscles
 * 
 * @author Richard Yang, Jonno Schmidt
 * 
 */
public interface ISimulationEntity {
    /**
     * update the current status
     * 
     * @param elapsedTime time unit for updating the frame
     * @param bounds boundaries of this system
     */
    void update (double elapsedTime, Dimension bounds);

    /**
     * paint stuff on canvas
     * 
     * @param pen pen that draw on canvas
     */
    void paint (Graphics2D pen);
}
