package drawing;

import javafx.application.Platform;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import Scenemanager.SceneManager;
public class WinScreen extends StackPane{
	Canvas canvas,canvas2;
	public WinScreen()
	{
		canvas=new Canvas(SceneManager.SCREEN_WIDTH,SceneManager.SCREEN_HEIGHT);
		GraphicsContext gc=canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, SceneManager.SCREEN_WIDTH,SceneManager.SCREEN_HEIGHT);
		Font TEXT_FONT = new Font("Monospace", 100);
		gc.setFont(TEXT_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setFill(Color.BLACK);
		gc.fillText("YOU WIN", SceneManager.SCREEN_WIDTH*0.5,SceneManager.SCREEN_HEIGHT*0.3);
		this.getChildren().add(canvas);
		canvas2=new Canvas(SceneManager.SCREEN_WIDTH,SceneManager.SCREEN_HEIGHT);
		gc=canvas2.getGraphicsContext2D();
		gc.setFill(Color.ALICEBLUE);
		gc.fillRect(SceneManager.SCREEN_WIDTH*0.2, SceneManager.SCREEN_HEIGHT*0.5+10, 535, 100);
		TEXT_FONT = new Font("Monospace", 60);
		gc.setFill(Color.BLACK);
		gc.setFont(TEXT_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillText("Click to end game", SceneManager.SCREEN_WIDTH*0.5,SceneManager.SCREEN_HEIGHT*0.6);
		canvas2.requestFocus();
		AddtoListerner(canvas2);
		this.getChildren().add(canvas2);
	}
	private void AddtoListerner(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.setOnMouseClicked(ev-> {
			if (ev.getButton()== MouseButton.PRIMARY)
			{
				Platform.exit();
			}
		});
	}
}
