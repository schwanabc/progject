package drawing;

import Input.InputUtility;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import logic.Gamestate;
import model_general.Board;
public class Menubar extends VBox{
	public static double MENU_WIDTH,MENU_HEIGHT;
	private static final Font TEXT_FONT = new Font("Monospace", 20);
	private static final Font TIME_TEXT_FONT = new Font("Monospace", 20);
	private static final int VTAB=4;
	private static final int HTAB=2;
	public static double ICONPOS;
	public static double ICONHEIGHT;
	public static double ICONWIDTH;
	private Canvas[][] menu=new Canvas[VTAB][HTAB];
	private GridPane gp;
	private int choseRow;
	private int choseColumn;
	private Gamestate Gamestate;
	private Canvas Menucanvas;
	private boolean isReset;
	public Menubar(double SCREEN_WIDTH, double SCREEN_HEIGHT)
	{
		isReset=false;
		choseRow=-1;
		choseColumn=-1;
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
				gc.setFill(Color.ALICEBLUE);
				gc.fillRect(0, 0, ICONWIDTH, ICONHEIGHT);
				gc.setFont(TEXT_FONT);
				gc.setTextAlign(TextAlignment.CENTER);
				gc.setTextBaseline(VPos.CENTER);
				gc.setFill(Color.BLACK);
				if(i+1==VTAB&& j+1==HTAB)
					gc.fillText("RESET", ICONWIDTH*0.5,ICONHEIGHT*0.5);
				else {
					if(i == 0 && j == 0){
						gc.fillText("NORMAL\n"+model_attacker.Bot0.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
					}
					else if(i == 0 && j == 1){
						gc.fillText("TANK\n"+model_attacker.Bot1.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
					}
					else if(i == 1 && j == 0){
						gc.fillText("FAST\n"+model_attacker.Bot2.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
					}
					else if(i == 1 && j == 1){
						gc.fillText("DESTROY\n"+model_attacker.Bot3.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
					}
					else if(i == 2 && j == 0){
						gc.fillText("WALL\nBOMBER\n"+model_attacker.Bot4.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
					}
					else if(i == 2 && j == 1){
						gc.fillText("HQ ATK\n"+model_attacker.Bot5.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
					}
					else if(i == 3 && j == 0){
						gc.fillText("BOSS\n"+model_attacker.Bot6.getHiringCost(), ICONWIDTH*0.5,ICONHEIGHT*0.5);
					}
				}
				this.Fillborder(gc, Color.BROWN,1);
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
			setUnHover(gc,Color.ALICEBLUE);
			}
		});
		canvas.setOnMouseClicked(ev->
		{
			if(row!=choseRow ||column!=choseColumn)
			{
				try
				{
					GraphicsContext temp=menu[choseRow][choseColumn].getGraphicsContext2D();
					setUnHover(temp,Color.ALICEBLUE);
				}
				catch(Exception e) {}//Do nothing
				choseRow=row;
				choseColumn=column;
			}
			if(!(choseRow+1==VTAB&& choseColumn+1==HTAB))setHover(gc,Color.CORNFLOWERBLUE);
			else setUnHover(gc,Color.ALICEBLUE);
			Choosecurrentbot(row,column);
		});
	}
	private void Choosecurrentbot(int row, int column) {
		if(row == 0 && column == 0)
			InputUtility.currentChosed ="Weak_1";
		else if(row == 0 && column == 1)
			InputUtility.currentChosed ="Weak_2";
		else if(row == 1 && column == 0)
			InputUtility.currentChosed ="Weak_3";
		else if(row == 1 && column == 1)
			InputUtility.currentChosed ="Weak_4";
		else if(row == 2 && column == 0)
			InputUtility.currentChosed ="Weak_5";
		else if(row == 2 && column == 1)
			InputUtility.currentChosed ="Weak_6";
		else if(row == 3 && column == 0)
			InputUtility.currentChosed ="Weak_7";
		else if(row == VTAB-1 && column == HTAB-1)isReset=true;
		else InputUtility.currentChosed ="x";
	}
	private void setHover(GraphicsContext gc,Color color) {
		Fillborder(gc,color,20);
		
	}
	private void Fillborder(GraphicsContext gc, Color color,double linewidth) {
		gc.setLineWidth(linewidth);
		gc.setStroke(color);
		gc.strokeRect(0, 0, ICONWIDTH, ICONHEIGHT);
	}
	private void setUnHover(GraphicsContext gc,Color color) {
		Fillborder(gc,color,20);
		Fillborder(gc,Color.BROWN,1);
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

		if(Board.isIswin())Gamestate.setWin(true);
		GraphicsContext gc=Menucanvas.getGraphicsContext2D();
		PaintMenucanvas(gc);
		gc.setFill(Color.BLACK);
		gc.setFont(TIME_TEXT_FONT);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.TOP);
		gc.fillText("Money: "+Board.getMoney(), 5, ICONPOS*0.5);
		gc.fillText("Stage: "+(Board.getDefaultNumboard()+1), 5, ICONPOS*0.7);
		gc.setTextAlign(TextAlignment.LEFT);
		Long sec=Gamestate.getsecond();
		String second=sec.toString();
		if(sec<10)second="0"+second;
		gc.fillText("Time: "+Gamestate.getMinute()+" : "+second,ICONWIDTH-25 , ICONPOS*0.7);
//		System.out.println(Gamestate.displayTime());
		
	}
	public Gamestate getGamestate() {
		return Gamestate;
	}
	public boolean isReset() {
		return isReset;
	}
	public void setReset(boolean isReset) {
		this.isReset = isReset;
	}
	public void setdefault() {
		// TODO Auto-generated method stub
		Gamestate.initialize();
		update();
		isReset=false;
		GraphicsContext gc=menu[choseRow][choseColumn].getGraphicsContext2D();
		setUnHover(gc,Color.ALICEBLUE);
		if(choseRow==VTAB-1&&choseColumn==HTAB-1)setHover(menu[VTAB-1][HTAB-1].getGraphicsContext2D(),Color.LIGHTGREEN);
		choseRow=-1;
		choseColumn=-1;
	}
	
	
}
