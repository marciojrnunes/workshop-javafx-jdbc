package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.entities.Department;

public class DepartmentFormController implements Initializable{

	private Department entity;
	
	
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
	 
	 //Métodos para tratar os eventos dos botões
	 @FXML
	 public void onBtSaveAction() {
		 System.out.println("onBtSaveAction");
	 }
	 
	 @FXML
	 public void onBtCancelAction() {
		 System.out.println("onBtCancelAction");
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
