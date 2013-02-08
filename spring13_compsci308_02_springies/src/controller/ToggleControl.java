package controller;

public abstract class ToggleControl extends KeyControl {

    private boolean isActive;
    
    // All types right now have a double that can be activated
    // May change for future things
    private double savedValue;
    
    public ToggleControl(int Key) 
    {
        super(Key);
        isActive = true;
    }
    
    public abstract void Toggle();
    
    public double doToggle(double current)
    {        
        isActive = !isActive;
        if (isActive)
        {   
            // turned on            
            return savedValue;
        }
        else
        {            
            // turned off
            savedValue = current;
            // turn it off
            return 0;
        }
    }
    
    @Override
    public void Activate()
    {
        Toggle();
    }

}
    
   
