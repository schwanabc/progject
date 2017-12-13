package drawing;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import button.ExitButton;
import button.MenuButton;
import button.NormalButton;
import button.PlayButton;
import button.ReplayButton;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import scenemanager.SceneManager;
import sharedobject.RenderableHolder;
import utility.Utility;
public class characterDescribeScreen extends Pane{
	private PlayButton Play;
	private MenuButton Menu;
	private Label Title;
	private Label characterName[] = new Label[7];
	private Label characterDescribeText[] = new Label[7];
	private Image characterImage[] = new Image[7];
	private String headerText = "Character Describe";
	public characterDescribeScreen()
	{
		RenderableHolder.stopAudio();
		RenderableHolder.openSong.play();
		System.out.println("Character Describe");
		this.setPrefSize(SceneManager.screenWidth, SceneManager.screenHeight);
		this.setBackground(new Background(new BackgroundImage(RenderableHolder.background, null, null, null, new BackgroundSize(SceneManager.screenWidth, SceneManager.screenHeight,true,true,true,true))));
		Play=new PlayButton("Play");
		Play.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*0.5/2, SceneManager.screenHeight*0.8);	
		Menu=new MenuButton("Main Menu");
		Menu.relocate((SceneManager.screenWidth-NormalButton.BUTTON_WIDTH)*1.5/2, SceneManager.screenHeight*0.8);
		Title=new Label(headerText);
		characterImage[0] = RenderableHolder.armed_Peasant;
		
		Title.setFont(RenderableHolder.SCREENTEXTFONT);
		Title.setTextFill(Color.RED);
		Title.relocate(Utility.getTextStartWidht(SceneManager.screenWidth, Utility.getFont_width(headerText, RenderableHolder.SCREENTEXTFONT)), SceneManager.screenHeight*0.05);
		this.getChildren().addAll(Menu,Play,Title);
	}

}
