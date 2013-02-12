package factory;

import java.io.File;
import java.util.Scanner;
import physics.CenterOfMass;
import physics.EnvironmentProperties;
import physics.Gravity;
import physics.Viscosity;
import physics.WallRepulsion;
import simulation.Model;


/**
 * The Environment Factory creates all the environment forces entities in the simulation.
 * 
 * @author Jonathan Schmidt, Yang Yang
 * 
 */
public class EnvironmentFactory extends Factory {

    private EnvironmentProperties myEnvironment;

    /**
     * Creates an instance of the Environment Factory.
     * Registers all the different types of environment properties that can be read in.
     */
    public EnvironmentFactory () {
        myEnvironment = new EnvironmentProperties();

        registerCreation("gravity", new IFactoryCreator() {
            public Object createItem (Scanner s) {
                Gravity g = Gravity.createEntity(s);
                myEnvironment.setGravity(g);
                return g;
            }
        });

        registerCreation("viscosity", new IFactoryCreator() {
            public Object createItem (Scanner s) {
                Viscosity v = Viscosity.createEntity(s);
                myEnvironment.setViscosity(v);
                return v;
            }
        });
        registerCreation("centermass", new IFactoryCreator() {
            public Object createItem (Scanner s) {
                CenterOfMass com = CenterOfMass.createEntity(s);
                myEnvironment.setCenterOfMass(com);
                return com;
            }
        });
        registerCreation("wall", new IFactoryCreator() {
            public Object createItem (Scanner s) {
                WallRepulsion wr = WallRepulsion.createEntity(s);
                myEnvironment.addWallRepulsion(wr);
                return wr;
            }
        });
    }

    /**
     * Loads the information from the specified file.
     * Does not require the simulation to be passed in.
     * 
     * @param modelFile The File to be read
     */
    public void load (File modelFile) {
        load(null, modelFile);
    }

    /**
     * Returns the Environment Properties file that was created.
     * 
     * @return The Environment Properties file
     */
    public EnvironmentProperties getEnvironment () {
        return myEnvironment;
    }

    @Override
    public void load (Model model, File file) {
        super.loadFile(file);
    }

}
