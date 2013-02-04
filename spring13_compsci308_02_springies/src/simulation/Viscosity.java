package simulation;

import java.util.Scanner;

import util.Vector;

public class Viscosity extends Vector {
      private double myScale;
      
      
      
      public Viscosity(double scale){
    	  myScale = scale;
    	  
      }

      public Viscosity(){
    	  myScale = 0.0;
    	  
      }
      
      
      public static Viscosity viscosityCommand(Scanner line){
  		double scale = line.nextDouble();
  		return new Viscosity(scale);
  	 
      }


      public Vector getForce(Mass m){
    	  Vector visco = new Vector(m.getVelocity());
    	  visco.scale(myScale);
    	  visco.negate();
    	  return visco;
      }
      
      public void applyViscosity(Mass m){
    	  m.applyForce(getForce(m));
      }
}
