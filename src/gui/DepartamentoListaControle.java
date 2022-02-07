package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.intidade.Departamento;

public class DepartamentoListaControle implements Initializable {
	
	@FXML
	private TableView<Departamento> tableViewDepartamento;
	@FXML
	private TableColumn<Departamento, Integer> tableColumnDepartamentoID;
	@FXML
	private TableColumn<Departamento, String> tableColumnDepartamentoNome;
	@FXML
	private Button btNew;
	
	public void anBtNewAction() {
		System.out.println("XXXXXXXXXX");
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}


	private void initializeNodes() {
		tableColumnDepartamentoID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnDepartamentoNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
	}

}
