package controller;

public abstract class KeyControl {
    
    private int myKeyCode;
    
    public KeyControl (int Key) {
        myKeyCode = Key;
    }
    
    public int getKey()
    {
        return myKeyCode;
    }
    
    public abstract void Activate();
    
}
