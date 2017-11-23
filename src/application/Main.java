package application;
import Scenemanager.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
public class Main extends Application {

	SceneManager Sm;
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.setStage(primaryStage);
			SceneManager.gotoPlayScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) { 
		launch(args);
	}

}
