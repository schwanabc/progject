package logic;

import Scenemanager.PlayScreen;
import javafx.animation.AnimationTimer;

public class Gamestate {
	private boolean isWin;
	private boolean isLose;
	private static long GAMEBASETIME=151*1000000000L;
	private long Timeleft;
	private long prevtime;
	private long currenttime;
	Thread Timethread;
	public Gamestate()
	{
		initialize();
		Timethread=new Thread(new Runnable()
		{
			@Override
			public void run() {
				while(true)
				{
				//	System.out.println(Timeleft);
					currenttime=System.nanoTime();	
					if(PlayScreen.isPausedstate()==false&&isLose==false &&isWin==false)
					{
						Timeleft-=currenttime-prevtime;
						if(Timeleft<=0)Timeleft=0;
					}
					prevtime=currenttime;
					Thread.yield();
				}
			}
		});
		Timethread.start();
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
		return Timeleft/(60*1000000000L);
	}
	public long getsecond()
	{
		return (Timeleft/1000000000L)%60;
	}
	public boolean isTimeup()
	{
		return Timeleft<=1000000000L;
	}
	public void initialize() {
		// TODO Auto-generated method stub
		System.out.println("reset"+GAMEBASETIME);
		isWin=false;
		isLose=false;
		Timeleft=GAMEBASETIME;
		prevtime=System.nanoTime();
	}
	public Thread getTimethread() {
		// TODO Auto-generated method stub
		return Timethread;
	}
	public void EndTimethread() {
		// TODO Auto-generated method stub
		System.out.println("threadstop");
		Timethread.interrupt();
	}

}
