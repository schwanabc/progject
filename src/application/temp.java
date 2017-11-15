/*
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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model_general.Board;

public class Menubar extends UI{
	private static double MENU_WIDTH,MENU_HEIGHT;
	private static final Font TEXT_FONT = new Font("Monospace", 30);
	private static final int VTAB=4;
	private static final int HTAB=2;
	private static double ICONPOS;
	private static double ICONHEIGHT;
	private static double ICONWIDTH;
	private static final int[][] menu=new int[VTAB][HTAB];
	GridPane gp=new GridPane();
	public Menubar(double SCREEN_WIDTH, double SCREEN_HEIGHT)
	{
		super(SCREEN_WIDTH,SCREEN_HEIGHT);
		MENU_WIDTH=SCREEN_WIDTH;
		MENU_HEIGHT=SCREEN_HEIGHT;
		ICONPOS= MENU_HEIGHT*0.2;
		ICONHEIGHT=(MENU_HEIGHT-ICONPOS)/VTAB;
		ICONWIDTH=MENU_WIDTH/HTAB;
		this.setVisible(true);
	}
	public void PaintComponent()
	{
		GraphicsContext gc=this.getGraphicsContext2D();
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(0, 0, MENU_WIDTH, MENU_HEIGHT);
		gc.setFont(TEXT_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		for(int i=0;i<VTAB;i++)
		{
			for(int j=0;j<HTAB;j++)
			{
				if((i+j)%2==0)gc.setFill(Color.LIGHTGRAY);
				else gc.setFill(Color.LIGHTCORAL);
				gc.fillRect(ICONWIDTH*j, ICONPOS+ICONHEIGHT*i,ICONWIDTH, ICONHEIGHT);
				gc.setFill(Color.BLACK);
				gc.fillText("BOT"+(2*i+j), ICONWIDTH*(j+0.5),ICONPOS+ICONHEIGHT*(i+0.5));
			}
		}
		gc.setFill(Color.BLACK);
		gc.fillText("MENU", MENU_WIDTH*0.5,MENU_HEIGHT*0.1);
	}
	@Override
	public void addListerner()
	{
		super.addListerner();
		this.setOnMouseEntered((MouseEvent event) -> {
			InputUtility.mouseOnScreen = true;
			InputUtility.currentUI="MENU";
		});
	}
	public boolean isInMenuscreen() {
		// TODO Auto-generated method stub
		if(InputUtility.currentUI.equals("MENU"))return true;
		return false;
	}
	public void update()
	{

	if(isInMenuscreen())
		{
			double mouseX=InputUtility.mouseX;
			double mouseY=InputUtility.mouseY;
			if(InputUtility.mouseOnScreen && isInchoosetab(mouseY))
			{
				System.out.println("IN CHOOSE");
			}
		}
	}
	private boolean isInchoosetab(double mouseY) {
		// TODO Auto-generated method stub
		if(mouseY>=ICONPOS)return true;
		return false;
	}
	
	
}
*/