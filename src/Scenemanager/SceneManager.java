package Scenemanager;

import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import drawing.WaitScreen;

public class SceneManager {

	private  static Stage primaryStage;
	private  static Scene playScene;
	private  static Scene waitScene;
	private  static PlayScreen Playscreen;
	public static double SCREEN_HEIGHT=660;
	public static double SCREEN_WIDTH=(SCREEN_HEIGHT*4)/3;
	public SceneManager(Stage primaryStage) {
		// TODO Auto-generated constructor stub
		this.primaryStage=primaryStage;
		InitializeplayScene();
		primaryStage.show();
	}

	public static  void gotoPlayScreen() {
		//TODO Fill Code
		primaryStage.setScene(playScene);
		Playscreen.AT.start();
	}
	private static void InitializeplayScene() {
		// TODO Auto-generated method stub
		Playscreen=new PlayScreen();
		playScene=new Scene(Playscreen,SCREEN_WIDTH,SCREEN_HEIGHT);
		Playscreen.gamescreen.requestFocus();
	}
	public static  void gotoWaitScreen() {
		//TODO Fill Code
		WaitScreen WaitScreen=new WaitScreen();
		waitScene=new Scene(WaitScreen,SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(waitScene);
	}
	
}
