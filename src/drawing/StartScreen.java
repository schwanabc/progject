package drawing;

import java.util.concurrent.TimeUnit;

import Button.ExitButton;
import Button.PlayButton;
import Scenemanager.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class StartScreen extends Pane{
	private static Image Background=new Image("file:res/Wiki-background.jpg");	
	private Canvas Back;
	private PlayButton Play;
	private ExitButton Exit;
	private Label Gametitle;
	private static Font TEXT_FONT = new Font("Monospace", 80);
	public StartScreen()
	{
		System.out.println("startscreen");
		this.setPrefSize(SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT);
		Back=new Canvas(SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT);
		GraphicsContext gc=Back.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT);
		gc.drawImage(Background, 0, 0, SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT);
		Back.relocate(0, 0);
		Play=new PlayButton("Play");
		Play.relocate(SceneManager.SCREEN_WIDTH*0.3, SceneManager.SCREEN_HEIGHT*0.4);	
		Exit=new ExitButton("Exit");
		Exit.relocate(SceneManager.SCREEN_WIDTH*0.3, SceneManager.SCREEN_HEIGHT*0.6);
		Gametitle=new Label(SceneManager.GAMENAME);
		Gametitle.setFont(TEXT_FONT);
		Gametitle.setTextFill(Color.RED);
		Gametitle.relocate(SceneManager.SCREEN_WIDTH*0.1, SceneManager.SCREEN_HEIGHT*0.1);
		this.getChildren().addAll(Back,Play,Exit,Gametitle);
	}

}
