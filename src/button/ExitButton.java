package button;

import javafx.scene.input.MouseButton;
import scenemanager.SceneManager;
import sharedobject.RenderableHolder;

public class ExitButton extends NormalButton{
	public ExitButton(String text)
	{
		super(text);
	}
	@Override
	public void addToListener() {
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
				System.exit(0);
				}
		});
	}
}
