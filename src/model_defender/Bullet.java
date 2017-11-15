package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_general.Board;
import model_general.Entity;

public class Bullet extends Entity{
	protected double angle;
	protected double radius;
	protected double speed;
	protected int direction;
	public Bullet(double Shootrange,double posX,double posY,double ATK,double angle,int direction)
	{
		this.ATK=ATK;
		this.DEF=0;
		this.radius=2;
		this.HP=Shootrange*1.5*60;
		this.speed=Math.sqrt(Board.getBOARD_HEIGHT()*Board.getBOARD_HEIGHT() + Board.getBOARD_COLUMN()*Board.getBOARD_COLUMN())/20;
		this.posX=posX;
		this.posY=posY;
		this.angle=angle;
		this.direction=direction;
	}
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.BLUE);
		gc.fillOval(posX-radius, posY-radius, radius*2, radius*2);
	}
	public void foward(double xAxis,double yAxis)
	{
		posX+=xAxis*speed;
		posY+=yAxis*speed;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		//System.out.println(angle+" "+direction);
		if(direction<2)foward(Math.cos(Math.toRadians(angle)),Math.sin(Math.toRadians(angle)));
		else foward(-1*Math.cos(Math.toRadians(angle)),-1*Math.sin(Math.toRadians(angle)));
	}

}
