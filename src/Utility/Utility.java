package Utility;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.text.Font;

public class Utility {
	public static double getFont_width(String text,Font font)
	{
		FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
		return  fontLoader.computeStringWidth(text, font);
	}
	public static double getTextStartWidht(double totalwidth,double fontlenght)
	{
		return (totalwidth-fontlenght)/2;
	}
}
