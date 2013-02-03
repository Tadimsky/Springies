package factory;

import java.io.File;
import java.util.Scanner;

public class EnvironmentFactory extends Factory {

    private static final String GRAVITY_KEYWORD = "gravity";
    private static final String VISCOSITY_KEYWORD = "viscosity";
    private static final String CENTEROFMASS_KEYWORD = "centerofmass";
    private static final String WALLREPULSION_KEYWORD = "wall";
    
    public EnvironmentFactory () {
        registerCreation("gravity", new IFactoryCreation() {
            public Object createItem (Scanner s) throws Exception {
                return null;
            }
        });
        
        registerCreation("gravity", new IFactoryCreation() {
            public Object createItem (Scanner s) throws Exception {
                return null;
            }
        });
        registerCreation("gravity", creation);
        registerCreation("gravity", creation);
    }
    
    public void loadEnvironment(File modelFile)
    {
        
    }

}
