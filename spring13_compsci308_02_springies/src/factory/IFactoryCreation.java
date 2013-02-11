package factory;

import java.util.Scanner;


public interface IFactoryCreation {
    public abstract Object createItem (Scanner s) throws Exception;
}
