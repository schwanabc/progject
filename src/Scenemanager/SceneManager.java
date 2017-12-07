package Scenemanager;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

import drawing.StartScreen;
import drawing.WaitScreen;
import drawing.WinScreen;

public class SceneManager {

	private  static Stage primaryStage;
	private  static Scene playScene;
	private  static Scene waitScene;
	private  static Scene winScene;
	private  static Scene StartScene;
	private  static PlayScreen Playscreen;
	public static String GAMENAME="Game Name Please";
	public static double SCREEN_HEIGHT=660;
	public static double SCREEN_WIDTH=(SCREEN_HEIGHT*4)/3;
	public static void setStage(Stage primaryStage) {
		SceneManager.primaryStage=primaryStage;
		SceneManager.primaryStage.setTitle(GAMENAME);
		primaryStage.show();
	} 
	public static  void gotoStartScreen() { 
		StartScene=new Scene(new StartScreen(),SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(StartScene);
		SceneManager.primaryStage.setFullScreenExitHint("");
		SceneManager.primaryStage.centerOnScreen();
	}
	public static  void gotoPlayScreen() { 
		InitializeplayScene();
		primaryStage.setScene(playScene);
		Playscreen.AT.start();
	}
	private static void InitializeplayScene() {
		Playscreen=new PlayScreen();
		playScene=new Scene(Playscreen,SCREEN_WIDTH,SCREEN_HEIGHT);
		Playscreen.gamescreen.requestFocus();
	}
	public static  void gotoWaitScreen() {
		waitScene=new Scene(new WaitScreen(),SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(waitScene);
	}

	public static void gotoWinScreen() {
		winScene=new Scene(new WinScreen(),SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(winScene);
	}
	
}
