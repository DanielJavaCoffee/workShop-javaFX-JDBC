package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MainViewControle implements Initializable{

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
		System.out.println("menuItemDepartamento");
	}
	@FXML
	public void onMenuItemAboutAction() {
		System.out.println("menuItemAbout");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
	}
}
