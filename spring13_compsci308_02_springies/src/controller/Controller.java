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
 * 
 * @author Jonno
 * 
 */
public class Controller {

    private Model mySimulation;

    /**
     * 
     */
    public static final int BOUNDS_CHANGE = 10;

    private Map<Integer, KeyControl> myControlMap = new HashMap<Integer, KeyControl>();
    private MouseControl myMouseControl;

    public Controller (Model sim) {
        mySimulation = sim;
        registerToggles();
        registerCommands();
        myMouseControl = new MouseControl(mySimulation);
    }

    public void update ()
    {
        processKeys();
        processMouse();
    }

    private void processKeys ()
    {
        KeyControl tc = myControlMap.get(mySimulation.getView().getLastKeyPressed());
        if (tc != null)
        {
            tc.Activate();
        }
    }

    private void processMouse ()
    {
        if (mySimulation.getView().getLastMousePosition() == null)
        {
            myMouseControl.releaseMouse();
        }
        else
        {
            myMouseControl.clickMouse();
        }
        myMouseControl.moveMouse();
    }

    private void addControl (KeyControl control)
    {
        myControlMap.put(control.getKey(), control);
    }

    private void registerToggles ()
    {
        // Gravity
        addControl(new ToggleControl(KeyEvent.VK_G) {
            @Override
            public void Toggle ()
            {
                Gravity g = mySimulation.getEnvironment().getGravity();
                g.setMagnitude(doToggle(g.getMagnitude()));
            }
        });
        // Viscosity
        addControl(new ToggleControl(KeyEvent.VK_V) {
            @Override
            public void Toggle ()
            {
                Viscosity v = mySimulation.getEnvironment().getViscosity();
                v.setMagnitude(doToggle(v.getMagnitude()));
            }
        });
        // Center of Mass
        addControl(new ToggleControl(KeyEvent.VK_M) {
            @Override
            public void Toggle ()
            {
                CenterOfMass com = mySimulation.getEnvironment().getCenterofMass();
                com.setMagnitude(doToggle(com.getMagnitude()));
            }
        });

        // http://docs.oracle.com/javase/1.4.2/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_0
        for (int i = 1; i <= 4; i++)
        {
            // to pass in to function below
            final int j = i;
            addControl(new ToggleControl(i + 48) {
                @Override
                public void Toggle ()
                {
                    WallRepulsion wr = mySimulation.getEnvironment().getWall(j);
                    wr.setMagnitude(doToggle(wr.getMagnitude()));
                }
            });
        }
    }

    private void registerCommands ()
    {
        addControl(new CommandControl(KeyEvent.VK_N) {
            @Override
            public void execute ()
            {
                mySimulation.loadModel();
            }
        });

        addControl(new CommandControl(KeyEvent.VK_C) {
            @Override
            public void execute ()
            {
                mySimulation.getEntities().clear();
            }
        });

        addControl(new CommandControl(KeyEvent.VK_UP) {
            @Override
            public void execute ()
            {
                mySimulation.changeBounds(BOUNDS_CHANGE);
            }
        });

        addControl(new CommandControl(KeyEvent.VK_DOWN) {
            @Override
            public void execute ()
            {
                mySimulation.changeBounds(-BOUNDS_CHANGE);
            }
        });
    }
}
