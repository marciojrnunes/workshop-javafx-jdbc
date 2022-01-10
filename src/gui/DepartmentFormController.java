package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listerners.DataChangeListener;
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
import models.entities.Department;
import models.services.DepartmentService;

public class DepartmentFormController implements Initializable{

	private Department entity;
	
	private DepartmentService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();  //Lista dos listeners interessados em receber o evento
	
	//Declaração dos elementos da tela
	 @FXML
	 private TextField txtId;
	 
	 @FXML
	 private TextField txtName;
	 
	 @FXML
	 private Label labelErrorName;
	 
	 @FXML
	 private Button btSave;
	 
	 @FXML
	 private Button btCancel;
	 
	 public void setDepartment(Department entity) {
		 this.entity = entity;
	 }
	 
	 public void setDepartmentService(DepartmentService service) {
		 this.service = service;
	 }
	 
	 //Método para os objetos se inscreverem no listener
	 public void subscribeDataChangeListener(DataChangeListener listener) {
		 dataChangeListeners.add(listener);
	 }
	 
	 //Métodos para tratar os eventos dos botões
	 @FXML
	 public void onBtSaveAction(ActionEvent event) {
		 //Verifica injeção de dependências
		 if(entity == null) {
			 throw new IllegalStateException("Entity was null");
		 }
		 if(service == null) {
			 throw new IllegalStateException("Service was null");
		 }
		 try {
			 entity = getFormData();  //Pega os dados na variavel entity
			 service.saveOrUpdate(entity);
			 //Após salvar, chama os listeners
			 notifyDataChangeListeners();
			 Utils.currentStage(event).close();
		} catch (DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
		 
	 }
	 
	 private void notifyDataChangeListeners() {
	    for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private Department getFormData() {
		Department obj = new Department();
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setName(txtName.getText());
		
		return obj;
	}

	@FXML
	 public void onBtCancelAction(ActionEvent event) {
		 System.out.println("onBtCancelAction");
		 Utils.currentStage(event).close();
	 }
	 
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	//Pega os dados do departamento e popula as caixas de texto
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

}
