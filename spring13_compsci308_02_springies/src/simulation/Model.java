package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import view.Canvas;


/**
 * XXX.
 * 
 * @author Robert C. Duvall
 */
public class Model {
    // bounds and input for game
    private Canvas myView;
    // simulation state
    private List<ISimulationEntity> myEntities;
    
    private EnvironmentProperties myEnvironment;

    /**
     * Create a game of the given size with the given display for its shapes.
     */
    public Model (Canvas canvas) {
        myView = canvas;    
        
        myEntities = new ArrayList<ISimulationEntity>();
    }

    /**
     * Draw all elements of the simulation.
     */
    public void paint (Graphics2D pen) {
        
        
        for (ISimulationEntity entity : myEntities)
        {
            entity.paint(pen);
        }
        
        if (myEnvironment != null)
        	myEnvironment.getCenterofMass().draw(pen);
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     */
    public void update (double elapsedTime) {
    	myEnvironment.getCenterofMass().calculateCenterOfMass(myEntities);    	
        Dimension bounds = myView.getSize(); 
        
        for (ISimulationEntity entity : myEntities)
        {
            entity.update(elapsedTime, bounds);
        }
        
    }
    
    public void add(ISimulationEntity entity)
    {
        myEntities.add(entity);
    }
    
    public void setEnvironment(EnvironmentProperties ep)
    {
    	myEnvironment = ep;
    	Mass.myEnvironment = ep;
    }
}
