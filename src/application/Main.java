package application;
	
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


public class Main extends Application {
	public static double SCREEN_WIDTH=800;
	public static double SCREEN_HEIGHT=600;
	public static AnimationTimer AT,AT2;
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
			AT=new AnimationTimer(){
				public void handle(long now)
				{
					gamelogic.update();
					gamescreen.PaintComponent();
					InputUtility.updateInputState();
					RenderableHolder.getInstance().update();
					if(Board.isIswin())
						{
							Pausewin(gamescreen);
						}
				}
			};
			AT2=new AnimationTimer(){
				public void handle(long now)
				{
					Board.update();
					gamescreen.PaintComponent();
					RenderableHolder.getInstance().update();
					InputUtility.updateInputState();
				}
			};
			AT.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void Pausewin(GameScreen gamescreen) {
		// TODO Auto-generated method stub
		gamescreen.PaintWinScreen();
		application.Main.AT.stop();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
