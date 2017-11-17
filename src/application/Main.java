package application;
import Scenemanager.SceneManager;
import java.util.concurrent.TimeUnit;

import Input.InputUtility;
import SharedObject.RenderableHolder;
import drawing.GameScreen;
import drawing.Menubar;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.Gamelogic;
import model_general.Board;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import model_general.Gamestate;
import model_general.Gamestate;
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoPlayScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}

}
