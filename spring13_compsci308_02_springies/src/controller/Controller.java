package controller;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import physics.CenterOfMass;
import physics.Gravity;
import physics.Viscosity;
import physics.WallRepulsion;
import simulation.Model;

public class Controller{
    
    private Model mySimulation;
    
    private Map<Integer, Control> controlMap = new HashMap<Integer, Control>();
    
    public Controller (Model sim) 
    {
        mySimulation = sim;
        registerToggles();
        registerCommands();
    }
    
    public void processKeys()
    {
        Control tc = controlMap.get(mySimulation.getView().getLastKeyPressed());
        if (tc != null)
            tc.Activate();
    }
    
    private void addControl(Control control)
    {
        controlMap.put(control.getKey(), control);
    }
    
    private void registerToggles()    
    {        
        // Gravity
        addControl(new ToggleControl(KeyEvent.VK_G) {       
            @Override
            public void Toggle () {                
                 Gravity g = mySimulation.getEnvironment().getGravity(); 
                 g.setMagnitude(doToggle(g.getMagnitude()));
            }
        });
        // Viscosity
        addControl(new ToggleControl(KeyEvent.VK_V) {       
            @Override
            public void Toggle () {                
                 Viscosity v = mySimulation.getEnvironment().getViscosity(); 
                 v.setMagnitude(doToggle(v.getMagnitude()));
            }
        });
        // Center of Mass
        addControl(new ToggleControl(KeyEvent.VK_M) {       
            @Override
            public void Toggle () {                
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
                public void Toggle () {
                     WallRepulsion wr = mySimulation.getEnvironment().getWall(j);
                     wr.setMagnitude(doToggle(wr.getMagnitude()));
                }
            });
        }
    }
    
    private void registerCommands()
    {
        addControl(new CommandControl(KeyEvent.VK_N) {
            @Override
            public void Execute () {
                mySimulation.loadModel();
            }
        });
        
        addControl(new CommandControl(KeyEvent.VK_C) {
            @Override
            public void Execute () {
                mySimulation.getEntities().clear();
            }
        });
        
        addControl(new CommandControl(KeyEvent.VK_UP) {
            @Override
            public void Execute () {
                mySimulation.changeBounds(10);
            }
        });
        
        addControl(new CommandControl(KeyEvent.VK_DOWN) {
            @Override
            public void Execute () {
                mySimulation.changeBounds(-10);
            }
        });
    }
}
