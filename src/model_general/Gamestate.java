package model_general;


public class Gamestate {
	private static final long START_NANO_TIME = 6000000000L;
	private long remainingNanoTime;
	private boolean isWin;
	private boolean isLose;
	public Gamestate() 
	{
		remainingNanoTime = START_NANO_TIME;
		isWin=false;
		isLose=false;
	}
	public long getSecond()
	{
		return (remainingNanoTime/(100000000L));
	}
	public long getMinute()
	{
		return  (remainingNanoTime/(60*100000000L));
	}
	public void TimeElapsed(long elapsedTime) {
		// TODO Auto-generated method stub
		remainingNanoTime-=elapsedTime;
	}
	public long getRemainingNanoTime() {
		return remainingNanoTime;
	}
	public void setRemainingNanoTime(long remainingNanoTime) {
		this.remainingNanoTime = remainingNanoTime;
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
}
