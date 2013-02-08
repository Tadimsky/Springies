package controller;

public abstract class CommandControl extends Control {

    
    public CommandControl (int Key) {
        super(Key);
    }
    
    public abstract void Execute();
    
    @Override
    public void Activate()
    {
        Execute();
    }
}
