package model_general;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import logic.IMovable;
import model_attacker.Attacker;

public class Bullet extends Entity implements IMovable{
	protected double angle;
	protected double radius;
	protected double speed;
	protected int direction;
	public Bullet(double shootRange,double posX,double posY,double ATK,double angle,int direction)
	{
		this.ATK=ATK;
		this.DEF=0;
		this.radius=2;
		this.HP=shootRange*60*1.8; //Bullet HP=tick left until bullet disappeared
		this.speed=Math.sqrt(Board.BOARD_HEIGHT*Board.BOARD_HEIGHT + Board.BOARD_WIDTH*Board.BOARD_WIDTH)/20;
		this.posX=posX;
		this.posY=posY;
		this.angle=angle;
		this.direction=direction;
	}
	@Override
	public int getZ() {
		return 7;
	}
	protected void colliedWithAttacker()
	{
		for(Attacker attacker: logic.Gamelogic.getAttackerContainer())
		{
			Circle c1=new Circle( posX, posY, radius);
			Circle c2=new Circle( attacker.getPosX(),  attacker.getPosY(), attacker.getRadius());
			if (c1.getBoundsInParent().intersects(c2.getBoundsInParent())) {
		        bulletAttack(attacker);
		        break;
		      }
		}
	}

	protected void bulletAttack(Attacker attacker) {
		double downHP=ATK-attacker.getDEF();
		if(downHP<=0)downHP=1;
		attacker.setHP(attacker.getHP() -downHP);//temp
		this.destroyed=true;
		
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.DIMGRAY);
		gc.fillOval(posX-radius, posY-radius, radius*2, radius*2);
	}
	@Override
	public void forward(double xAxis,double yAxis)
	{
		double Calibrator=Math.abs(xAxis)+Math.abs(yAxis);
		posX+=calibrate(xAxis,Calibrator)*speed;
		posY+=calibrate(yAxis,Calibrator)*speed;
		HP-=6;
	}
	@Override
	public double calibrate(double velocity, double speed) {
		if(speed!=0)return velocity/speed;
		else return 0;
	}
	@Override
	public void update() {
		//System.out.println(angle+" "+direction);
		if(direction<2)forward(Math.cos(Math.toRadians(angle)),Math.sin(Math.toRadians(angle)));
		else forward(-1*Math.cos(Math.toRadians(angle)),-1*Math.sin(Math.toRadians(angle)));
		colliedWithAttacker();
	}
	

}
