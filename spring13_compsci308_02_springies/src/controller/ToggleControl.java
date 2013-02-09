package Controller;

public abstract class ToggleControl extends KeyControl{

	private boolean isActive;
	private double savedValue;
	
	public ToggleControl(int keyCode) {
		super(keyCode);
		isActive = true;
	}

	public abstract void Toggle();
	
	public double doToggle(double value){
		if(isActive){
			savedValue = value;
			isActive = !isActive;
			return 0;
		}else{
			isActive = !isActive;
			return savedValue;
		}
		
	}
	
	public void activate(){
		Toggle();
	}
}
