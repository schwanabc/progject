package Button;

import Scenemanager.SceneManager;
import SharedObject.RenderableHolder;
import javafx.scene.input.MouseButton;
import model_general.Board;

public class MenuButton extends NormalButton{
	public MenuButton(String text)
	{
		super(text);
		Addtolisterner();
	}
	@Override
	protected void Addtolisterner() {
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
				RenderableHolder.Clickedsound.play();
				Board.resetNumboard();
				SceneManager.gotoStartScreen();
				}
		});
	}
}
