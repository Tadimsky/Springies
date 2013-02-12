package controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import physics.CenterOfMass;
import physics.Gravity;
import physics.Viscosity;
import physics.WallRepulsion;
import simulation.Model;


/**
 * Controller manages the actual controlling of the simulation.
 * Stores a list of all the controls that are active in the program
 * and processes each one when the key is pressed.
 * 
 * @author Jonathan Schmidt, Yang Yang
 * 
 */
public class Controller {

    /**
     * The amount that the bounds of the simulation will change by when
     * the Up or Down button is pressed.
     */
    public static final int BOUNDS_CHANGE = 10;

    /**
     * The Number of Walls that can exist
     */
    private static final int NUM_WALLS = 4;

    /**
     * The Key Code for the 0 button.
     */
    private static final int KEY_ZERO_OFFSET = 48;

    private Model mySimulation;

    private Map<Integer, KeyControl> myControlMap = new HashMap<Integer, KeyControl>();
    private MouseControl myMouseControl;

    /**
     * Creates an instance of the controller.
     * Registers the different controls that are active in the controller.
     * 
     * @param sim The simulation that will be controlled using this controller.
     */
    public Controller (Model sim) {
        mySimulation = sim;
        registerToggles();
        registerCommands();
        myMouseControl = new MouseControl(mySimulation);
    }

    /**
     * Updates the Controller every frame that the simulation updates
     */
    public void update () {
        processKeys();
        processMouse();
    }

    /**
     * Processes all the keys that have been pressed and calls the corresponding control.
     */
    private void processKeys () {
        KeyControl tc = myControlMap.get(mySimulation.getView().getLastKeyPressed());
        if (tc != null) {
            tc.activate();
        }
    }

    /**
     * Processes the movement of the mouse and calls the mouse controller.
     */
    private void processMouse () {
        if (mySimulation.getView().getLastMousePosition() == null) {
            myMouseControl.releaseMouse();
        }
        else {
            myMouseControl.clickMouse();
        }
        myMouseControl.moveMouse();
    }

    /**
     * Adds a Key Control in the Control Map.
     * Uses the key value of the Key Control as the key in the map.
     * 
     * @param control
     */
    private void addControl (KeyControl control) {
        myControlMap.put(control.getKey(), control);
    }

    /**
     * Registers all the Toggle Controls for this controller.
     * If more Toggle Controls want to be added, just add it in here.
     */
    private void registerToggles () {
        // Gravity
        addControl(new ToggleControl(KeyEvent.VK_G) {
            @Override
            public void toggle () {
                Gravity g = mySimulation.getEnvironment().getGravity();
                g.setMagnitude(doToggle(g.getMagnitude()));
            }
        });
        // Viscosity
        addControl(new ToggleControl(KeyEvent.VK_V) {
            @Override
            public void toggle () {
                Viscosity v = mySimulation.getEnvironment().getViscosity();
                v.setMagnitude(doToggle(v.getMagnitude()));
            }
        });
        // Center of Mass
        addControl(new ToggleControl(KeyEvent.VK_M) {
            @Override
            public void toggle () {
                CenterOfMass com = mySimulation.getEnvironment().getCenterofMass();
                com.setMagnitude(doToggle(com.getMagnitude()));
            }
        });
        for (int i = 1; i <= NUM_WALLS; i++) {
            // to pass in to function below
            final int WALL_ID = i;
            addControl(new ToggleControl(i + KEY_ZERO_OFFSET) {
                @Override
                public void toggle () {
                    WallRepulsion wr = mySimulation.getEnvironment().getWall(WALL_ID);
                    wr.setMagnitude(doToggle(wr.getMagnitude()));
                }
            });
        }
    }

    /**
     * Registers all the Command Controls for this controller.
     * If more Command Controls want to be added, just add it in here.
     */
    private void registerCommands () {
        addControl(new CommandControl(KeyEvent.VK_N) {
            @Override
            public void execute () {
                mySimulation.loadModel();
            }
        });

        addControl(new CommandControl(KeyEvent.VK_C) {
            @Override
            public void execute () {
                mySimulation.getEntities().clear();
            }
        });

        addControl(new CommandControl(KeyEvent.VK_UP) {
            @Override
            public void execute () {
                mySimulation.changeBounds(BOUNDS_CHANGE);
            }
        });

        addControl(new CommandControl(KeyEvent.VK_DOWN) {
            @Override
            public void execute () {
                mySimulation.changeBounds(-BOUNDS_CHANGE);
            }
        });
    }
}
