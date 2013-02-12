package controller;

/**
 * Toggle Control is a control that can be toggled on and off.
 * It will set a value that it is changing to be 0 if off and
 * restore the original value if it is 'on'.
 * Uses a double as the saved state as the only thing that was
 * toggled is a double value.
 * Could easily be changed to store an Object as the save state.
 * 
 * @author Jonathan Schmidt, Yang Yang
 * 
 */
public abstract class ToggleControl extends KeyControl {

    private boolean myIsActive;

    private double mySavedValue;

    /**
     * Provides a constructor to the concrete subclasses of this class.
     * 
     * @param key The key that will be used to toggle the control.
     */
    public ToggleControl (int key) {
        super(key);
        myIsActive = true;
    }

    /**
     * The method that is called when the keyboard button is pressed.
     * The code that must be run when the key is pressed must be written in here.
     */
    public abstract void toggle ();

    /**
     * Helper method to set the state of the toggle, and set the value of the property.
     * 
     * @param current The current value of the property that is being changed.
     * @return 0 if the toggle is now disabled or the original value if it is now enabled
     */
    public double doToggle (double current) {
        myIsActive = !myIsActive;
        if (myIsActive) {
            // turned on
            return mySavedValue;
        }
        else {
            // turned off
            mySavedValue = current;
            // turn it off
            return 0;
        }
    }

    /**
     * Used to call the toggle method when activate is called by the controller.
     * 
     * @see controller.KeyControl#activate()
     */
    @Override
    public void activate () {
        toggle();
    }

}
