package controller;

public abstract class CommandControl extends KeyControl {

    
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
