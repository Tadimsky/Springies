package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import physics.EnvironmentProperties;
import view.Canvas;
import controller.Controller;
import factory.EnvironmentFactory;
import factory.ModelFactory;


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

    private Controller myController;

    private Dimension myChangeBounds;

    /**
     * Create a game of the given size with the given display for its shapes.
     * 
     * @param canvas
     */
    public Model (Canvas canvas) {
        myView = canvas;
        myEntities = new ArrayList<ISimulationEntity>();
        myController = new Controller(this);
        myChangeBounds = new Dimension();
    }

    /**
     * Draw all elements of the simulation.
     * 
     * @param pen
     */
    public void paint (Graphics2D pen) {

        for (ISimulationEntity entity : myEntities) {
            entity.paint(pen);
        }

        if (myEnvironment != null) {
            myEnvironment.getCenterofMass().draw(pen);
        }
    }

    /**
     * change the dimension of boundaries by add/minus a certain value
     * @param amnt
     */
    public void changeBounds (double amnt)
    {
        myChangeBounds.height += amnt;
        myChangeBounds.width += amnt;
    }

    private Dimension sumDimensions (Dimension dO, Dimension dT)
    {
        return new Dimension(dO.width + dT.width, dO.height + dT.height);
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     */
    public void update (double elapsedTime) {
        Dimension bounds = sumDimensions(myChangeBounds, myView.getSize());
        myController.update();

        myEnvironment.getCenterofMass().calculateCenterOfMass(myEntities);

        for (ISimulationEntity entity : myEntities)
        {
            entity.update(elapsedTime, bounds);
        }
    }
    
    public List<ISimulationEntity> getEntities ()
    {
        return myEntities;
    }

    public void add (ISimulationEntity entity)
    {
        myEntities.add(entity);
    }

    public void setEnvironment (EnvironmentProperties ep)
    {
        myEnvironment = ep;
        Mass.Environment = ep;
    }

    /**
     * @return the Environment
     */
    public EnvironmentProperties getEnvironment () {
        return myEnvironment;
    }

    /**
     * Prompt the user to load a Model file.
     */
    public void loadModel () {
        try {
            ModelFactory factory;
            factory = new ModelFactory();
            factory.load(this, myView.selectFile("Select Model Data"));
        }
        catch (Exception e) {
            // no model loaded
        }
    }

    /**
     * Prompt the user to load an Environment file.
     */
    public void loadEnvironment () {
        try
        {
            EnvironmentFactory ef = new EnvironmentFactory();
            ef.load(myView.selectFile("Select Environment File"));
            setEnvironment(ef.getEnvironment());
        }
        catch (Exception e)
        {
            // if error, just use blank environment properties
            setEnvironment(new EnvironmentProperties());
        }
    }

    /**
     * @return the Canvas
     */
    public Canvas getView () {
        return myView;
    }

    public Mass findNearest (Point p)
    {
        Mass closest = null;
        double minD = Double.MAX_VALUE;
        for (ISimulationEntity s : myEntities)
        {
            if (s instanceof Mass)
            {
                if (!(s instanceof FixedMass))
                {
                    Mass cur = (Mass) s;
                    double distance = Point2D.distance(p.getX(), p.getY(), cur.getX(), cur.getY());
                    System.out.println(distance);
                    if (distance < minD)
                    {
                        closest = cur;
                        minD = distance;
                    }
                }
            }
        }
        return closest;
    }

}
