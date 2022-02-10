package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import gui.listener.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.exception.ValidationException;
import model.services.DepartamentoService;

public class DepartmentFormControle implements Initializable {

	private Department entity;
	private DepartamentoService service;
	private List<DataChangeListener> list = new ArrayList<>();

	@FXML
	private TextField txtid;
	@FXML
	private TextField txtNome;
	@FXML
	private Label labelErroName;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btCancelar;

	@FXML

	public void setDepartamentoService(DepartamentoService service) {
		this.service = service;
	}

	public void setDepartamento(Department departamento) {
		this.entity = departamento;
	}

	public void setChangeDateListener(DataChangeListener listener) {
		list.add(listener);
	}

	public void onbtSalvarAction(ActionEvent event) {

		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}

		try {

			entity = getFormDate();
			service.save(entity);
			notifyDataChangeListener();
			Utils.currentsStage(event).close();

		} catch (ValidationException e) {
			setErroMessages(e.getErros());

		} catch (Exception e) {
			Alerts.showAlert("Erro de salvar", null, e.getMessage(), AlertType.ERROR);
		}
	}

	private void notifyDataChangeListener() {

		for (DataChangeListener listener : list) {
			listener.onDataChange();
		}

	}

	private Department getFormDate() {

		ValidationException exception = new ValidationException("Validation Error");

		Department departamento = new Department();

		departamento.setId(Utils.tryParseToInt(txtid.getText()));
		
		if (txtNome.getText().isBlank() || txtNome.getText().trim().equals("")) {
			exception.addErro("Nome", "Preencha o campo em branco!");
		}
		departamento.setName(txtNome.getText());

		if (exception.getErros().size() > 0) {
			throw exception;
		}

		return departamento;
	}

	@FXML
	public void onbtCancelarAction(ActionEvent event) {
		Utils.currentsStage(event).close();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtid);
		Constraints.setTextFieldMaxLength(txtNome, 30);
	}

	public void updateFormData() {

		if (entity == null) {
			throw new IllegalStateException("Objeto vazio");
		}
		txtid.setText(String.valueOf(entity.getId()));
		txtNome.setText(entity.getName());
	}

	private void setErroMessages(Map<String, String> error) {
		Set<String> fields = error.keySet();

		if (fields.contains("Nome")) {
			labelErroName.setText(error.get("Nome"));
		}

	}
}
