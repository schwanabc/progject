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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model_general.Board;
import model_general.Gamestate;
public class Menubar extends VBox{
	private static double MENU_WIDTH,MENU_HEIGHT;
	private static final Font TEXT_FONT = new Font("Monospace", 30);
	private static final Font TIME_TEXT_FONT = new Font("Monospace", 20);
	private static final int VTAB=4;
	private static final int HTAB=2;
	private static double ICONPOS;
	private static double ICONHEIGHT;
	private static double ICONWIDTH;
	private static final Canvas[][] menu=new Canvas[VTAB][HTAB];
	private GridPane gp;
	private static int choseRow=-1;
	private static int choseColumn=-1;
	private Gamestate Gamestate;
	private Canvas Menucanvas;
	public Menubar(double SCREEN_WIDTH, double SCREEN_HEIGHT)
	{
		Gamestate=new Gamestate();
		MENU_WIDTH=SCREEN_WIDTH;
		MENU_HEIGHT=SCREEN_HEIGHT;
		ICONPOS= MENU_HEIGHT*0.2;
		ICONHEIGHT=(MENU_HEIGHT-ICONPOS)/VTAB;
		ICONWIDTH=MENU_WIDTH/HTAB;
		setMenu();
		
	}
	private void setMenu() {
		setMenutab();
		setIcon();
	}
	private void setIcon() {
		gp=new GridPane();
		int count=0;
		for(int i=0;i<VTAB;i++)
		{
			for(int j=0;j<HTAB;j++)
			{
				menu[i][j]=new Canvas(ICONWIDTH,ICONHEIGHT);
				GraphicsContext gc=menu[i][j].getGraphicsContext2D();
				if((i+j)%2==0)gc.setFill(Color.LIGHTGRAY);
				else gc.setFill(Color.ALICEBLUE);
				gc.fillRect(0, 0, ICONWIDTH, ICONHEIGHT);
				gc.setFont(TEXT_FONT);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setTextBaseline(VPos.CENTER);
				gc.setFill(Color.BLACK);
				gc.fillText("BOT"+count++, ICONWIDTH*0.5,ICONHEIGHT*0.5);
				gp.add(menu[i][j], j, i);
				Checkevent(menu[i][j],i,j);
			}
		}
		this.getChildren().add(gp);
	}
	private void Checkevent(Canvas canvas, int row, int column) {
		GraphicsContext gc=canvas.getGraphicsContext2D();
		canvas.setOnMouseEntered(ev->
		{
			if(!(row==choseRow &&column==choseColumn))
			setHover(gc,Color.LIGHTGREEN);
		});
		canvas.setOnMouseExited(ev->
		{
			if(!(row==choseRow &&column==choseColumn))
			{
			if((row+column)%2==0)setHover(gc,Color.LIGHTGRAY);
			else setHover(gc,Color.ALICEBLUE);
			}
		});
		canvas.setOnMouseClicked(ev->
		{
			if(row!=choseRow ||column!=choseColumn)
			{
				try
				{
					GraphicsContext temp=menu[choseRow][choseColumn].getGraphicsContext2D();
					if((choseRow+choseColumn)%2==0)setHover(temp,Color.LIGHTGRAY);
					else setHover(temp,Color.ALICEBLUE);
				}
				catch(Exception e) {}//Do nothing
				choseRow=row;
				choseColumn=column;
			}
			setHover(gc,Color.CORNFLOWERBLUE);
			Choosecurrentbot(row,column);
		});
	}
	private void Choosecurrentbot(int row, int column) {
		if(row==0&&column==0)InputUtility.currentChosed ="Weak_1";
		else if(row==0&&column==1)InputUtility.currentChosed ="Weak_2";
		else InputUtility.currentChosed ="x";
	}
	private void setHover(GraphicsContext gc,Color color) {
		gc.setLineWidth(20);
		gc.setStroke(color);
		gc.strokeRect(0, 0, ICONWIDTH, ICONHEIGHT);
	}
	private void PaintMenucanvas(GraphicsContext gc)
	{
		gc.setFill(Color.ALICEBLUE);
		gc.fillRect(0, 0, MENU_WIDTH, ICONPOS);
		gc.setFont(TEXT_FONT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.BOTTOM);
		gc.setFill(Color.BLACK);
		gc.fillText("MENU", MENU_WIDTH*0.5,ICONPOS*0.5);
	}
	public void setMenutab() {
		Menucanvas=new Canvas(MENU_WIDTH,ICONPOS);
		GraphicsContext gc=Menucanvas.getGraphicsContext2D();
		PaintMenucanvas(gc);
		
		this.getChildren().add(Menucanvas);
	}
	public void update() {
		// TODO Auto-generated method stub
		if(Gamestate.getRemainingNanoTime()<=0)
		{
			Gamestate.setLose(true);
		}
		if(Board.isIswin())
		{
			Gamestate.setWin(true);
		}
		GraphicsContext gc=Menucanvas.getGraphicsContext2D();
		PaintMenucanvas(gc);
		gc.setFill(Color.BLACK);
		gc.setFont(TIME_TEXT_FONT);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.TOP);
		gc.fillText("Time left: "+Gamestate.displayTime(), 5, ICONPOS*0.5);
//		System.out.println(Gamestate.displayTime());
		
	}
	public Gamestate getGamestate() {
		return Gamestate;
	}
	public void setGamestate(Gamestate gamestate) {
		Gamestate = gamestate;
	}
	
	
}
