package factory;

import java.util.Scanner;


/**
 * This interface requires sub classes to have a createItem method so that
 * the factory can call on this method for the object to be created.
 * 
 * @author Jonathan Schmidt, Yang Yang
 * 
 */
public interface IFactoryCreator {
    /**
     * Creates the item from the provided scanner
     * 
     * @param s The Scanner containing the current line.
     * @return The created object
     */
    Object createItem (Scanner s);
}
