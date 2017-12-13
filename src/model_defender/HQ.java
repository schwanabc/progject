package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model_general.Board;
import sharedobject.RenderableHolder;

public class HQ extends Defender{

	public HQ(double posX,double posY,int posI,int posJ)
	{
		super(posX,posY,posI,posJ);
		this.ATK=0;
		this.DEF=0;
		this.setHP(5000);
		this.MaxHP=getHP();
		this.wallWidth=Board.BOARD_WIDTH*4;
		this.wallHeight=Board.BOARD_HEIGHT*4;
	}
	@Override
	public int getZ() {
		return 3;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.HQ, posX-5, posY-5, wallWidth+5, wallHeight+10);
		drawHPBar(gc);
	}

	@Override
	public void update() {}

}
