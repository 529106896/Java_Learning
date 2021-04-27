package lab8_3;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogsController 
{
	//一般的消息对话框
	public void informationDialogButtonPressed(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("这是一个消息对话框");
		alert.setContentText("Incoming message");
		 
		alert.showAndWait();
	}
	
	//没有标题的消息对话框
	public void withoutHeaderInfoDialogButtonPressed(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Without Header Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Incoming message");

		alert.showAndWait();
	}
	
	//警告对话框
	public void warningDialogButtonPressed(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText("这是一个警告");
		alert.setContentText("Be careful!");
		 
		alert.showAndWait();
	}
	
	//错误对话框
	public void errorDialogButtonPressed(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("程序报告了一个错误");
		alert.setContentText("404 Not Found!");
		 
		alert.showAndWait();
	}
	
	//异常消息处理框
	
}
