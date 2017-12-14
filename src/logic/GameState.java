package logic;

import drawing.PlayScreen;
import javafx.animation.AnimationTimer;

public class GameState {
	private boolean isWin;
	private boolean isLose;
	private static long GAMEBASETIME=151*1000000000L;
	private long timeLeft;
	private long prevTime;
	private long currentTime;
	private Thread timeThread;
	public GameState()
	{
		initialize();
		timeThread=new Thread(new Runnable()
		{
			@Override
			public void run() {
				while(true)
				{
				//	System.out.println(Timeleft);
					currentTime=System.nanoTime();	
					if(PlayScreen.instance.isPausedstate()==false&&isLose==false &&isWin==false)
					{
						timeLeft-=currentTime-prevTime;
						if(timeLeft<=0)timeLeft=0;
					}
					prevTime=currentTime;
					Thread.yield();
				}
			}
		});
		timeThread.start();
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
	public long getMinute()
	{
		return timeLeft/(60*1000000000L);
	}
	public long getSecond()
	{
		return (timeLeft/1000000000L)%60;
	}
	public boolean isTimeup()
	{
		return timeLeft<=1000000000L;
	}
	public void initialize() {
		isWin=false;
		isLose=false;
		timeLeft=GAMEBASETIME;
		prevTime=System.nanoTime();
	}
	public Thread getTimethread() {
		return timeThread;
	}
	public void endTimethread() {
//		System.out.println("threadstop");
		timeThread.interrupt();
	}

}
