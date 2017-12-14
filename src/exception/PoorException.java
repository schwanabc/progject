package exception;

import drawing.Menubar;
import drawing.PlayScreen;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.Alert.AlertType;

public class PoorException extends Exception {
	public PoorException() {
		String text="YOU CANT AFFORD THIS UNIT";
		Menubar.instance.writePoor(text);
	}

}
