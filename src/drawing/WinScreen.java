package drawing;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import Button.ExitButton;
import Button.MenuButton;
import Button.NormalButton;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
public class WinScreen extends Pane{
	private ExitButton Exit;
	private ReplayButton Replay;
	private MenuButton Menu;
	private Label Title;
	private static final String WIN_TEXT ="YOU CLEAR THE GAME!!!";
	private static InputStream fontStream = ClassLoader.getSystemResourceAsStream("Penumbra-HalfSerif-Std_35114.ttf");
	private static final Font TEXT_FONT = Font.loadFont(fontStream, 80);
	public WinScreen()
	{
		RenderableHolder.Victory.play();
		System.out.println("Winscreen");
		this.setPrefSize(SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.Victorybackground, null, null, null, new BackgroundSize(SceneManager.SCREEN_WIDTH, SceneManager.SCREEN_HEIGHT,true,true,true,true))));
		Replay=new ReplayButton("Play Again");
		Replay.relocate((SceneManager.SCREEN_WIDTH-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.SCREEN_HEIGHT*0.4);
		Menu=new MenuButton("Main Menu");
		Menu.relocate((SceneManager.SCREEN_WIDTH-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.SCREEN_HEIGHT*0.6);
		Exit=new ExitButton("Exit");
		Exit.relocate((SceneManager.SCREEN_WIDTH-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.SCREEN_HEIGHT*0.8);
		Title=new Label(WIN_TEXT);
		Title.setFont(TEXT_FONT);
		Title.setTextFill(Color.RED);
		Title.relocate(1.5*Utility.TextStartWidht(SceneManager.SCREEN_WIDTH, Utility.getFont_width(WIN_TEXT, TEXT_FONT)), SceneManager.SCREEN_HEIGHT*0.2);
		this.getChildren().addAll(Menu,Replay,Exit,Title);
	}

}
