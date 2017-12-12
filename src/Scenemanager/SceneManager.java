package Scenemanager;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

import com.sun.javafx.geom.Rectangle;

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
	static 
	{
		getScreenSize();
	};
	public static void setStage(Stage primaryStage) {
		System.out.println(System.getProperty("os.arch"));
		System.out.println(SCREEN_WIDTH);
		SceneManager.primaryStage=primaryStage;
		SceneManager.primaryStage.setTitle(GAMENAME);
		SceneManager.primaryStage.setHeight(SCREEN_HEIGHT);
		SceneManager.primaryStage.setWidth(SCREEN_WIDTH);
		SceneManager.primaryStage.setFullScreenExitHint("");
		SceneManager.primaryStage.centerOnScreen();
		SceneManager.primaryStage.show();
	} 
	private static void getScreenSize() {
		// TODO Auto-generated method stub
		if(System.getProperty("os.arch").equals("amd64"))
		{
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        SCREEN_HEIGHT=screenSize.getHeight()*2/3;
        SCREEN_WIDTH=screenSize.getWidth()*2/3;
		}
		else
		{
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        SCREEN_HEIGHT=screenSize.getHeight()*3;
	        SCREEN_WIDTH=screenSize.getWidth()*3;
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
		playScreen.getAT().start();
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
