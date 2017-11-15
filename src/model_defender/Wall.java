package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_general.Board;

public class Wall extends Defender{
	public Wall(double x,double y)
	{
		this.ATK=0;
		this.DEF=0;
		this.posX=x;
		this.posY=y;
		this.setHP(1000);
		this.MaxHP=getHP();
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.SLATEGRAY);
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
