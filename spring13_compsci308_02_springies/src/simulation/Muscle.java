package simulation;

import java.awt.Dimension;
import java.util.Map;
import java.util.Scanner;

public class Muscle extends Spring implements ISimulationEntity{

    private double myAmplitude;
    private double originalLength; 
    private double totalTime;

    public Muscle(Mass start, Mass end, double length, double kVal, double amp) {
        super(start, end, length, kVal);
        myAmplitude = amp;
        originalLength = length;
        totalTime = 0;
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        double newLength = 0;
        totalTime += elapsedTime;		
        double avgmass = Math.abs(getStartMass().getMass() + getEndMass().getMass());
        avgmass /= 2;		
        double omega = Math.sqrt(avgmass / getKValue());        
        newLength = originalLength + Math.sin(omega * totalTime)*myAmplitude;        
        this.setLength(newLength);
        super.update(elapsedTime, bounds);
    }
    
    public static Muscle createEntity (Scanner s, Map<Integer, Mass> myMasses) {
        
        Mass m1 = myMasses.get(s.nextInt());
        Mass m2 = myMasses.get(s.nextInt());
        double restLength = s.nextDouble();
        double ks = s.nextDouble();
        double amp = s.nextDouble();
        return new Muscle(m1, m2, restLength, ks, amp);
    }
}
