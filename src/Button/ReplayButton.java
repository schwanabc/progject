package Button;

import Scenemanager.SceneManager;
import javafx.scene.input.MouseButton;
import model_general.Board;

public class ReplayButton extends NormalButton{
	public ReplayButton(String text)
	{
		super(text);
		Addtolisterner();
	}
	@Override
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
				Board.decreaseNumboard();
				SceneManager.gotoPlayScreen();
				}
		});
	}
}