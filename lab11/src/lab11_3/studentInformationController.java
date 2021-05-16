package lab11_3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class studentInformationController 
{
	@FXML private Button addInformationButton;
	@FXML private Button displayInformationButton;
	@FXML private Button deleteInformationButton;
	@FXML private Button searchInformationButton;
	@FXML private TextArea showTextArea;
	@FXML private Button lastInformationButton;
	@FXML private Button nextInformationButton;
	@FXML private Button modifyInformationButton;
	private Status currentMode;
	private enum Status
	{
		ADD(0),
		DELETE(1),
		CHANGE(2),
		SEARCH(3),
		DISPLAY(4);
		
		Status(int status){}
	};
	private ArrayList<String> information = new ArrayList<>();
	private int currentDisplayPosition;
	private boolean isAddSuccessful = true;

	private ArrayList<String> wrongInformation = new ArrayList<>();
	private Pattern studentCodePattern = Pattern.compile("^[0-9]+$");
	private Matcher studentCodeMatcher;
	private Pattern namePattern = Pattern.compile("[^0-9]+");
	private Matcher nameMatcher;
	private Pattern phoneNumberPattern = Pattern.compile("[0-9-]+");
	private Matcher phoneNumberMatcher;
	private Pattern emailPattern = Pattern.compile("^\\w+@\\w{1,}+(.\\w+)+$");
	private Matcher emailMatcher;
	
	public void addInformationButtonPressed(ActionEvent e)
	{
		currentMode = Status.ADD;
		//showTextArea.clear();
		TextInputDialog dialog = new TextInputDialog("Input here");
		dialog.setTitle("新增学生记录");
		dialog.setHeaderText("格式：学号 姓名 电话 邮箱");
		dialog.setContentText("请在下方输入学生信息：");
		
		Optional<String> input = dialog.showAndWait();
		
		if(input.isPresent())
		{
			String[] inputs = input.get().split("[ ]+");
			
			if(input.get().length() == 0 || input.get().equals("Input here"))
			{
				isAddSuccessful = false;
				wrongInformation.add("输入不能为空！");
			}
			else if(inputs.length != 4)
			{
				isAddSuccessful = false;
				wrongInformation.add("输入项数不匹配！");
			}
			else if(inputs.length == 4)
			{
				studentCodeMatcher = studentCodePattern.matcher(inputs[0]);
				nameMatcher = namePattern.matcher(inputs[1]);
				phoneNumberMatcher = phoneNumberPattern.matcher(inputs[2]);
				emailMatcher = emailPattern.matcher(inputs[3]);
				
				if(!studentCodeMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("学号只能由数字组成！");
				}
				if(!nameMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("姓名不能包含数字！");
				}
				if(!phoneNumberMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("电话号码只能由数字和\"-\"组成！");
				}
				if(!emailMatcher.matches())
				{
					isAddSuccessful = false;
					wrongInformation.add("邮箱格式错误！正确格式：数字/字母/下划线+@+地址后缀");
				}
				if(wrongInformation.size() == 0)
					isAddSuccessful = true;
			}
			
			//System.out.println(inputs.length);
			
			if(isAddSuccessful == false) 
			{
				String wrongText = wrongInformation.get(0);
				for(int i = 1; i < wrongInformation.size(); i++)
				{
					wrongText += ("\n" + wrongInformation.get(i));
				}
				
				showTextArea.setText("新增失败！错误信息如下：\n" + wrongText);
				wrongInformation.clear();
			}
			else
			{
				information.clear();
				List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
		        if (resultList != null) 
		        {
		            List<String> addList = Arrays.asList(inputs[0],inputs[1],inputs[2],inputs[3]);
		            resultList.add(addList);
		        }
		        ExcelUtil.writeExcel(resultList, "src/lab11_3/studentInformation.xlsx");
		        wrongInformation.clear();
				showTextArea.setText("新增成功！新增信息如下：\n" + input.get() + "\n您可以点击\"显示\"按钮查看新增后的信息");
			}
		}
	}
	
	public void deleteInformationButtonPressed(ActionEvent e)
	{
		if(currentMode != Status.DISPLAY)
		{
			showTextArea.setText("请先点击\"显示\"按钮选择要删除的信息！");
		}
		else
		{
			information.clear();
			List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
			String deleteInformation = showTextArea.getText();
			
	        if (resultList != null) 
	        {
	        	Iterator<List<String>> iter = resultList.iterator();
	        	while(iter.hasNext())
	        	{
	        		String[] str = deleteInformation.split("[ ]+");
	        		List<String> tempList = Arrays.asList(str[0],str[1],str[2],str[3]);
	        		if(iter.next().equals(tempList))
	        			iter.remove();
	        	}
	        }
	        ExcelUtil.writeExcel(resultList, "src/lab11_3/studentInformation.xlsx");
	     }
	     
	     currentMode = Status.DELETE;
	     showTextArea.setText("已删除当前记录！您可以点击\"显示\"按钮继续查看信息！");
	}
	
	public void modifyInformationButtonPressed(ActionEvent e)
	{
		if(currentMode != Status.DISPLAY)
		{
			showTextArea.setText("请先点击\"显示\"按钮选择要修改的信息！");
		}
		else
		{
			//String targetInformation = showTextArea.getText();
			TextInputDialog dialog = new TextInputDialog("Input here");
			dialog.setTitle("修改学生记录");
			dialog.setHeaderText("格式：学号 姓名 电话 邮箱");
			dialog.setContentText("请在下方输入学生信息：");
			
			Optional<String> input = dialog.showAndWait();
			
			if(input.isPresent())
			{
				String[] inputs = input.get().split("[ ]+");
				
				if(input.get().length() == 0 || input.get().equals("Input here"))
				{
					isAddSuccessful = false;
					wrongInformation.add("输入不能为空！");
				}
				else if(inputs.length != 4)
				{
					isAddSuccessful = false;
					wrongInformation.add("输入项数不匹配！");
				}
				else if(inputs.length == 4)
				{
					studentCodeMatcher = studentCodePattern.matcher(inputs[0]);
					nameMatcher = namePattern.matcher(inputs[1]);
					phoneNumberMatcher = phoneNumberPattern.matcher(inputs[2]);
					emailMatcher = emailPattern.matcher(inputs[3]);
					
					if(!studentCodeMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("学号只能由数字组成！");
					}
					if(!nameMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("姓名不能包含数字！");
					}
					if(!phoneNumberMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("电话号码只能由数字和\"-\"组成！");
					}
					if(!emailMatcher.matches())
					{
						isAddSuccessful = false;
						wrongInformation.add("邮箱格式错误！正确格式：数字/字母/下划线+@+地址后缀");
					}
					if(wrongInformation.size() == 0)
						isAddSuccessful = true;
				}
				
				if(isAddSuccessful == false) 
				{
					String wrongText = wrongInformation.get(0);
					for(int i = 1; i < wrongInformation.size(); i++)
					{
						wrongText += ("\n" + wrongInformation.get(i));
					}
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("修改失败！");
					alert.setHeaderText("请参照以下提示更新您的注册信息：");
					alert.setContentText(wrongText);
					wrongInformation.clear();
					alert.showAndWait();
				}
				else
				{
					List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
					List<String> modifyElement = Arrays.asList(inputs[0],inputs[1],inputs[2],inputs[3]);
					String[] tempString = showTextArea.getText().split("[ ]+");
					List<String> targetElement = Arrays.asList(tempString[0],tempString[1],tempString[2],tempString[3]);
			        if (resultList != null) 
			        {
			            for (int i = 0; i < resultList.size(); i++) 
			            {
			                List<String> cellList = resultList.get(i);            
			                if(cellList.equals(targetElement))
			                {
			                	resultList.set(i, modifyElement);
			                }
			            }
			        }
			        ExcelUtil.writeExcel(resultList, "src/lab11_3/studentInformation.xlsx");
					currentMode = Status.CHANGE;
					showTextArea.setText("已修改当前记录！您可以点击\"显示\"按钮继续查看信息！");
				}
			}
		}
	}
	
	public void searchInformationButtonPressed(ActionEvent e)
	{
		boolean isSearchSuccessful = false;
		TextInputDialog dialog = new TextInputDialog("Input here");
		dialog.setTitle("查询学生记录");
		dialog.setHeaderText("格式：姓名");
		dialog.setContentText("请在下方输入学生姓名：");
		
		Optional<String> input = dialog.showAndWait();
		
		if(input.isPresent())
		{
			information.clear();
			List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
			String targetName = input.get();
	        if (resultList != null) 
	        {
	            for (int i = 0; i < resultList.size(); i++) 
	            {
	                List<String> cellList = resultList.get(i);
	                if(cellList.get(1).equals(targetName))
	                {
	                	String str = cellList.get(0) + " " + cellList.get(1) + " " + cellList.get(2) + " " + cellList.get(3);
	                	information.add(str);
	                	isSearchSuccessful = true;
	                }
	            }
	        }
	        if(isSearchSuccessful == true)
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("查找成功！");
				alert.setHeaderText(null);
				alert.setContentText("已找到" + information.size() + "条记录");
				alert.showAndWait();
				currentDisplayPosition = 0;
				showTextArea.setText("第" + (currentDisplayPosition+1) + "条查找记录" + "\n" + information.get(currentDisplayPosition));
				currentMode = Status.SEARCH;
			}
			else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("未找到对应记录！");
				alert.setHeaderText(null);
				alert.setContentText("请确认您的输入是否正确");
				alert.showAndWait();
			}
		}
		

	}
	
	public void displayInformationButtonPressed(ActionEvent e)
	{
		List<List<String>> resultList = ExcelUtil.readExcel("src/lab11_3/studentInformation.xlsx");
		currentMode = Status.DISPLAY;
		information.clear();
		if (resultList != null) 
        {
            for (int i = 0; i < resultList.size(); i++) 
            {
                List<String> cellList = resultList.get(i);
                String str = cellList.get(0) + " " + cellList.get(1) + " " + cellList.get(2) + " " + cellList.get(3);
                information.add(str);
            }
        }
		currentDisplayPosition = 0;
		showTextArea.setText(information.get(currentDisplayPosition));
	}
	
	public void lastInformationButtonPressed(ActionEvent e)
	{
		
		if(currentMode == Status.DISPLAY)
		{
			currentDisplayPosition--;
			if(currentDisplayPosition < 0)
				currentDisplayPosition = 0;
			showTextArea.setText(information.get(currentDisplayPosition));
		}
		else if(currentMode == Status.SEARCH)
		{
			currentDisplayPosition--;
			if(currentDisplayPosition < 0)
				currentDisplayPosition = 0;
			showTextArea.setText("第" + (currentDisplayPosition+1) + "条查找记录" + "\n" + information.get(currentDisplayPosition));
		}
		else
		{
			showTextArea.setText("请先点击\"显示\"按钮，再进行该操作！");
		}
		

	}
	
	public void nextInformationButtonPressed(ActionEvent e)
	{
		if(currentMode == Status.DISPLAY)
		{
			currentDisplayPosition++;
			if(currentDisplayPosition >= information.size())
				currentDisplayPosition = information.size() - 1;
			showTextArea.setText(information.get(currentDisplayPosition));
		}
		else if(currentMode == Status.SEARCH)
		{
			currentDisplayPosition++;
			if(currentDisplayPosition >= information.size())
				currentDisplayPosition = information.size() - 1;
			showTextArea.setText("第" + (currentDisplayPosition+1) + "条查找记录" + "\n" + information.get(currentDisplayPosition));
		}
		else
		{
			showTextArea.setText("请先点击\"显示\"按钮，再进行该操作！");
		}
	}
}
