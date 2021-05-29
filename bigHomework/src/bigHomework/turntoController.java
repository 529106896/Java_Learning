package bigHomework;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class turntoController implements Initializable
{
	@FXML private TextField turntoTextField;
	@FXML private Button turntoConfirmButton;
	@FXML private Button turntoCancelButton;
	
	public Global global;
	public notepadController controller;
	public int targetTurnto;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		global = (Global) Main.globals.get("global");
		controller = (notepadController) Main.controllers.get(notepadController.class.getSimpleName());
	}
	
	@FXML
	private void turntoConfirmButtonPressed()
	{
		targetTurnto = Integer.parseInt(turntoTextField.getText());
		global.turnto = targetTurnto;
		controller.turnto();
		controller.turntoClose();
	}
	
	@FXML
	private void turntoCancelButtonPressed()
	{
		controller.turntoClose();
	}
}
