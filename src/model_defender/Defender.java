package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model_attacker.Attacker;
import model_general.Board;
import model_general.Entity;

public abstract class Defender extends Entity {
	protected static double WALL_WIDTH=Board.getBOARD_WIDTH();
	protected static double WALL_HEIGHT=Board.getBOARD_HEIGHT();
	protected double Shootrange;
	protected void drawHPbar(GraphicsContext gc) {
		// TODO Auto-generated method stub
		double ratio=HP/MaxHP;
		gc.setFill(Color.LIGHTGREEN);
		if(ratio!=1)gc.fillRect(posX, posY, WALL_WIDTH, 4);
		gc.setFill(Color.ORANGERED) ;
		if(ratio!=1)gc.fillRect(posX, posY, WALL_WIDTH*(1-ratio), 4);
	}
	 //UPGRADING BOT
	public static double getWALL_WIDTH() {
		return WALL_WIDTH;
	}
	public static double getWALL_HEIGHT() {
		return WALL_HEIGHT;
	}
}
