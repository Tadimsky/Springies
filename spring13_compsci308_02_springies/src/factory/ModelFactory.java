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


public class ModelFactory extends Factory {

    Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();

    public ModelFactory () throws NoSuchMethodException, SecurityException {
        registerCreation("mass", new IFactoryCreation() {
            public Object createItem (Scanner s) throws Exception {
                Mass mass = Mass.createEntity(s);
                myMasses.put(mass.getId(), mass);
                return mass;
            }
        });

        registerCreation("spring", new IFactoryCreation() {
            public Object createItem (Scanner s) throws Exception {
                return Spring.createEntity(s, myMasses);
            }
        });

        registerCreation("muscle", new IFactoryCreation() {
            public Object createItem (Scanner s) throws Exception {
                return Muscle.createEntity(s, myMasses);
            }
        });
    }

    @Override
    public void load (Model model, File modelFile)
    {
        List<Object> objects = super.loadFile(modelFile);
        for (Object o : objects)
        {
            model.add((ISimulationEntity) o);
        }
    }

}
