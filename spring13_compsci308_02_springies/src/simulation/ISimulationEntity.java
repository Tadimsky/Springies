package simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Scanner;

public interface ISimulationEntity {
    public abstract void update(double elapsedTime, Dimension bounds);    
    public abstract void paint(Graphics2D draw);
}