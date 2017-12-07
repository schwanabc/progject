package model_defender;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model_general.Board;

public class Wall extends Defender{
	private static Image Wall=new Image(ClassLoader.getSystemResource("Wall.jpg").toString());

	public Wall(double posX,double posY,int posI,int posJ)
	{
		super(posX,posY,posI,posJ);
		this.ATK=0;
		this.DEF=0;
		this.setHP(1000);
		this.MaxHP=getHP();
		this.Shootrange=0;
		this.Wallwidth=Board.BOARD_WIDTH;
		this.Wallheight=Board.BOARD_HEIGHT;
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(Wall, posX, posY, Wallwidth, Wallheight);

		drawHPbar(gc);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public int getZ() {
		return 1;
	}

}
