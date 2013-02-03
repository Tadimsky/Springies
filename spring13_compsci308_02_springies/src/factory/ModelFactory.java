package factory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import simulation.ISimulationEntity;
import simulation.Mass;
import simulation.Model;
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
                return Spring.createEntity(s, myMasses);                         
            }
        });
    }
    
    public void loadModel(Model model, File modelFile)
    {
        List<Object> objects = super.loadModel(modelFile);
        for (Object o : objects)
        {
            model.add((ISimulationEntity)o);
        }
    }

}
