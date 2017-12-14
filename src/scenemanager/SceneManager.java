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
import drawing.CharacterDescribeScreen;

public class SceneManager {

	private static Stage primaryStage;
	private static Scene playScene;
	private static Scene waitScene;
	private static Scene winScene;
	private static Scene loseScene;
	private static Scene startScene;
	private static Scene characterDescribeScreen;
	private static PlayScreen playScreen;
	public static String GAMENAME="TOWER HATER 2.0";
	public static double screenHeight;
	public static double screenWidth;
	static {
		getScreensize();
	};
	public static void setStage(Stage primaryStage) {
		SceneManager.primaryStage=primaryStage;
		SceneManager.primaryStage.setTitle(GAMENAME);
		SceneManager.primaryStage.setHeight(screenHeight);
		SceneManager.primaryStage.setWidth(screenWidth);
		SceneManager.primaryStage.setFullScreenExitHint("");
		SceneManager.primaryStage.centerOnScreen();
		SceneManager.primaryStage.show();
		SceneManager.primaryStage.setResizable(false);
	} 
	private static void getScreensize() {
//		System.out.println(System.getProperty("os.name"));
		if(System.getProperty("os.name").equals("Windows 10"))
		{
			screenHeight= Toolkit.getDefaultToolkit().getScreenSize().getHeight()*2/3;
			screenWidth= Toolkit.getDefaultToolkit().getScreenSize().getWidth()*2/3;
		}
		else
		{
			screenHeight= Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.8;
			screenWidth= Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.8;

		}

	}
	public static void gotoStartScreen() { 
		startScene=new Scene(new StartScreen(),screenWidth,screenHeight);
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
		playScene=new Scene(playScreen,screenWidth,screenHeight);
		playScreen.getGameScreen().requestFocus();
	}
	public static void gotoWaitScreen() {
		waitScene=new Scene(new WaitScreen(),screenWidth,screenHeight);
		primaryStage.setScene(waitScene);
		SceneManager.primaryStage.setFullScreen(true);
	}

	public static void gotoWinScreen() {
		winScene=new Scene(new WinScreen(),screenWidth,screenHeight);
		primaryStage.setScene(winScene);
		SceneManager.primaryStage.setFullScreen(true);
	}
	public static void gotoLoseScreen() {
		loseScene=new Scene(new LoseScreen(),screenWidth,screenHeight);
		primaryStage.setScene(loseScene);
		SceneManager.primaryStage.setFullScreen(true);
	}
	public static void gotoCharacterScreen() {
		characterDescribeScreen=new Scene(new CharacterDescribeScreen(),screenWidth,screenHeight);
		primaryStage.setScene(characterDescribeScreen);
		SceneManager.primaryStage.setFullScreen(true);
	}
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
}
