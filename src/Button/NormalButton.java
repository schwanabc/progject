package Button;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

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

public class NormalButton extends Canvas{
	protected static Font BUTTON_FONT = new Font("Monospace", 48);
	protected static Image ReleaseButtonBackground=new Image("file:res/releasebutton.jpg");
	protected ColorAdjust colorAdjust;
	public NormalButton(String text)
	{
		super(400, 100);
		colorAdjust=new ColorAdjust();
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.drawImage(ReleaseButtonBackground, 0, 0, 400, 100);
		gc.setFont(BUTTON_FONT);
		gc.setTextBaseline(VPos.CENTER);
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		double font_width = fontLoader.computeStringWidth(text, gc.getFont());
		gc.fillText(text, 200-font_width/2, 50);
		Addtolisterner();

	}
	protected void Addtolisterner() {
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
