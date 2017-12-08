package Scenemanager;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;
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
	public static String GAMENAME="TOWER HATER 2.0";
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static double SCREEN_HEIGHT=720;
	public static double SCREEN_WIDTH=1280;
	public static void setStage(Stage primaryStage) {
		SceneManager.primaryStage=primaryStage;
		SceneManager.primaryStage.setTitle(GAMENAME);
		SceneManager.primaryStage.setHeight(SCREEN_HEIGHT);
		SceneManager.primaryStage.setWidth(SCREEN_WIDTH);
		SceneManager.primaryStage.setFullScreen(true);
		primaryStage.show();
	} 
	public static  void gotoStartScreen() { 
		StartScene=new Scene(new StartScreen(),SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(StartScene);
		SceneManager.primaryStage.setFullScreenExitHint("");
		SceneManager.primaryStage.setFullScreen(true);
		SceneManager.primaryStage.centerOnScreen();
	}
	public static  void gotoPlayScreen() { 
		InitializeplayScene();
		primaryStage.setScene(playScene);
		SceneManager.primaryStage.setFullScreen(true);
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
		SceneManager.primaryStage.setFullScreen(true);
	}

	public static void gotoWinScreen() {
		winScene=new Scene(new WinScreen(),SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(winScene);
		SceneManager.primaryStage.setFullScreen(true);
	}
	
}
