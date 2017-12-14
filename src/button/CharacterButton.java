package button;

import javafx.scene.input.MouseButton;
import scenemanager.SceneManager;
import sharedobject.RenderableHolder;

public class CharacterButton extends NormalButton{
	public CharacterButton(String text)
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
					System.out.print("characterclick");
					RenderableHolder.clickedSound.play();
					SceneManager.gotoCharacterScreen();
				}
		});
	}
}
