package drawing;

import Input.InputUtility;
import SharedObject.IRenderable;
import SharedObject.RenderableHolder;
import application.Main;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model_general.Board;

public class Menubar extends UI{
	private static double MENU_WIDTH,MENU_HEIGHT;
	private static final Font TEXT_FONT = new Font("Monospace", 30);
	public Menubar(double SCREEN_WIDTH, double SCREEN_HEIGHT)
	{
		super(SCREEN_WIDTH,SCREEN_HEIGHT);
		MENU_WIDTH=SCREEN_WIDTH;
		MENU_HEIGHT=SCREEN_HEIGHT;
		this.setVisible(true);
	}
	public void PaintComponent()
	{
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.setFill(Color.LIGHTBLUE);
		gc.setFont(TEXT_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.fillRect(0, 0,MENU_WIDTH, MENU_HEIGHT);
		gc.setFill(Color.BLACK);
		gc.fillText("MENU", MENU_WIDTH*0.5,MENU_HEIGHT*0.1);
	}
	@Override
	public void addListerner()
	{
		super.addListerner();
		this.setOnMousePressed((MouseEvent event) -> {
			if (event.getButton() == MouseButton.PRIMARY)
				InputUtility.mouseLeftDown("MENU");
		});
	}
	public static boolean isInMenuscreen() {
		// TODO Auto-generated method stub
		if(InputUtility.currentUI.equals("MENU"))return true;
		return false;
	}
}
