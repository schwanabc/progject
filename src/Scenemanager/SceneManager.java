package Scenemanager;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

	private  Stage primaryStage;
	private  Scene playScene;
	private  PlayScreen Playscreen;
	public static double SCREEN_HEIGHT=660;
	public static double SCREEN_WIDTH=(SCREEN_HEIGHT*4)/3;
	public SceneManager(Stage primaryStage) {
		// TODO Auto-generated constructor stub
		this.primaryStage=primaryStage;
	}

	public void gotoPlayScreen() {
		//TODO Fill Code
		InitializeplayScene();
		primaryStage.setScene(playScene);
		primaryStage.show();
		Playscreen.AT.start();
	}
	private void InitializeplayScene() {
		// TODO Auto-generated method stub
		Playscreen=new PlayScreen();
		playScene=new Scene(Playscreen,SCREEN_WIDTH,SCREEN_HEIGHT);
		Playscreen.gamescreen.requestFocus();
	}

	
}
