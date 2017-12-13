package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import scenemanager.SceneManager;
import sharedobject.RenderableHolder;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			SceneManager.setStage(primaryStage);
			SceneManager.gotoStartScreen();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void stop()
	{
		System.out.println("end");
		RenderableHolder.stopAudio();
	    System.exit(0);
	}
	public static void main(String[] args) { 
		launch(args);
	}

}
