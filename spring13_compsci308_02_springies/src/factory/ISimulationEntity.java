package factory;

import java.util.Scanner;

public interface ISimulationEntity {
    public abstract ISimulationEntity createEntity(Scanner s);
}
