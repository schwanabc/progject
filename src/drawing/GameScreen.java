package drawing;

import SharedObject.IRenderable;
import SharedObject.RenderableHolder;
import application.Main;
import Input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model_general.Board;

public class GameScreen extends UI{
	public static double GAMESCREEN_WIDTH=Main.SCREEN_WIDTH*0.75;
	public static double GAMESCREEN_HEIGHT=Main.SCREEN_HEIGHT;
	
	public GameScreen()
	{
		super(GAMESCREEN_WIDTH,GAMESCREEN_HEIGHT);
		this.setVisible(true);
	}
	
	public void PaintComponent()
	{
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(0, 0, GAMESCREEN_WIDTH, GAMESCREEN_HEIGHT);
		for(IRenderable entity: RenderableHolder.getInstance().getEntities())
		{
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
	}
	@Override
	public void addListerner()
	{
		super.addListerner();
		this.setOnMouseEntered((MouseEvent event) -> {
			InputUtility.mouseOnScreen = true;
			InputUtility.currentUI="GAME";
		});
	}

	public static boolean isIngamescreen() {
		// TODO Auto-generated method stub
		if(InputUtility.currentUI.equals("GAME"))return true;
		return false;
	}
	
}
