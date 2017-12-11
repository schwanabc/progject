package Utility;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {
 
	public static double mouseX;
	public static double mouseY;
	public static String currentUI="x";
	public static KeyCode Lastkey;
	public static String currentChosed="x"; //currentChosed is called in Menubar.Choosecurrentbot 
	private static boolean isLeftDown = false;
	private static boolean isKeyPress = false;
	private static int Tick= 10;
	public static final int MAXTICK= Tick;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>(); 
	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}
	public static void setKeyPressed(KeyCode keycode) {
		Lastkey=keycode;
		isKeyPress=true;
	}
	public static void setKeyReleased() {
		isKeyPress=false;
	}
	public static void mouseLeftDown(){
		isLeftDown = true;
	}
	
	public static void mouseLeftRelease(){
		isLeftDown = false;
		Tick= MAXTICK;
	}

	public static boolean isKeyPress() {
		return isKeyPress;
	}
	public static boolean isLeftDown() {
		return isLeftDown;
	}
	public static int getTick() {
		return Tick;
	}
	public static void setTick(int tick) {
		InputUtility.Tick = tick;
	}
	public static void addTick() {
		InputUtility.Tick+=1;
	}
	public static void checkTick() {
		if(isLeftDown)addTick();
	}
	
}
