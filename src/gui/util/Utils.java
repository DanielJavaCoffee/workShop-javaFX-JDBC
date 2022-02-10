package gui.util;

import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	public static Stage currentsStage(javafx.event.ActionEvent actionEvent) {
		return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
	}

	public static Integer tryParseToInt(String texto) {
		try {
			return Integer.parseInt(texto);
		} catch (NumberFormatException e) {
			return null;
		}

	}

}
