package lab10_2;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

public class registerController 
{
	@FXML private TextField userNameTextField;
	@FXML private PasswordField passwordTextField;
	@FXML private TextField emailTextField;
	@FXML private Button confirmationButton;
	@FXML private Button closeButton;
	
	private String userName;
	private String password;
	private String email;
	
	static ArrayList<String> wrongInformation = new ArrayList<>();
	static ArrayList<Throwable> exceptionList = new ArrayList<>();
	
	private Pattern userNamePattern = Pattern.compile("^[A-Za-z0-9_]+$");
	private Pattern userNamePatternStartByNum = Pattern.compile("^[0-9]+[A-Za-z0-9_]+$");
	private Matcher userNameMatcher;
	private Pattern passwordPattern = Pattern.compile("^[A-Za-z0-9_]+$");
	private Matcher passwordMatcher;
	private Pattern emailPattern = Pattern.compile("^\\w+@\\w{1,}+(.\\w+)+$");
	private Matcher emailMatcher;
	
	private Boolean isRegisterSuccessful = false;
	
	@FXML
	private void confirmationButtonPressed(ActionEvent e)
	{
		try
		{
			userName = userNameTextField.getText();
			userNameMatcher = userNamePattern.matcher(userName);
			if(userName.length() == 0)
			{
				wrongInformation.add("用户名不能为空！");
				throw new userNameException1();
			}
			if(userNameMatcher.find())
			{
				userNameMatcher = userNamePatternStartByNum.matcher(userName);
				if(userNameMatcher.find())
				{
					wrongInformation.add("用户名不能以数字开头！");
					throw new userNameException2();
				}
			}
			else
			{
				wrongInformation.add("用户名只能由字母、数字、下划线组成！");
				throw new userNameException2();
			}
		}
		catch(userNameException1 e1)
		{
			exceptionList.add(e1);
		}
		catch(userNameException2 e2)
		{
			exceptionList.add(e2);
		}
		
		try
		{
			password = passwordTextField.getText();
			passwordMatcher = passwordPattern.matcher(password);
			if(password.length() == 0)
			{
				wrongInformation.add("密码不能为空！");
				throw new passwordException();
			}
			if(passwordMatcher.find())
			{
				if(password.length() < 8)
				{
					wrongInformation.add("密码不能小于8位！");
					throw new passwordException1();
				}
			}
			else
			{
				wrongInformation.add("密码只能由字母、数字、下划线组成！");
				throw new passwordException1();
			}
		}
		catch(passwordException e1)
		{
			exceptionList.add(e1);
		}
		catch(passwordException1 e2)
		{
			exceptionList.add(e2);
		}
		
		try
		{
			email = emailTextField.getText();
			emailMatcher = emailPattern.matcher(email);
			
			if(email.length() == 0)
			{
				wrongInformation.add("邮箱不能为空！");
				throw new emailException1();
			}
			if(!emailMatcher.matches())
			{
				wrongInformation.add("邮箱格式错误！\n正确格式：数字/字母/下划线+@+地址后缀");
				throw new emailException2();
			}
		}
		catch(emailException1 e1)
		{
			exceptionList.add(e1);
		}
		catch(emailException2 e2)
		{
			exceptionList.add(e2);
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
			alert.setContentText("用户名：" + userName + "\n" + "密码：" + passwordHidden + "\n" + "邮箱：" + email);
			ButtonType buttonTypeOne = new ButtonType("显示密码");
			ButtonType buttonTypeCancel = new ButtonType("确认", ButtonData.CANCEL_CLOSE);
			 
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
			 
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne)
			{
				alert.setContentText("用户名：" + userName + "\n" + "密码：" + password + "\n" + "邮箱：" + email);
				alert.getButtonTypes().clear();
				alert.getButtonTypes().setAll(buttonTypeCancel);
				alert.showAndWait();
			} 
		}
	}
	
	@FXML
	private void closeButtonPressed(ActionEvent e)
	{
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
}
