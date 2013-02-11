package controller;

/**
 * An abstract class that defines a key that will be 
 * used to activate a control that is inherited from 
 * this class.
 * Forms the base for all controls.
 * @author Jonno
 *
 */
public abstract class KeyControl {

    private int myKeyCode;

    /**
     * Creates the KeyControl and stores the key value
     * @param key The key that will activate this control
     */
    public KeyControl (int key) {
        myKeyCode = key;
    }

    /**
     * Returns the key that this control is activated by
     * @return activation key
     */
    public int getKey () {
        return myKeyCode;
    }

    /**
     * Activate the control - make it perform it's duties.
     * This will be called when the key is pressed
     */
    public abstract void activate ();

}
