package bigHomework;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class searchController implements Initializable
{
	@FXML private Button searchButton;
	@FXML private Button previousSearchButton;
	@FXML private Button replaceButton;
	@FXML private Button replaceAllButton;
	@FXML private CheckBox IgnoreCaseBox;
	
	@FXML TextField searchTextField;
	@FXML TextField replaceTextField;
	
	private String searchString;
	private String replaceString;
//	private boolean isCaseIgnored;
	
	public Global global;
	public notepadController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		global = (Global)Main.globals.get("global");
		controller = (notepadController)Main.controllers.get(notepadController.class.getSimpleName());
		//isCaseIgnored = IgnoreCaseBox.isSelected();
	}
	
	//搜索功能
	@FXML
	private void search()
	{
		searchString = searchTextField.getText();
		replaceString = replaceTextField.getText();
		global.search = searchString;
		global.replace = replaceString;
		global.isCaseIgnored = IgnoreCaseBox.isSelected();
		controller.search();
	}
	
	@FXML
	private void previousSearch()
	{
		searchString = searchTextField.getText();
		replaceString = replaceTextField.getText();
		global.search = searchString;
		global.replace = replaceString;
		global.isCaseIgnored = IgnoreCaseBox.isSelected();
		controller.previousSearch();
	}
	
	//替换功能
	@FXML
	private void replace()
	{
		searchString = searchTextField.getText();
		replaceString = replaceTextField.getText();
		global.search = searchString;
		global.replace = replaceString;
		global.isCaseIgnored = IgnoreCaseBox.isSelected();
		controller.replace();
	}
	
	//替换全部功能
	@FXML
	private void replaceAll()
	{
		searchString = searchTextField.getText();
		replaceString = replaceTextField.getText();
		global.search = searchString;
		global.replace = replaceString;
		global.isCaseIgnored = IgnoreCaseBox.isSelected();
		controller.replaceAll();
	}
}
