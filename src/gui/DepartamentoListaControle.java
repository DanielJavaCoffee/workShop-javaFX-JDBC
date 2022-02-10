package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbIntegrityException;
import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartamentoService;

public class DepartamentoListaControle implements Initializable, DataChangeListener {

	private DepartamentoService service;
	@FXML
	private TableView<Department> tableViewDepartamento;
	@FXML
	private TableColumn<Department, Integer> tableColumnDepartamentoID;
	@FXML
	private TableColumn<Department, String> tableColumnDepartamentoNome;
	@FXML
	private Button btNew;
	@FXML
	private TableColumn<Department, Department> tableColumnEDIT;
	@FXML
	private TableColumn<Department, Department> tableColumnREMOVE;

	private ObservableList<Department> obsList;

	public void setDepartamentoService(DepartamentoService service) {
		this.service = service;
	}

	@FXML
	public void anBtNewAction(javafx.event.ActionEvent event) {
		Stage parentStage = Utils.currentsStage(event);
		Department departamento = new Department();
		createDialogForm(departamento, "/gui/DepartmentForm.fxml", parentStage);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColumnDepartamentoID.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnDepartamentoNome.setCellValueFactory(new PropertyValueFactory<>("Name"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartamento.prefHeightProperty().bind(stage.heightProperty());
	}

	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service null");
		}

		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartamento.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}

	private void createDialogForm(Department departamento, String absoluteName, Stage parentStage) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = fxmlLoader.load();

			DepartmentFormControle controle = fxmlLoader.getController();
			controle.setDepartamento(departamento);
			controle.setDepartamentoService(new DepartamentoService());
			controle.setChangeDateListener(this);
			controle.updateFormData();

			Stage stage = new Stage();
			stage.setTitle("Enter Department data");
			stage.setScene(new Scene(pane));
			stage.setResizable(false);
			stage.initOwner(parentStage);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.showAndWait();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro de carregamento de tela", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChange() {
		updateTableView();
	}

	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<Department, Department>() {
			private final Button button = new Button("edit");

			@Override
			protected void updateItem(Department obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/DepartmentForm.fxml", Utils.currentsStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<Department, Department>() {
			private final Button button = new Button("remove");

			@Override
			protected void updateItem(Department obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}

		});
	}

	private void removeEntity(Department obj) {
		Optional<ButtonType> resultado =  Alerts.showConfirmation("confirma", "Tem certeza que você deseja deletar esse departamento?");
		
		if(resultado.get() == ButtonType.OK) {
			if(service == null) {
				throw new IllegalStateException("Service was null");
			}
			try {
			service.remove(obj);
			updateTableView();
			} catch (DbIntegrityException e) {
				Alerts.showAlert("Erro de remoção", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}
}
