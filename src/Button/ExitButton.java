package Button;

import Scenemanager.SceneManager;
import javafx.scene.input.MouseButton;

public class ExitButton extends NormalButton{
	public ExitButton(String text)
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
				System.exit(0);
				}
		});
	}
}
