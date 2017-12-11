package Utility;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class InputUtility {
 
	public static double mouseX;
	public static double mouseY;
	public static String currentUI="x";
	public static KeyCode lastKey;
	public static String currentChosed="x"; //currentChosed is called in Menubar.Choosecurrentbot 
	private static boolean isLeftDown = false;
	private static boolean isKeyPress = false;
	private static int tick= 10;
	public static final int MAXTICK= tick;
	public static void setKeyPressed(KeyCode keycode) {
		lastKey=keycode;
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
		tick= MAXTICK;
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
		if(isLeftDown)addTick();
	}
	
}
