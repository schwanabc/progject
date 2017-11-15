package application;
	
import Input.InputUtility;
import SharedObject.RenderableHolder;
import drawing.GameScreen;
import drawing.Menubar;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import logic.Gamelogic;
import model_attacker.Weak_1;
import model_general.Board;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	public static double SCREEN_WIDTH=800;
	public static double SCREEN_HEIGHT=600;
	@Override
	public void start(Stage primaryStage) {
		try {
			HBox root = new HBox();
			GameScreen gamescreen=new GameScreen();
			Menubar menubar=new Menubar(SCREEN_WIDTH*0.25,SCREEN_HEIGHT);
			root.getChildren().add(gamescreen);
			root.getChildren().add(menubar);
			Scene scene = new Scene(root,SCREEN_WIDTH,SCREEN_HEIGHT);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Final_Prog_METH");
			primaryStage.show();
			gamescreen.requestFocus();
			Gamelogic gamelogic=new Gamelogic();
			new AnimationTimer(){
				public void handle(long now)
				{
					gamescreen.PaintComponent();
				//	menubar.PaintComponent();
				//	menubar.update();
					gamelogic.update();
					RenderableHolder.getInstance().update();
					InputUtility.updateInputState();
				}
			}.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
