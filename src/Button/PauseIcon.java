package Button;

import Scenemanager.SceneManager;
import SharedObject.RenderableHolder;
import Utility.Utility;
import drawing.Menubar;
import drawing.PlayScreen;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseButton;
import model_general.Board;

public class PauseIcon extends Canvas implements IsButton{
	public static PauseIcon instance;
	private ColorAdjust colorAdjust;
	public static final double BUTTON_WIDTH=Menubar.MENU_WIDTH*0.2+1;
	public static final double BUTTON_HEIGHT=Menubar.ICONPOS*0.8/3;
	private boolean isClick;
	public PauseIcon() {
		super(BUTTON_WIDTH, BUTTON_HEIGHT);
		instance=this;
		isClick=false;
		colorAdjust=new ColorAdjust();
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.releasePaused, 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		addToListerner();
	}
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
					if(isClick==false)
					{
						drawPaused();
						PlayScreen.instance.Pause();
					}
					else
					{
						drawUnpaused();
						PlayScreen.instance.Pause();
					}
				}
		});
	}
	public void drawPaused() {
		isClick=true;
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.pressedPaused, 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		RenderableHolder.clickedSound.play();
	}
	public void drawUnpaused() {
		isClick=false;
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.drawImage(RenderableHolder.releasePaused, 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		RenderableHolder.clickedSound.play();
	}
}
