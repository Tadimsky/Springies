package controller;

public abstract class Control {
    
    private int myKeyCode;
    
    public Control (int Key) {
        myKeyCode = Key;
    }
    
    public int getKey()
    {
        return myKeyCode;
    }
    
    public abstract void Activate();
    
}
