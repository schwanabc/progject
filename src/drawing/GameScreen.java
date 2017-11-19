package drawing;

import SharedObject.IRenderable;
import SharedObject.RenderableHolder;
import Input.InputUtility;
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
	public static double GAMESCREEN_WIDTH=SceneManager.SCREEN_WIDTH*0.75;
	public static double GAMESCREEN_HEIGHT=SceneManager.SCREEN_HEIGHT;
	
	public GameScreen()
	{
		super(GAMESCREEN_WIDTH,GAMESCREEN_HEIGHT);
		this.setVisible(true);
		addListerner();
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
	public void PaintLoseScreen() {
		// TODO Auto-generated method stub
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, GAMESCREEN_WIDTH, GAMESCREEN_HEIGHT);
		Font TEXT_FONT = new Font("Monospace", 100);
		gc.setFont(TEXT_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(Color.WHITE);
		gc.fillText("YOU LOSE", GAMESCREEN_WIDTH*0.5,GAMESCREEN_HEIGHT*0.5);
	}
	public void addListerner() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});
		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
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
			InputUtility.mouseOnScreen = true;
			InputUtility.currentUI="GAME";
		});
		this.setOnMouseExited((MouseEvent event) -> {
			InputUtility.mouseOnScreen = false;
			InputUtility.currentUI="x";
		});

		this.setOnMouseMoved((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});
		this.setOnMouseDragged((MouseEvent event) -> {
			if (InputUtility.mouseOnScreen) {
				InputUtility.mouseX = event.getX();
				InputUtility.mouseY = event.getY();
			}
		});
	}

	public static boolean isIngamescreen() {
		if(InputUtility.currentUI.equals("GAME"))return true;
		return false;
	}

}
