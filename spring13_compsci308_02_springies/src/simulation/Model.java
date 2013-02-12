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
 * The Model Class
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
     * @param canvas canvas of this model
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
     * @param pen pen to draw
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
     * 
     * @param amnt amount of changing for each time
     */
    public void changeBounds (double amnt) {
        myChangeBounds.height += amnt;
        myChangeBounds.width += amnt;
    }

    private Dimension sumDimensions (Dimension dO, Dimension dT) {
        return new Dimension(dO.width + dT.width, dO.height + dT.height);
    }

    /**
     * Update simulation for this moment, given the time since the last moment.
     * 
     * @param elapsedTime update time
     */
    public void update (double elapsedTime) {
        Dimension bounds = sumDimensions(myChangeBounds, myView.getSize());
        myController.update();

        myEnvironment.getCenterofMass().calculateCenterOfMass(myEntities);

        for (ISimulationEntity entity : myEntities) {
            entity.update(elapsedTime, bounds);
        }
    }

    /**
     * getter for the list of springs and masses and their subclasses
     * 
     * @return
     */
    public List<ISimulationEntity> getEntities () {
        return myEntities;
    }

    /**
     * add a element into entities
     * 
     * @param entity objects we want to add
     */
    public void add (ISimulationEntity entity) {
        myEntities.add(entity);
    }

    /**
     * set the environment to certain one
     * 
     * @param ep a certain environment
     */
    public void setEnvironment (EnvironmentProperties ep) {
        myEnvironment = ep;
        Mass.setOurEnvironment(ep);
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
        ModelFactory factory;
        factory = new ModelFactory();
        factory.load(this, myView.selectFile("Select Model Data"));
    }

    /**
     * Prompt the user to load an Environment file.
     */
    public void loadEnvironment () {
        EnvironmentFactory ef = new EnvironmentFactory();
        ef.load(myView.selectFile("Select Environment File"));
        setEnvironment(ef.getEnvironment());
    }

    /**
     * @return the Canvas
     */
    public Canvas getView () {
        return myView;
    }

    /**
     * find the nearest mass point to the certain point where mouse click at
     * 
     * @param p a certain point which is the location of mouse
     * @return
     */
    public Mass findNearest (Point p) {
        Mass closest = null;
        double minD = Double.MAX_VALUE;
        for (ISimulationEntity s : myEntities) {
            if (s instanceof Mass) {
                if (!(s instanceof FixedMass)) {
                    Mass cur = (Mass) s;
                    double distance = Point2D.distance(p.getX(), p.getY(), cur.getX(), cur.getY());
                    System.out.println(distance);
                    if (distance < minD) {
                        closest = cur;
                        minD = distance;
                    }
                }
            }
        }
        return closest;
    }

}
