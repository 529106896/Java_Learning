package lab9_4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class tabPaneTestController 
{
	@FXML private Button thirdPageButton;
	@FXML private TabPane tabPane;
	//@FXML private Tab newTab;
	
	@FXML
	private void thirdPageButtonPressed(ActionEvent e)
	{
		Tab newTab = new Tab(thirdPageButton.getText());
		tabPane.getTabs().add(newTab);
	}
}
