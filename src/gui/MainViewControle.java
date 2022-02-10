package gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartamentoService;

public class MainViewControle implements Initializable {

	@FXML
	private MenuItem menuItemVendedor;
	@FXML
	private MenuItem menuItemDepartamento;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemVendedorAction() {
		System.out.println("menuItemVendedor");
	}

	@FXML
	public void onMenuItemDepartamentoAction() {
		loadView("/gui/ListarDepartamento.fxml", (DepartamentoListaControle controle) -> {
			controle.setDepartamentoService(new DepartamentoService());
			controle.updateTableView();
		});
		
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializinAction) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = fxmlLoader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T contole = fxmlLoader.getController();
			initializinAction.accept(contole);

		} catch (Exception e) {

			Alerts.showAlert("IO Exception", "Erro de carregamento de tela", e.getMessage(), AlertType.ERROR);
		}
	}

	

}
