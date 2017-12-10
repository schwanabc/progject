package Button;

import Scenemanager.PlayScreen;
import Scenemanager.SceneManager;
import SharedObject.RenderableHolder;
import Utility.Utility;
import drawing.Menubar;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseButton;
import model_general.Board;

public class ExitIcon extends Canvas implements IsButton{
	protected ColorAdjust colorAdjust;
	public static final double BUTTON_WIDTH=Menubar.MENU_WIDTH*0.2;
	public static final double BUTTON_HEIGHT=Menubar.ICONPOS*0.8/3;

	public ExitIcon() {
		super(BUTTON_WIDTH, BUTTON_HEIGHT);
		colorAdjust=new ColorAdjust();
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.ReleaseExit, 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		Addtolisterner();
	}
	public void Addtolisterner() {
		this.setOnMouseEntered(ev->
		{
			RenderableHolder.Buttonhover.play();
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
				GraphicsContext gc=this.getGraphicsContext2D();
				gc.drawImage(RenderableHolder.PressedExit, 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
				Board.resetNumboard();
				RenderableHolder.StopAudio();
				RenderableHolder.Clickedsound.play();
				PlayScreen.instance.Forceend();
				System.exit(0);
				}
		});
	}
}
