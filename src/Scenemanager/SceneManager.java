package Scenemanager;

import javafx.scene.Scene;
import javafx.stage.Stage;
import drawing.WaitScreen;
import drawing.WinScreen;

public class SceneManager {

	private  static Stage primaryStage;
	private  static Scene playScene;
	private  static Scene waitScene;
	private  static Scene winScene;
	private  static PlayScreen Playscreen;
	public static double SCREEN_HEIGHT=660;
	public static double SCREEN_WIDTH=(SCREEN_HEIGHT*4)/3;
	public static void setStage(Stage primaryStage) {
		// TODO Auto-generated constructor stub
		SceneManager.primaryStage=primaryStage;
		InitializeplayScene();
		primaryStage.show();
	} 

	public static  void gotoPlayScreen() { 
		//TODO Fill Code
		primaryStage.setScene(playScene);
		SceneManager.primaryStage.centerOnScreen();
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

	public static void gotoWinScreen() {
		WinScreen WinScreen=new WinScreen();
		winScene=new Scene(WinScreen,SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(winScene);
		
	}
	
}
