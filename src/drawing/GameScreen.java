package drawing;

import SharedObject.IRenderable;
import SharedObject.RenderableHolder;
import Utility.InputUtility;
import Scenemanager.SceneManager;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameScreen extends Canvas{
	public static double GAMESCREEN_HEIGHT=SceneManager.SCREEN_HEIGHT;
	public static double GAMESCREEN_WIDTH=GAMESCREEN_HEIGHT;
	
	public GameScreen()
	{
		super(GAMESCREEN_WIDTH,GAMESCREEN_HEIGHT);
		System.out.print(GAMESCREEN_HEIGHT);

		System.out.println(GAMESCREEN_WIDTH);
		System.out.println(GAMESCREEN_HEIGHT);
		this.setVisible(true);
		addListerner();
	}
	
	public void paintComponent()
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
	public void addListerner() {
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

	public static boolean isIngamescreen() {
		if(InputUtility.currentUI.equals("GAME"))return true;
		return false;
	}

}
