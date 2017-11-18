package Input;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {

	public static double mouseX,mouseY;
	public static String currentUI="x";
	public static KeyCode Lastkey;
	public static String currentChosed="x"; //currentChosed is called in Menubar.Choosecurrentbot 
	public static boolean mouseOnScreen = true;
	private static boolean isLeftDown = false;
	private static boolean isKeyPress = false;
	private static int tick= 10;
	public static int Maxtick= 10;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>(); 
	
	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}
	public static void setKeyPressed(KeyCode keycode,boolean pressed) {
		if(pressed){
			if(!keyPressed.contains(keycode)){
				keyPressed.add(keycode);
				Lastkey=keycode;
			}
		}else{
			keyPressed.remove(keycode);
		}
		isKeyPress=pressed;
		//System.out.println(keyPressed);
	}
	public static void mouseLeftDown(){
		isLeftDown = true;
	}
	
	public static void mouseLeftRelease(){
		isLeftDown = false;
		tick= Maxtick;
	}

	public static boolean isKeyPress() {
		return isKeyPress;
	}
	public static boolean isLeftDown() {
		return isLeftDown;
	}
	public static int getTick() {
		return tick;
	}
	public static void setTick(int tick) {
		InputUtility.tick = tick;
	}
	public static void addTick() {
		InputUtility.tick+=1;
	}
	public static void checkTick() {
		if(InputUtility.isLeftDown())InputUtility.addTick();
	}
	
}
