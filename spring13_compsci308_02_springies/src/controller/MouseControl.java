package Controller;

import simulation.InvisibleFixedMass;
import simulation.Mass;
import simulation.Model;
import simulation.Spring;
import util.Location;


public class MouseControl {

	private Location myLastLocation;
    private Model mySimulation;
    private boolean isPressed;
    
    private InvisibleFixedMass newMass;
    private Spring newSpring;
    private Mass nearest;
    private double distance;
    
    public MouseControl(Model model){
    	myLastLocation = new Location();
    	isPressed = false;
    	mySimulation = model;
    }
    
  
    public void mousePressed(){
    	myLastLocation = new Location(mySimulation.getView().getLastMousePosition());
    	
    	if(!isPressed){
    		isPressed = true;
    		nearest = mySimulation.findNearesMass(myLastLocation);
    		distance = myLastLocation.distance(nearest.getX(), nearest.getY());
    		newMass = new InvisibleFixedMass(Integer.MAX_VALUE,myLastLocation.getX(),myLastLocation.getY(),Double.MIN_NORMAL);    
    	}else{
            mySimulation.remove(newMass);
            mySimulation.remove(newSpring);
    		newMass.setCenter(myLastLocation);
    	}
    	newSpring = new Spring(nearest,newMass,distance,Spring.DEFAULT_K);
    	mySimulation.add(newMass);
	    mySimulation.add(newSpring);
    }
	public void mouseReleased(){
		if(isPressed){
			isPressed = false;
			if(newMass != null && newSpring != null){
			    mySimulation.remove(newMass);
				mySimulation.remove(newSpring);
				newMass = null;
		        newSpring = null;
			}
		    
		}
	}
}
