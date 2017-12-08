package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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
		gc.setFill(Color.DARKGREEN);
		if(ratio!=1)gc.fillRect(posX, posY, Wallwidth, 4);
		gc.setFill(Color.ORANGERED) ;
		if(ratio!=1)gc.fillRect(posX+Wallwidth*(ratio), posY, Wallwidth*(1-ratio), 4);
		gc.setStroke(Color.BLACK);
		if(ratio!=1)gc.strokeRect(posX, posY, Wallwidth, 4);
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
