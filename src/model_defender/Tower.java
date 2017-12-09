package model_defender;

import SharedObject.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.Gamelogic;
import model_attacker.Attacker;
import model_general.Board;
import model_general.Bullet;

public class Tower extends Defender{
	protected int Currentshootingtick;
	protected int Shootingtick;
	public Tower(double posX,double posY,int posI,int posJ)
	{
		super(posX,posY,posI,posJ);
		this.ATK=50;
		this.DEF=10;
		this.setHP(500);
		this.MaxHP=getHP();
		this.Shootrange=5;
		Currentshootingtick=60;
		Shootingtick=60;
		this.Wallwidth=Board.BOARD_WIDTH;
		this.Wallheight=Board.BOARD_HEIGHT;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.NormalTower,posX, posY, Wallwidth, Wallheight);
		drawHPbar(gc);
		
		/*
		gc.setStroke(Color.RED);
		gc.setLineWidth(1);
		gc.strokeOval(posX-Shootrange*Wallwidth, posY-Shootrange*Wallheight,Wallwidth+2*Shootrange*Wallwidth,Wallheight+2*Shootrange*Wallheight);
		*/
	}
	protected void ColliedwithAttacker()//find nearest target
	{
		double min=999999999;
		int idx=-1;
		int count=0;
		for(Attacker attacker:Gamelogic.getAttackercontainer())
		{
			double dist=Math.hypot(posX+Wallwidth/2-attacker.getPosX(),posY+Wallheight/2-attacker.getPosY());
			if(dist<min)
			{
				min=dist;
				idx=count;
			}
			count++;
		}
		try
		{
			Attacker attacker=Gamelogic.getAttackercontainer().get(idx);
			double x0=posX+Wallwidth/2;
			double y0=posY+Wallheight/2;
			double r0=Shootrange*Board.BOARD_RANGE;
			double x1=attacker.getPosX();
			double y1=attacker.getPosY();  
			double r1=attacker.getRADIUS();
			
			if(Math.hypot(x0-x1, y0-y1) <= (r0 + r1))
			{
			//	System.out.println(Math.hypot(x0-x1, y0-y1)+" "+(r0 + r1));
			//	System.out.println("Shootable");
				int direction=0;
				double dy = y1 - y0;
				double dx = x1 - x0;
				if(dy<0)direction+=1;
				if(dx<0)direction+=2;
				double theta = Math.atan(dy/dx);
				 theta*= 180/Math.PI ;
			//	 System.out.println(theta);
				 if(Shootable())
					 {shoot(theta,direction);}
			}
		}
		catch(Exception e) {}
	}
	private boolean Shootable() {
		if(Currentshootingtick>=Shootingtick)
		{
			Currentshootingtick=0;
			return true;
		}
		return false;
	}
	@Override
	public void update() {
		
		// TODO Auto-generated method stub
		Currentshootingtick++;
		ColliedwithAttacker();
	}
	private void shoot(double theta,int direction) {
		// TODO Auto-generated method stub
		//Gamelogic.addNewObject(new Weak_1(posX,posY));
		RenderableHolder.Arrow.setVolume(0.05);
		RenderableHolder.Arrow.play();
		Gamelogic.addNewObject(new Bullet(Shootrange,posX+Wallwidth/2,posY+Wallheight/2,ATK,theta,direction));
	}
	public int getZ() {
		return 2;
	}
}
