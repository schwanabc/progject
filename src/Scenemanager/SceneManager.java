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

	private  Stage primaryStage;
	private  Scene playScene;
	private  PlayScreen Playscreen;
	public static double SCREEN_WIDTH=800;
	public static double SCREEN_HEIGHT=600;
	public SceneManager(Stage primaryStage) {
		// TODO Auto-generated constructor stub
		this.primaryStage=primaryStage;
	}

	public void gotoPlayScreen() {
		//TODO Fill Code
		InitializeplayScene();
		Playscreen.AT.start();
		primaryStage.setScene(playScene);
		primaryStage.show();
	}
	private void InitializeplayScene() {
		// TODO Auto-generated method stub
		Playscreen=new PlayScreen();
		playScene=new Scene(Playscreen,SCREEN_WIDTH,SCREEN_HEIGHT);
		Playscreen.gamescreen.requestFocus();
	}

	
}
