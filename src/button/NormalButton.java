package button;

import java.io.InputStream;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

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
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import scenemanager.SceneManager;
import sharedobject.RenderableHolder;
import utility.Utility;

public class NormalButton extends Canvas implements IButton{
	private static final Font BUTTON_FONT = Font.loadFont(ClassLoader.getSystemResourceAsStream("Pamela.ttf"), 48);
	protected ColorAdjust colorAdjust;
	public static final double BUTTON_WIDTH=400;
	public static final double BUTTON_HEIGHT=100;
	public NormalButton(String text)
	{
		super(BUTTON_WIDTH, BUTTON_HEIGHT);
		colorAdjust=new ColorAdjust();
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.releaseButtonBackground, 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		gc.setFont(BUTTON_FONT);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText(text,Utility.getTextStartWidht(BUTTON_WIDTH,Utility.getFont_width(text,gc.getFont())), BUTTON_HEIGHT/2);
		addToListerner();

	}
	@Override
	public void addToListerner() {
		this.setOnMouseEntered(ev->
		{
			RenderableHolder.buttonHover.play();
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
				RenderableHolder.stopAudio();
				RenderableHolder.clickedSound.play();
				SceneManager.gotoPlayScreen();
				}
		});
	}
}
