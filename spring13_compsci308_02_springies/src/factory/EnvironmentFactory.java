package factory;

import java.io.File;
import java.util.Scanner;
import physics.CenterOfMass;
import physics.EnvironmentProperties;
import physics.Gravity;
import physics.Viscosity;
import physics.WallRepulsion;
import simulation.Model;

public class EnvironmentFactory extends Factory {

    private EnvironmentProperties ep;
    
    public EnvironmentFactory () {
        ep = new EnvironmentProperties();
        
        registerCreation("gravity", new IFactoryCreation() {
            public Object createItem (Scanner s) throws Exception {
                Gravity g = Gravity.createEntity(s);
                ep.setGravity(g);
                return g;                
            }
        });
        
        registerCreation("viscosity", new IFactoryCreation() {
            public Object createItem (Scanner s) throws Exception {
                Viscosity v = Viscosity.createEntity(s);
                ep.setViscosity(v);
                return v;
            }
        });
        registerCreation("centermass", new IFactoryCreation() {
            public Object createItem (Scanner s) throws Exception {
                CenterOfMass com = CenterOfMass.createEntity(s);
                ep.setCenterOfMass(com);
                return com;
            }
        });
        registerCreation("wall", new IFactoryCreation() {
            public Object createItem (Scanner s) throws Exception {
                WallRepulsion wr = WallRepulsion.createEntity(s);
                ep.addWallRepulsion(wr);
                return wr;
            }
        });
    }
    
    public void load(File modelFile)
    {    
        load(null, modelFile);
    }
    
    public EnvironmentProperties getEnvironment()
    {
        return ep;
    }

    @Override
    public void load (Model model, File file) {
        super.loadFile(file);
    }

}
