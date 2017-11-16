package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model_attacker.Attacker;
import model_general.Board;
import model_general.Entity;

public abstract class Defender extends Entity {
	protected double Wallwidth;
	protected double Wallheight;
	protected double Shootrange;
	protected int posI,posJ;
	public Defender(double posX,double posY,int posI,int posJ)
	{
		this.posX=posX;
		this.posY=posY;
		this.posI=posI;
		this.posJ=posJ;
	}
	protected void drawHPbar(GraphicsContext gc) {
		// TODO Auto-generated method stub
		double ratio=(HP/MaxHP);
		if(ratio<0)ratio=0;
		gc.setFill(Color.LIGHTGREEN);
		if(ratio!=1)gc.fillRect(posX, posY, Wallwidth, 4);
		gc.setFill(Color.ORANGERED) ;
		if(ratio!=1)gc.fillRect(posX+Wallwidth*(ratio), posY, Wallwidth*(1-ratio), 4);
	}
	 //UPGRADING BOT
	public double getWallwidth() {
		return Wallwidth;
	}
	public double getWallheight() {
		return Wallheight;
	}
	public int getPosI() {
		return posI;
	}
	public int getPosJ() {
		return posJ;
	}

}
