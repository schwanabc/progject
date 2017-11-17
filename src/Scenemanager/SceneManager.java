package Scenemanager;

import java.util.concurrent.TimeUnit;

import Input.InputUtility;
import SharedObject.RenderableHolder;
import drawing.GameScreen;
import drawing.Menubar;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Gamelogic;
import model_general.Board;

public class SceneManager {

	private static Stage primaryStage;
	private static Scene playScene;
	public static double SCREEN_WIDTH=800;
	public static double SCREEN_HEIGHT=600;
	public static AnimationTimer AT,AT2;
	public static void initialize(Stage stage) {
		primaryStage = stage;
	}
	public static void gotoPlayScreen() {
		//TODO Fill Code
		InitializeplayScene();
		primaryStage.setScene(playScene);
		primaryStage.show();
	}
	private static void InitializeplayScene() {
		// TODO Auto-generated method stub
		HBox root = new HBox();
		GameScreen gamescreen=new GameScreen();
		Menubar menubar=new Menubar(SCREEN_WIDTH*0.25,SCREEN_HEIGHT);
		root.getChildren().add(gamescreen);
		root.getChildren().add(menubar);
		playScene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
		gamescreen.requestFocus();
		Gamelogic gamelogic=new Gamelogic();
		AT=new AnimationTimer(){
			public void handle(long now)
			{
				long lastLoopStartTime = System.nanoTime();
				gamelogic.update();
				gamescreen.PaintComponent();
				InputUtility.updateInputState();
				RenderableHolder.getInstance().update();
				long elapsedTime = System.nanoTime() - lastLoopStartTime;
				menubar.getGamestate().TimeElapsed(elapsedTime);
				menubar.setMenutab();
			}

		};
		AT2=new AnimationTimer(){
			public void handle(long now)
			{
				Board.update();
				gamescreen.PaintComponent();
				RenderableHolder.getInstance().update();
				InputUtility.updateInputState();
			}
		};
		AT.start();
	}
	private void Pausewin(GameScreen gamescreen) {
		// TODO Auto-generated method stub
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gamescreen.PaintWinScreen();
		AT.stop();
	}
	
}
