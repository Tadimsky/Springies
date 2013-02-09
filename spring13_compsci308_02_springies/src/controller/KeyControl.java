package Controller;

public abstract class KeyControl {
    private int myKeyCode;
    
    public KeyControl(int keyCode){
    	myKeyCode = keyCode;
    }
    public int getKey(){
    	return myKeyCode;
    }
    public abstract void activate();
}
