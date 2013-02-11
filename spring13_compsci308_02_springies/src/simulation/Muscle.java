package simulation;

import java.awt.Dimension;
import java.util.Map;
import java.util.Scanner;


/**
 * class of muscle.
 * have same properties with springies
 * unless its motion is harmonic
 * 
 * 
 * @author Jonno, Yang
 * 
 */
public class Muscle extends Spring implements ISimulationEntity {

    private double myAmplitude;
    private double myOriginalLength;
    private double myTotalTime;

    /**
     * constructor for muscle
     * 
     * @param start the start mass point of this muscle
     * @param end the end mass point of this muscle
     * @param length the rest length of this muscle
     * @param kVal the k parameter of this mass
     * @param amp the amplitude of the harmonic motion
     */
    public Muscle (Mass start, Mass end, double length, double kVal, double amp) {
        super(start, end, length, kVal);
        myAmplitude = amp;
        myOriginalLength = length;
        myTotalTime = 0;
    }

    @Override
    public void update (double elapsedTime, Dimension bounds) {
        double newLength = 0;
        myTotalTime += elapsedTime;
        double avgmass = Math.abs(getStartMass().getMass() + getEndMass().getMass());
        avgmass /= 2;
        double omega = Math.sqrt(avgmass / getKValue());
        newLength = myOriginalLength + Math.sin(omega * myTotalTime) * myAmplitude;
        setLength(newLength);
        super.update(elapsedTime, bounds);
    }

    /**
     * create muscle entities using data obtained from sources
     * 
     * @param s scanner where we get data
     * @param myMasses a map that contains all the mass point in this system
     * @return
     */
    public static Muscle createEntity (Scanner s, Map<Integer, Mass> myMasses) {

        Mass m1 = myMasses.get(s.nextInt());
        Mass m2 = myMasses.get(s.nextInt());
        double restLength = s.nextDouble();
        double ks = s.nextDouble();
        double amp = s.nextDouble();
        return new Muscle(m1, m2, restLength, ks, amp);
    }
}
