package factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * XXX
 * 
 * @author Robert C. Duvall
 */
public abstract class Factory {
    
    // mass IDs
   
    
    private Map<String, IFactoryCreation> creationMap = new HashMap<String, IFactoryCreation>();

    /**
     * XXX.
     */
    public List<Object> loadModel (File modelFile) {        
        try {
            ArrayList<Object> objects = new ArrayList<Object>();
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) 
                {                    
                    String type = line.next();
                    IFactoryCreation create = creationMap.get(type);
                    if (create != null)
                    {
                        try 
                        {
                            objects.add(create.createItem(line));
                        }
                        catch (Exception  e) 
                        {                            
                            e.printStackTrace();
                        }                                                
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
    
    public void registerCreation(String name, IFactoryCreation creation)
    {
        creationMap.put(name, creation);
    }
}
