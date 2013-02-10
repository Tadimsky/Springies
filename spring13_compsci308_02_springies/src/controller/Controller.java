package Controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import physics.CenterOfMass;
import physics.Gravity;
import physics.Viscosity;
import physics.WallRepulsion;

import simulation.Model;

public class Controller {

	private Model mySimulation;
	
	private Map<Integer,KeyControl> myControls = new HashMap<Integer, KeyControl>();
	private MouseControl myMouseControl ;
	

    public Controller(Model model){
    	mySimulation = model;
        registerCommands();
        registerToggles();
        myMouseControl = new MouseControl(model);
    }

    public void addControls(KeyControl con){
    	myControls.put(con.getKey(), con);
    	
    } 

    public void processCommands(){
    	processKeyCommands();
    	processMouseCommands();
    }
    
    public void processKeyCommands(){
    	KeyControl keyCommand = myControls.get(mySimulation.getView().getLastKeyPressed());
    	if(keyCommand != null){
    		keyCommand.activate();
    	}
    }
    public void processMouseCommands(){
    	if(mySimulation.getView().getLastMousePosition() != null){
    		myMouseControl.mousePressed();
    	}else{
    		myMouseControl.mouseReleased();
    	}
    }
    
    public void registerCommands(){
    	
    	addControls(new CommandControl(KeyEvent.VK_N){
    		@Override
    		public void  execute(){
    			mySimulation.loadModel();
    		}
    	});
    
        addControls(new CommandControl(KeyEvent.VK_C){
        	@Override
        	public void execute(){
        		mySimulation.getEntities().clear();
        	}
        });
   
    }

    public void registerToggles(){
    	addControls(new ToggleControl(KeyEvent.VK_G){
    		@Override
    		public void Toggle(){
    		   Gravity gra = mySimulation.getEnvironment().getGravity();
    		   gra.setMagnitude(doToggle(gra.getMagnitude()));
    		}
    	});
    	
    	addControls(new ToggleControl(KeyEvent.VK_V){
    		@Override
    		public void Toggle(){
    		   Viscosity vis = mySimulation.getEnvironment().getViscosity();
    		   vis.setMagnitude(doToggle(vis.getMagnitude()));
    		}
    	});
    	
    	addControls(new ToggleControl(KeyEvent.VK_M){
    		@Override
    		public void Toggle(){
    			CenterOfMass cen = mySimulation.getEnvironment().getCenterofMass();
    			cen.setMagnitude(doToggle(cen.getMagnitude()));
    		}
    	});
    	for(int i=1 ;i<5;i++){
    		final int j = i;
    		addControls(new ToggleControl(i+48){
    			@Override
    			public void Toggle(){
    			   WallRepulsion wal = mySimulation.getEnvironment().getWall(j);
    			   wal.setMagnitude(doToggle(wal.getMagnitude()));
    			}
    		});
    	}
    	
    	
    	
    	
    	
    	
    	
    }















}
