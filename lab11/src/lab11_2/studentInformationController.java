package lab11_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXB;
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
	
	public void addInformationButtonPressed(ActionEvent e) throws IOException
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
				BufferedReader input1 = Files.newBufferedReader(Paths.get("src/lab11_2/students.xml"));
				Students students = new Students();
				Students students1 = JAXB.unmarshal(input1, Students.class);
		        for (Student student : students1.getStudents()) 
		        {
		        	String line = student.getStudentNumber() + " " + student.getStudentName() + " " + student.getStudentPhoneNumber() + " " + student.getStudentEmail();
		        	information.add(line);
		        	//System.out.println(line);
		        }
		        information.add(input.get());
		        for(String str : information)
		        {
		        	String []records = str.split("[ ]+");
		        	Student record = new Student(records[0], records[1], records[2], records[3]);
		        	students.getStudents().add(record);
		        }
		        BufferedWriter output = Files.newBufferedWriter(Paths.get("src/lab11_2/students.xml"));
		        JAXB.marshal(students, output);
				wrongInformation.clear();
				showTextArea.setText("新增成功！新增信息如下：\n" + input.get() + "\n您可以点击\"显示\"按钮查看新增后的信息");
			}
		}
	}
	public void deleteInformationButtonPressed(ActionEvent e) throws IOException
	{
		if(currentMode != Status.DISPLAY)
		{
			showTextArea.setText("请先点击\"显示\"按钮选择要删除的信息！");
		}
		else
		{
			information.clear();
			BufferedReader input1 = Files.newBufferedReader(Paths.get("src/lab11_2/students.xml"));
			Students students = new Students();
			Students students1 = JAXB.unmarshal(input1, Students.class);
	        for (Student student : students1.getStudents()) 
	        {
	        	String line = student.getStudentNumber() + " " + student.getStudentName() + " " + student.getStudentPhoneNumber() + " " + student.getStudentEmail();
	        	information.add(line);
	        }
	        
			String deleteInformation = showTextArea.getText();
			Iterator<String> iter = information.iterator();
			
			while (iter.hasNext()) 
			{ 
			    if (iter.next().equals(deleteInformation)) 
			    { 
			    	iter.remove(); 
			    }
			}
			
			for(String str : information)
	        {
	        	String []records = str.split("[ ]+");
	        	Student record = new Student(records[0], records[1], records[2], records[3]);
	        	students.getStudents().add(record);
	        }
	        BufferedWriter output = Files.newBufferedWriter(Paths.get("src/lab11_2/students.xml"));
	        JAXB.marshal(students, output);
			
			currentMode = Status.DELETE;
			showTextArea.setText("已删除当前记录！您可以点击\"显示\"按钮继续查看信息！");
		}
	}
	public void modifyInformationButtonPressed(ActionEvent e) throws IOException
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
					information.clear();
					BufferedReader input1 = Files.newBufferedReader(Paths.get("src/lab11_2/students.xml"));
					Students students = new Students();
					Students students1 = JAXB.unmarshal(input1, Students.class);
			        for (Student student : students1.getStudents()) 
			        {
			        	String line = student.getStudentNumber() + " " + student.getStudentName() + " " + student.getStudentPhoneNumber() + " " + student.getStudentEmail();
			        	information.add(line);
			        }
					
					String targetInformation = showTextArea.getText();
					int targetInformationIndex = information.indexOf(targetInformation);
					String modifyInformation = inputs[0] + " " + inputs[1] + " " + inputs[2] + " " + inputs[3];
					information.set(targetInformationIndex, modifyInformation);
					
					for(String str : information)
			        {
			        	String []records = str.split("[ ]+");
			        	Student record = new Student(records[0], records[1], records[2], records[3]);
			        	students.getStudents().add(record);
			        }
			        BufferedWriter output = Files.newBufferedWriter(Paths.get("src/lab11_2/students.xml"));
			        JAXB.marshal(students, output);
					
					currentMode = Status.CHANGE;
					showTextArea.setText("已修改当前记录！您可以点击\"显示\"按钮继续查看信息！");
				}
			}
		}
	}
	public void searchInformationButtonPressed(ActionEvent e) throws IOException
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
			BufferedReader input1 = Files.newBufferedReader(Paths.get("src/lab11_2/students.xml"));
			Students students1 = JAXB.unmarshal(input1, Students.class);
			String targetName = input.get();
			
	        for (Student student : students1.getStudents()) 
	        {
	        	String line = student.getStudentNumber() + " " + student.getStudentName() + " " + student.getStudentPhoneNumber() + " " + student.getStudentEmail();
	        	String[] inputs = line.split("[ ]+");
				if(inputs.length != 4)
				{
					continue;
				}
				else if(inputs.length == 4 && inputs[1].equals(targetName))
				{
					information.add(line);
					isSearchSuccessful = true;
					break;
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
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("未找到对应记录！");
				alert.setHeaderText(null);
				alert.setContentText("请确认您的输入是否正确");
				alert.showAndWait();
			}
		}
	}
	public void displayInformationButtonPressed(ActionEvent e) throws IOException
	{
		currentMode = Status.DISPLAY;
		information.clear();
		BufferedReader input = Files.newBufferedReader(Paths.get("src/lab11_2/students.xml"));
		Students students = JAXB.unmarshal(input, Students.class);
		//scan = new Scanner(file);
        for (Student student : students.getStudents()) 
        {
        	String line = student.getStudentNumber() + " " + student.getStudentName() + " " + student.getStudentPhoneNumber() + " " + student.getStudentEmail();
        	information.add(line);
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
