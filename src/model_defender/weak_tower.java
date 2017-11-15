package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_general.Board;

public class weak_tower extends Defender{

	
	public weak_tower(double x,double y)
	{
		this.ATK=20;
		this.DEF=10;
		this.posX=x;
		this.posY=y;
		this.setHP(500);
		this.MaxHP=getHP();
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.YELLOWGREEN);
		gc.fillRect(posX, posY, WALL_WIDTH, WALL_HEIGHT);
		drawHPbar(gc);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public int getZ() {
		return 0;
	}
}
