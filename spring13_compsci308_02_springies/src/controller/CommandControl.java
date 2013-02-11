package controller;

/**
 * This is a control that is activated by the keyboard and
 * will execute a particular command when the appropriate
 * button is pressed.
 * 
 * @author Jonathan Schmidt, Yang Yang
 * 
 */
public abstract class CommandControl extends KeyControl {

    /**
     * Creates the CommandControl
     * 
     * @param key The key that will be used to activate the command
     */
    public CommandControl (int key) {
        super(key);
    }

    /**
     * The command that will be run when the key is pressed.
     */
    public abstract void execute ();

    @Override
    public void Activate ()
    {
        execute();
    }
}
