package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model_general.Board;
import sharedobject.RenderableHolder;

public class Wall extends Defender{

	public Wall(double posX,double posY,int posI,int posJ)
	{
		super(posX,posY,posI,posJ);
		this.ATK=0;
		this.DEF=0;
		this.setHP(1000);
		this.MaxHP=getHP();
		this.wallWidth=Board.BOARD_WIDTH;
		this.wallHeight=Board.BOARD_HEIGHT;
	}
	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.wall, posX, posY, wallWidth, wallHeight);
		drawHPBar(gc);
	}
	
	@Override
	public void update() {}
	public int getZ() {
		return 1;
	}

}
