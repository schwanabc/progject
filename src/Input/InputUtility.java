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
	private static boolean isLeftClickedLastTick = false;
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
		isLeftClickedLastTick = true;
	}
	
	public static void mouseLeftRelease(){
		isLeftDown = false;
	}
	
	public static boolean isLeftClickTriggered(){
		return isLeftClickedLastTick;
	}
	
	public static void updateInputState(){
		isLeftClickedLastTick = false;
	}
	public static boolean isKeyPress() {
		return isKeyPress;
	}
	
}
