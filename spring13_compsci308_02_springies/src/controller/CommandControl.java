package Controller;

public abstract class CommandControl extends KeyControl {

	
	public CommandControl(int keyCode) {
		super(keyCode);
	}
	
	public abstract void execute();
	@Override
	
	public void activate() {
		execute();

	}

}
