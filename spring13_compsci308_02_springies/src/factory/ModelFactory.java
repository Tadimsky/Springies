package factory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import simulation.ISimulationEntity;
import simulation.Mass;
import simulation.Model;
import simulation.Muscle;
import simulation.Spring;


/**
 * The Model Factory creates all the physical entities in the simulation.
 * 
 * @author Jonathan Schmidt, Yang Yang
 * 
 */
public class ModelFactory extends Factory {

    /**
     * Stores a map of all the masses that have been read in so that
     * the springs can be linked to them.
     */
    private Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();

    /**
     * Creates an instance of the Model Factory.
     * Registers all the different creations of the factory
     */
    public ModelFactory () {
        registerCreation("mass", new IFactoryCreator() {
            public Mass createItem (Scanner s) {
                Mass mass = Mass.createEntity(s);
                myMasses.put(mass.getId(), mass);
                return mass;
            }
        });

        registerCreation("spring", new IFactoryCreator() {
            public Spring createItem (Scanner s) {
                return Spring.createEntity(s, myMasses);
            }
        });

        registerCreation("muscle", new IFactoryCreator() {
            public Muscle createItem (Scanner s) {
                return Muscle.createEntity(s, myMasses);
            }
        });
    }

    @Override
    public void load (Model model, File modelFile) {
        List<Object> objects = super.loadFile(modelFile);
        for (Object o : objects) {
            model.add((ISimulationEntity) o);
        }
    }

}
