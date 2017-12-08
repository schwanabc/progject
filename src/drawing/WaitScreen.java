package drawing;

import java.util.concurrent.TimeUnit;

import Button.ExitButton;
import Button.MenuButton;
import Button.NormalButton;
import Button.PlayButton;
import Button.ReplayButton;
import Scenemanager.SceneManager;
import SharedObject.RenderableHolder;
import Utility.Utility;
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
public class WaitScreen extends Pane{
	private Canvas Back;
	private PlayButton Play;
	private ExitButton Exit;
	private ReplayButton Replay;
	private Label Title;
	private static final String WAIT_TEXT="YOU WIN";
	private static Font TEXT_FONT = new Font("Monospace", 80);
	public WaitScreen()
	{
		System.out.println("Waitscreen");
		this.setPrefSize(SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT);
		Back=new Canvas(SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT);
		GraphicsContext gc=Back.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT);
		gc.drawImage(RenderableHolder.Background, 0, 0, SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT);
		Back.relocate(0, 0);
		Play=new PlayButton("Next Stage");
		Play.relocate((SceneManager.SCREEN_WIDTH-NormalButton.BUTTON_WIDTH)/2, SceneManager.SCREEN_HEIGHT*0.4);	
		Replay=new ReplayButton("Play Again");
		Replay.relocate((SceneManager.SCREEN_WIDTH-NormalButton.BUTTON_WIDTH)/2, SceneManager.SCREEN_HEIGHT*0.6);
		Exit=new ExitButton("Exit");
		Exit.relocate((SceneManager.SCREEN_WIDTH-NormalButton.BUTTON_WIDTH)/2, SceneManager.SCREEN_HEIGHT*0.8);
		Title=new Label(WAIT_TEXT);
		Title.setFont(TEXT_FONT);
		Title.setTextFill(Color.RED);
		Title.relocate(Utility.TextStartWidht(SceneManager.SCREEN_WIDTH, Utility.getFont_width(WAIT_TEXT, TEXT_FONT)), SceneManager.SCREEN_HEIGHT*0.2);
		this.getChildren().addAll(Back,Play,Replay,Exit,Title);
	}

}
