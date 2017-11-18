package model_general;


public class Gamestate {
	private boolean isWin;
	private boolean isLose;
	public Gamestate()
	{
		isWin=false;
		isLose=false;
	}
	public boolean isWin() {
		return isWin;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
	public boolean isLose() {
		return isLose;
	}
	public void setLose(boolean isLose) {
		this.isLose = isLose;
	}
	public boolean isOngoing() {
		return !(isWin||isLose);
		
	}
}
