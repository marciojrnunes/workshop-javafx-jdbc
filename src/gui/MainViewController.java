package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import models.services.DepartmentService;

public class MainViewController implements Initializable{

	//Itens do menu da tela
	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	//Métodos para tratar cada ação nos itens do menu
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}
	
	//Função para abrir tela
	private synchronized void loadView(String absoluteName) {
		try {
			//Carrega View
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load(); 
			
			//Mostra View na janela principal
			Scene mainScene = Main.getMainScene();
			//Pega uma referencia para o Vbox dentro do s=CrollPane da janela Main
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			//Abre na janela o mainMenu e os filhos da nova janela
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		} 
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private synchronized void loadView2(String absoluteName) {
		try {
			//Carrega View
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load(); 
			
			//Mostra View na janela principal
			Scene mainScene = Main.getMainScene();
			//Pega uma referencia para o Vbox dentro do s=CrollPane da janela Main
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			//Abre na janela o mainMenu e os filhos da nova janela
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			DepartmentListController controller = loader.getController();
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		} 
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
