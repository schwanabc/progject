package drawing;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import scenemanager.SceneManager;
import sharedobject.IRenderable;
import sharedobject.RenderableHolder;
import utility.InputUtility;

public class GameScreen extends Canvas{
	public static final double GAMESCREEN_HEIGHT=SceneManager.screenHeight;
	public static final double GAMESCREEN_WIDTH=GAMESCREEN_HEIGHT;
	
	public GameScreen()
	{
		super(GAMESCREEN_WIDTH,GAMESCREEN_HEIGHT);
		addListener();
	}
	
	public void paintComponent()
	{	
		GraphicsContext gc=this.getGraphicsContext2D();
		for(IRenderable entity: RenderableHolder.getInstance().getEntities())
		{
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}
		}
	}
	public void addListener() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode());
		});
		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyReleased();
		});
		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftDown();
		});
		this.setOnMouseReleased((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftRelease(); 
		});
		this.setOnMouseEntered((MouseEvent event) -> {
			InputUtility.currentUI="GAME";
		});
		this.setOnMouseExited((MouseEvent event) -> {
			InputUtility.currentUI="x";
		});
		this.setOnMouseMoved((MouseEvent event) -> {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
		});
		this.setOnMouseDragged((MouseEvent event) -> {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
		});
	}
	public static boolean isInGameScreen() {
		if(InputUtility.currentUI.equals("GAME"))return true;
		return false;
	}

}
