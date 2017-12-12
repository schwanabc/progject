package scenemanager;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

import drawing.LoseScreen;
import drawing.PlayScreen;
import drawing.StartScreen;
import drawing.WaitScreen;
import drawing.WinScreen;

public class SceneManager {

	private static Stage primaryStage;
	private static Scene playScene;
	private static Scene waitScene;
	private static Scene winScene;
	private static Scene loseScene;
	private static Scene startScene;
	private static PlayScreen playScreen;
	public static String GAMENAME="TOWER HATER 2.0";
	public static double SCREEN_HEIGHT;
	public static double SCREEN_WIDTH;
	static {
		getScreensize();
	};
	public static void setStage(Stage primaryStage) {
		System.out.println(SCREEN_HEIGHT);
		System.out.println(SCREEN_WIDTH);
		SceneManager.primaryStage=primaryStage;
		SceneManager.primaryStage.setTitle(GAMENAME);
		SceneManager.primaryStage.setHeight(SCREEN_HEIGHT);
		SceneManager.primaryStage.setWidth(SCREEN_WIDTH);
		SceneManager.primaryStage.setFullScreenExitHint("");
		SceneManager.primaryStage.centerOnScreen();
		SceneManager.primaryStage.show();
		SceneManager.primaryStage.setResizable(false);
	} 
	private static void getScreensize() {
		// TODO Auto-generated method stub
		System.out.println(System.getProperty("os.name"));
		if(System.getProperty("os.name").equals("Windows 10"))
		{
			SCREEN_HEIGHT= Toolkit.getDefaultToolkit().getScreenSize().getHeight()*2/3;
			SCREEN_WIDTH= Toolkit.getDefaultToolkit().getScreenSize().getWidth()*2/3;
		}
		else
		{
			SCREEN_HEIGHT= Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.8;
			SCREEN_WIDTH= Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.8;

		}

	}
	public static void gotoStartScreen() { 
		startScene=new Scene(new StartScreen(),SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(startScene);
		SceneManager.primaryStage.setFullScreen(true);
	}
	public static void gotoPlayScreen() { 
		initializePlayScene();
		primaryStage.setScene(playScene);
		SceneManager.primaryStage.setFullScreen(true);
		playScreen.getPlayThread().start();
	}
	private static void initializePlayScene() {
		playScreen=new PlayScreen();
		playScene=new Scene(playScreen,SCREEN_WIDTH,SCREEN_HEIGHT);
		playScreen.getGameScreen().requestFocus();
	}
	public static void gotoWaitScreen() {
		waitScene=new Scene(new WaitScreen(),SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(waitScene);
		SceneManager.primaryStage.setFullScreen(true);
	}

	public static void gotoWinScreen() {
		winScene=new Scene(new WinScreen(),SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(winScene);
		SceneManager.primaryStage.setFullScreen(true);
	}
	public static void gotoLoseScreen() {
		loseScene=new Scene(new LoseScreen(),SCREEN_WIDTH,SCREEN_HEIGHT);
		primaryStage.setScene(loseScene);
		SceneManager.primaryStage.setFullScreen(true);
	}
	
}
