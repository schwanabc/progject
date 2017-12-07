package drawing;

import Scenemanager.SceneManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class PlayButton extends Canvas{
	private static Font BUTTON_FONT = new Font("Monospace", 48);
	private static Image ReleaseButtonBackground=new Image("file:res/releasebutton.jpg");
	private static ColorAdjust colorAdjust = new ColorAdjust();
	public PlayButton(String text)
	{
		super(400, 100);
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.drawImage(ReleaseButtonBackground, 0, 0, 400, 100);
		gc.setFont(BUTTON_FONT);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText(text, 150, 50);
		//setOpacity(0.7);
		
		this.setOnMouseEntered(ev->
		{
			colorAdjust.setBrightness(0.1);
			this.setEffect(colorAdjust);
		});
		this.setOnMouseExited(ev->
		{
			colorAdjust.setBrightness(0);
			this.setEffect(colorAdjust);
		});
		this.setOnMouseClicked(ev->
		{
			if(ev.getButton()==MouseButton.PRIMARY)
				{
				SceneManager.gotoPlayScreen();
				}
		});

	}
}
