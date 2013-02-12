package factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import simulation.Model;


/**
 * The abstract Factory class that loads in files and turns them into creations.
 * Register a keyword and a class that will handle the creation of the object for
 * the factory to know how to create it.
 * 
 * @author Jonathan Schmidt, Yang Yang
 */
public abstract class Factory {

    /**
     * The map that stores keywords and the class that has the method to create the item
     */
    private Map<String, IFactoryCreator> myCreationMap = new HashMap<String, IFactoryCreator>();

    /**
     * Loads the specified file and hands off each line to the correct class so that an
     * instance of the object can be created.
     * 
     * @param modelFile The file that contains the information that will be created
     * @return List of items that were created.
     */
    protected List<Object> loadFile (File modelFile) {
        try {
            ArrayList<Object> objects = new ArrayList<Object>();

            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    IFactoryCreator create = myCreationMap.get(type);
                    if (create != null) {
                        objects.add(create.createItem(line));
                    }
                }
            }
            input.close();
            return objects;
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Contains the code for creation of the objects for the concrete factory subclasses
     * 
     * @param model The simulation that the items will be put in
     * @param file The file that is read in
     */
    public abstract void load (Model model, File file);

    /**
     * Add a Factory Creation to the map.
     * Allows the item to be created by the factory.
     * 
     * @param name The keyword that will be used in the file
     * @param creation The implemented class that has the code needed to create the object
     */
    protected void registerCreation (String name, IFactoryCreator creation) {
        myCreationMap.put(name, creation);
    }
}
