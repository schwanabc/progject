package Button;

import Scenemanager.SceneManager;
import SharedObject.RenderableHolder;
import javafx.scene.input.MouseButton;
import model_general.Board;

public class MenuButton extends NormalButton{
	public MenuButton(String text)
	{
		super(text);
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
				RenderableHolder.clickedSound.play();
				Board.resetNumBoard();
				SceneManager.gotoStartScreen();
				}
		});
	}
}
