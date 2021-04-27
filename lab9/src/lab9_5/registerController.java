package lab9_5;

import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class registerController 
{
	@FXML private Button confirmationButton;
	@FXML private Button closeButton;
	@FXML private TextField userNameTextField;
	@FXML private TextField passwordTextField;
	@FXML private TextField confirmPasswordTextField;
	
	private String userName;
	private String password;
	private String confirmPassword;
	private Boolean isRegisterSuccessful = false;
	
	static ArrayList<String> wrongInformation = new ArrayList<>();
	static ArrayList<Throwable> exceptionList = new ArrayList<>();
	
	@FXML
	private void closeButtonPressed(ActionEvent e)
	{
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private void confirmationButtonPressed(ActionEvent e)
	{
		try
		{
			userName = userNameTextField.getText();
			if(userName.length() == 0)
			{
				wrongInformation.add("请输入用户名！");
				throw new userNameException();
			}
			else if(userName.length() < 4)
			{
				wrongInformation.add("用户名长度不能小于4！");
				throw new userNameException();
			}
		}
		catch(userNameException e1)
		{
			exceptionList.add(e1);
		}
		
		try
		{
			password = passwordTextField.getText();
			confirmPassword = confirmPasswordTextField.getText();
			if(password.length() == 0)
			{
				wrongInformation.add("请输入密码！");
				throw new passwordConfirmException();
			}
			else if(!password.equals(confirmPassword))
			{
				wrongInformation.add("两次密码输入不一致！");
				throw new passwordConfirmException();
			}
		}
		catch(passwordConfirmException e1)
		{
			exceptionList.add(e1);
		}
		
		try
		{
			if(exceptionList.size() > 0)
			{
				isRegisterSuccessful = false;
				throw new Exception();
			}
				
			else
				isRegisterSuccessful = true;
		}
		catch(Exception e1)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("注册失败！");
			alert.setHeaderText("请参照以下提示更新您的注册信息：");
			String contextText = wrongInformation.get(0);
			for(int i = 1; i < wrongInformation.size(); i++)
			{
				contextText += ("\n" + wrongInformation.get(i));
			}
			alert.setContentText(contextText);
			exceptionList.clear();
			wrongInformation.clear();
			alert.showAndWait();
		}
		
		if(isRegisterSuccessful == true)
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("注册成功！");
			alert.setHeaderText("您的用户信息如下：");
			StringBuilder passwordHidden = new StringBuilder(password.length());
			for(int i = 0; i < password.length(); i++)
			{
				passwordHidden.append('*');
			}
			alert.setContentText("用户名：" + userName + "\n" + "密码：" + passwordHidden);
			ButtonType buttonTypeOne = new ButtonType("显示密码");
			ButtonType buttonTypeCancel = new ButtonType("确认", ButtonData.CANCEL_CLOSE);
			 
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
			 
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne)
			{
				alert.setContentText("用户名：" + userName + "\n" + "密码：" + password);
				alert.getButtonTypes().clear();
				alert.getButtonTypes().setAll(buttonTypeCancel);
				alert.showAndWait();
			} 
		}
	}
}
