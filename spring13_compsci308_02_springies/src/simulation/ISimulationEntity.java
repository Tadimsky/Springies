package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * 
 * @author Richard Yang, Jonno Schmidt
 *
 */
public interface ISimulationEntity {
    /**
     * update the current status
     * @param elapsedTime
     * @param bounds
     */
    public abstract void update (double elapsedTime, Dimension bounds);

    /**
     * paint stuff on canvas
     * @param draw
     */
    public abstract void paint (Graphics2D draw);
}
