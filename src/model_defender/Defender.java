package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_general.Entity;

public abstract class Defender extends Entity {
	protected double wallWidth;
	protected double wallHeight;
	protected double shootRange;
	protected int posI,posJ;
	public Defender(double posX,double posY,int posI,int posJ)
	{
		this.posX=posX;
		this.posY=posY;
		this.posI=posI;
		this.posJ=posJ;
	}
	protected void drawHPBar(GraphicsContext gc) {
		double ratio=(HP/MaxHP);
		if(ratio<0)ratio=0;
		gc.setFill(Color.DARKGREEN);
		if(ratio!=1)gc.fillRect(posX, posY, wallWidth, 4);
		gc.setFill(Color.ORANGERED) ;
		if(ratio!=1)gc.fillRect(posX+wallWidth*(ratio), posY, wallWidth*(1-ratio), 4);
		gc.setStroke(Color.BLACK);
		if(ratio!=1)gc.strokeRect(posX, posY, wallWidth, 4);
	}
	public double getWallWidth() {
		return wallWidth;
	}
	public double getWallHeight() {
		return wallHeight;
	}
	public int getPosI() {
		return posI;
	}
	public int getPosJ() {
		return posJ;
	}

}
