package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.Gamelogic;
import model_attacker.Attacker;
import model_attacker.Weak_1;
import model_general.Board;

public class weak_tower extends Defender{
	protected int Currentshootingtick;
	protected int Shootingtick;
	public weak_tower(double x,double y)
	{
		this.ATK=40;
		this.DEF=10;
		this.posX=x;
		this.posY=y;
		this.setHP(500);
		this.MaxHP=getHP();
		this.Shootrange=5;
		Currentshootingtick=0;
		Shootingtick=40;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.YELLOWGREEN);
		gc.fillRect(posX, posY, WALL_WIDTH, WALL_HEIGHT);
		drawHPbar(gc);
	}
	protected void ColliedwithAttacker()//find nearest target
	{
		double min=999999999;
		int idx=-1;
		int count=0;
		for(Attacker attacker:Gamelogic.getAttackercontainer())
		{
			double dist=Math.hypot(posX+Defender.WALL_WIDTH/2-attacker.getPosX(),posY+Defender.WALL_HEIGHT/2-attacker.getPosY());
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
			double x0=posX+Defender.WALL_WIDTH/2;
			double y0=posY+Defender.WALL_HEIGHT/2;
			double r0=Shootrange*Board.getBoardRange();
			double x1=attacker.getPosX();
			double y1=attacker.getPosY();
			double r1=attacker.getRADIUS();
			
			if(Math.hypot(x0-x1, y0-y1) <= (r0 + r1))
			{
			//	System.out.println(Math.hypot(x0-x1, y0-y1)+" "+(r0 + r1));
			//	System.out.println("Shootable");
				int direction=0;
				Currentshootingtick++;
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
		ColliedwithAttacker();
	}
	private void shoot(double theta,int direction) {
		// TODO Auto-generated method stub
		//Gamelogic.addNewObject(new Weak_1(posX,posY));
		Gamelogic.addNewObject(new Bullet(Shootrange,posX+Defender.WALL_WIDTH/2,posY+Defender.WALL_HEIGHT/2,ATK,theta,direction));
	}
	public int getZ() {
		return 0;
	}
}
