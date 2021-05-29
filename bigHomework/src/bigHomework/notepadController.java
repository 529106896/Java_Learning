package bigHomework;

import java.io.InputStreamReader;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
//import cn.hutool.core.util.StrUtil;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class notepadController extends Stage implements Initializable
{
	@FXML private Stage stage;
	@FXML private Stage fontStage;
	@FXML private Stage turntoStage;
	private Global global;
	private String fontColor = "BLACK";
	private Font font;
	private double size = 20;
	private int zoomPercentage = 100;
	@FXML public TextArea textArea;
	@FXML public Menu firstMenu;
	@FXML public Menu secondMenu;
	@FXML public Menu thirdMenu;
	@FXML public Menu fourthMenu;
	@FXML public Menu fifthMenu;
	@FXML public ContextMenu textAreaContextMenu;
	
	@FXML public MenuItem firstMenu_NewFile;
	@FXML public MenuItem firstMenu_Open;
	@FXML public MenuItem firstMenu_Save;
	@FXML public MenuItem firstMenu_SaveAs;
	@FXML public MenuItem firstMenu_PageSet;
	@FXML public MenuItem firstMenu_Exit;
	
	@FXML public MenuItem secondMenu_Undo;
	@FXML public MenuItem secondMenu_Redo;
	@FXML public MenuItem secondMenu_Cut;
	@FXML public MenuItem secondMenu_Copy;
	@FXML public MenuItem secondMenu_Paste;
	@FXML public MenuItem secondMenu_Delete;
	@FXML public MenuItem secondMenu_Search;
	@FXML public MenuItem secondMenu_PreviousSearch;
	@FXML public MenuItem secondMenu_NextSearch;
	@FXML public MenuItem secondMenu_Replace;
	@FXML public MenuItem secondMenu_TurnTo;
	@FXML public MenuItem secondMenu_SelectAll;
	@FXML public MenuItem secondMenu_TimeAndDate;
	@FXML public MenuItem secondMenu_baiduSearch;
	
	@FXML public MenuItem contextMenuUndo;
	@FXML public MenuItem contextMenuRedo;
	@FXML public MenuItem contextMenuCut;
	@FXML public MenuItem contextMenuCopy;
	@FXML public MenuItem contextMenuPaste;
	@FXML public MenuItem contextMenuDelete;
	@FXML public MenuItem contextMenuSelectAll;
	@FXML public MenuItem contextMenuBaiduSearch;
	
	@FXML public MenuItem thirdMenu_FontChooser;
	@FXML public CheckMenuItem thirdMenu_ChangeLine;
	@FXML public CheckMenuItem fourthMenu_Status;
	
	@FXML public MenuItem fifthMenu_Help;
	@FXML public MenuItem fifthMenu_About;
	
	@FXML public MenuBar menuBar;
	@FXML public Label statusLabel;
	
	private ExtensionFilter fileChooserFilter1 = new FileChooser.ExtensionFilter("文本文档", "*.txt");
	private ExtensionFilter fileChooserFilter2 = new FileChooser.ExtensionFilter("所有文件", "*.*");
	
	//标记状态
	private int status = 1;		//1：新建		2：已修改		3：已保存
	
	public int getStatus()
	{
		return status;
	}
	
	private String currentPath;
	@SuppressWarnings("unused")
	private String currentFileName;
	
	public String getCurrentPath()
	{
		return currentPath;
	}
	
	public void mainStage(Stage stage)
	{
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		global = (Global)Main.globals.get("global");
		Main.controllers.put(this.getClass().getSimpleName(), this);
		
		font = Font.font("微软雅黑", FontWeight.NORMAL, FontPosture.REGULAR, size);
		textArea.setFont(font);
		statusLabel.setText("    第 " + 1 + " 行, 第 " + 1 + " 列  " + " 共 " + 0 + " 字 " + " 缩放：" + zoomPercentage + "%");
		myEdit();
		fourthMenu_Status.setSelected(true);
		//监控鼠标点击
		textArea.setOnMouseClicked(new EventHandler<MouseEvent>()
				{
					@Override
					public void handle(MouseEvent event)
					{
						statusLabelUpdate();
						myEdit();
					}
				});
		
		//监控键盘按下，用alt切换菜单栏选择
		textArea.setOnKeyPressed(new EventHandler<KeyEvent>()
				{
					@Override
					public void handle(KeyEvent event)
					{
						if(event.getCode() == KeyCode.ALT)
							menuBar.requestFocus();
						else
						{
							//statusLabelUpdate();
						}
					}
				});
		
		//监控鼠标滚轮
		textArea.setOnScroll(new EventHandler<ScrollEvent>()
				{
					@Override
					public void handle(ScrollEvent event)
					{
						if(event.isControlDown() && event.getDeltaY() < 0)
							zoomout();
						else if(event.isControlDown() && event.getDeltaY() > 0)
							zoomin();
					}
				});
		
		//监控键盘按键抬起
		textArea.setOnKeyReleased(new EventHandler<KeyEvent>()
				{
					@Override
					public void handle(KeyEvent event)
					{
						if(event.getCode() == KeyCode.ALT)
							menuBar.requestFocus();
						else
							statusLabelUpdate();
					}
				});
		
		//监控文本变化
		textArea.textProperty().addListener(new ChangeListener<String>()
				{
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
					{
						myEdit();
					}
				});
	}
	
	//新建
	@FXML
	private void newFile()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("新建");
        alert.setHeaderText("请确认是否要新建文件？");
        alert.setContentText("如果需要，请先取消并保存您的文件");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) 
        {
            textArea.clear();
            stage.setTitle("无标题");
            status = 1;
            currentPath = null;
        }
	}
	
	//打开文件
	@FXML
	private void open()
	{
		FileChooser chooser = new FileChooser();
		chooser.setTitle("打开文件");
		chooser.setInitialDirectory(new File("C:\\"));
		
		chooser.getExtensionFilters().add(fileChooserFilter1);
		chooser.getExtensionFilters().add(fileChooserFilter2);
		File file = chooser.showOpenDialog(this);
		if(file == null)
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("打开文件");
            alert.setHeaderText("未选择任何文件");
            alert.setContentText("未打开新文件");
            alert.showAndWait();
		}
		else
		{
			status = 2;
			currentPath = file.getAbsolutePath();
			stage.setTitle(file.getName());
			BufferedReader bufferRead = null;
			try
			{
				InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
				bufferRead = new BufferedReader(streamReader);
				
				StringBuffer strBuffer = new StringBuffer();
				
				String line;
				
				while((line = bufferRead.readLine()) != null)
				{
					strBuffer.append(line).append(System.getProperty("line.separator"));
				}
				textArea.setText(strBuffer.toString());
			}
			catch(Exception e)
			{
			}
			finally
			{
				try
				{
					if(bufferRead != null)
						bufferRead.close();
				}
				catch(Exception e1)
				{
				}
			}
		}
	}
	
	//保存
	@FXML
	public void save()
	{
		status = 3;
		if(currentPath == null)
			saveAs();
		else
		{
			FileWriter fw = null;
			try
			{
				fw = new FileWriter(new File(currentPath));
				fw.write(textArea.getText());
				fw.flush();
				status = 3;
				stage.setTitle(currentPath);
			}
			catch(Exception e)
			{
			}
			finally
			{
				 try 
				 {
					 if (fw != null) fw.close();
	             } 
				 catch (IOException e) 
				 {
	             }
			}
		}
	}
	
	//另存为
	@FXML
	private void saveAs()
	{
		FileChooser chooser = new FileChooser();
        chooser.setTitle("保存文件");
        chooser.setInitialDirectory(new File("C:\\"));
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("文本文件(*.txt)", "*.txt");
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showSaveDialog(this);
        if (file == null) 
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告！");
            alert.setHeaderText("当前文件未保存！");
            alert.setContentText("将导致目前已编辑内容丢失！");
            alert.showAndWait();
        }
        else
        {
        	status = 3;
        	FileWriter fw = null;
        	try
        	{
        		fw = new FileWriter(file);
        		fw.write(textArea.getText());
        		currentFileName = file.getName();
        		currentPath = file.getAbsolutePath();
        		fw.flush();
        		this.status = 3;
        		stage.setTitle(currentPath);
        	}
        	catch(Exception e)
        	{
        		
        	}
        	finally
        	{
        		try
        		{
        			if(fw != null)
        				fw.close();
        		}
        		catch(Exception e)
        		{
        			
        		}
        	}
        }
	}
	
	//直接退出时的保存
	public void exitSave()
	{
		saveAs();
	}
	
	//页面设置
	@FXML
	private void pageSet()
	{
		PageFormat pf = new PageFormat();
		PrinterJob.getPrinterJob().pageDialog(pf);
	}
	
	//退出
	@FXML
	private void exit()
	{
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("关闭");
        alert.setHeaderText("确定要退出？");
        alert.setContentText("");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) 
        {
            if (status != 3) 
            {
            	Alert alert1 = new Alert(AlertType.INFORMATION);
            	alert1.setTitle("请先保存文件！");
            	alert1.setHeaderText(null);
            	alert1.setContentText("您还未保存当前编辑，请先保存文件！");
            	 
            	alert1.showAndWait();

                if (currentPath == null) 
                {
                    saveAs();
                } 
                else 
                {
                    save();
                }
            }
            stage.close();
        }
	}
	
	//设置编辑菜单里部分选项是否可选
	private void myEdit()
	{
		if(!textArea.isUndoable())
		{
			secondMenu_Undo.setDisable(true);
			contextMenuUndo.setDisable(true);
		}
		else if(textArea.isUndoable())
		{
			secondMenu_Undo.setDisable(false);
			contextMenuUndo.setDisable(false);
		}
		
		if(!textArea.isRedoable())
		{
			secondMenu_Redo.setDisable(true);
			contextMenuRedo.setDisable(true);
		}
		else if(textArea.isRedoable())
		{
			secondMenu_Redo.setDisable(false);
			contextMenuRedo.setDisable(false);
		}
		
		if(textArea.getSelectedText().isEmpty())
		{
			secondMenu_Cut.setDisable(true);
			contextMenuCut.setDisable(true);
			secondMenu_Copy.setDisable(true);
			contextMenuCopy.setDisable(true);
		}
		else if(!textArea.getSelectedText().isEmpty())
		{
			secondMenu_Cut.setDisable(false);
			contextMenuCut.setDisable(false);
			secondMenu_Copy.setDisable(false);
			contextMenuCopy.setDisable(false);
		}
	}
	
	//更新状态栏
	private void statusLabelUpdate()
	{
		status = 2;
		int row;	//列
		int line;	//行
		int findStartPos;	//记录当前指针位置
		
		String str1 = textArea.getText();
		findStartPos = textArea.getCaretPosition();
		String str2 = str1.substring(0, findStartPos);
		
		String[] strings = str2.split("\n");
		line = strings.length;
		
		if(line == 0)
		{
			str1 += "*";
			str2 = str1.substring(0, findStartPos + 1);
			strings = str2.split("\n");
			line = strings.length;
			row = strings[line - 1].length() - 1;
		}
		else
			row = strings[line - 1].length();

		statusLabel.setText("    第 " + line + " 行, 第 " + (row + 1) + " 列  " + " 共 " + str1.length() + " 字 "  + " 缩放：" + zoomPercentage + "%");
	}
	
	//撤销
	//存在bug：只能撤销上一次操作；输入文本会被默认为一次操作
	@FXML
	private void undo()
	{
		textArea.undo();
	}
	
	//还原
	@FXML
	private void redo()
	{
		textArea.redo();
	}
	
	//剪切
	@FXML
	private void cut()
	{
		textArea.cut();
	}
	
	//复制
	@FXML
	private void copy()
	{
		textArea.copy();
	}
	
	//粘贴
	@FXML
	private void paste()
	{
		textArea.paste();
	}
	
	//删除
	//有时候会有莫名其妙的string下标越界bug？
	@FXML
	private void delete()
	{
		try
		{
			//textArea.deselect();
			textArea.replaceSelection("");
		}
		catch(Exception e)
		{
			
		}

	}
	
	//查找、替换界面
	@FXML
	public void mySearch() throws IOException
	{
		Parent searchRoot = FXMLLoader.load(getClass().getResource("search.fxml"));
		Scene searchScene = new Scene(searchRoot);
		
		Stage searchStage = new Stage();
		searchStage.setScene(searchScene);
		searchStage.setTitle("搜索与替换");
		searchStage.setResizable(false);
		searchStage.show();
		
	}
	
	//查找功能的实现
	@FXML
	public void search()
	{
		int a = 0, b = 0;
		
		//开始查找的位置（光标位置）
		int findStartPos = textArea.getCaretPosition();
		
		String strA, strB;
		strA = textArea.getText();	//存储文本
		strB = global.search;		//用global把要查找的文本传进来
		
		if(strB.length() == 0)
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("查询与替换");
            alert.setHeaderText("查找结果");
            alert.setContentText("查找内容不能为空！");
            alert.showAndWait();
            return;
		}
		
		//光标在最后会有莫名其妙的bug
		//所以加上修正光标位置
		if(findStartPos >= strA.length())
			findStartPos = 0;
		
//		if(textArea.getSelectedText() == null)
//			//字符串子串查找
//			a = strA.indexOf(strB, findStartPos);
//		else
//			//如果当前有选中的文本，说明上次的已经找到，就接着往下找
//			a = strA.indexOf(strB, findStartPos - strB.length() + 1);
		
		if(textArea.getSelectedText().isEmpty())
		{
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrA = strA.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos);
			}
			else
				a = strA.indexOf(strB, findStartPos);
		}

		else
		{
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrA = strA.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos - caseIgnoredStrB.length() + 1);
			}
			else
				a = strA.indexOf(strB, findStartPos - strB.length() + 1);
		}
		
		//System.out.println(global.isCaseIgnored);
		
		//查找失败时索引返回值为-1
		if(a > -1)
		{
			textArea.positionCaret(a);
			b = strB.length();
			textArea.selectRange(a, a + b);
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("查询与替换");
            alert.setHeaderText("查找结果");
            alert.setContentText("后面没有您要查找的内容！");
            alert.showAndWait();
		}
	}
	
	@FXML
	public void previousSearch()
	{
		int a = 0, b = 0;
		
		//开始查找的位置（光标位置）
		int findStartPos = textArea.getCaretPosition();
		
		String strA, strB;
		strA = textArea.getText();	//存储文本
		strB = global.search;		//用global把要查找的文本传进来
		
		if(strB.length() == 0)
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("查询与替换");
            alert.setHeaderText("查找结果");
            alert.setContentText("查找内容不能为空！");
            alert.showAndWait();
            return;
		}
		
		
		String strTemp = strA.substring(0, findStartPos);
		
		if(textArea.getSelectedText().isEmpty())
		{
			strTemp = strA.substring(0, findStartPos);
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrTemp = strTemp.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrTemp.lastIndexOf(caseIgnoredStrB);
			}
			else
				a = strTemp.lastIndexOf(strB);
		}
			
		else
		{
			//如果当前有选中的文本，说明上次的已经找到，就接着往下找
			strTemp = strA.substring(0, strTemp.length() - strB.length());
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrTemp = strTemp.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrTemp.lastIndexOf(caseIgnoredStrB);
			}
			else
				a = strTemp.lastIndexOf(strB);
		}

		
		//查找失败时索引返回值为-1
		if(a > -1)
		{
			textArea.positionCaret(a);
			b = strB.length();
			textArea.selectRange(a, a + b);
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("查询与替换");
            alert.setHeaderText("查找结果");
            alert.setContentText("前面没有您要查找的内容！");
            alert.showAndWait();
		}
	}
	
	//替换功能的实现
	public void replace()
	{
		int a = 0, b = 0;
		
		//开始查找的位置（光标位置）
		int findStartPos = textArea.getCaretPosition();
		
		String strA, strB, strC;
		strA = textArea.getText();
		strB = global.search;
		strC = global.replace;
		
		if(strB.length() == 0)
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("查询与替换");
            alert.setHeaderText("替换结果");
            alert.setContentText("替换对象不能为空！");
            alert.showAndWait();
            return;
		}
		
		//光标在最后会有莫名其妙的bug
		//所以加上修正光标位置
		if(findStartPos >= strA.length())
			findStartPos = 0;
		
		if(textArea.getSelectedText().isEmpty())
		{
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrA = strA.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos);
			}
			else
				a = strA.indexOf(strB, findStartPos);
		}

		else
		{
			if(global.isCaseIgnored == true)
			{
				String caseIgnoredStrA = strA.toLowerCase();
				String caseIgnoredStrB = strB.toLowerCase();
				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos - caseIgnoredStrB.length() + 1);
			}
			else
				a = strA.indexOf(strB, findStartPos - strB.length() + 1);
		}
		
		//查找失败时索引返回值为-1
		if(a > -1)
		{
			textArea.positionCaret(a);
			b = strB.length();
			textArea.selectRange(a, a + b);
			
			try
			{
				if(strC.length() == 0 && !textArea.getSelectedText().isEmpty())
				{
					textArea.replaceSelection("");
				}
				if(strC.length() > 0 && !textArea.getSelectedText().isEmpty())
				{
					textArea.replaceSelection(strC);
				}
			}
			catch(Exception e)
			{
				
			}
			
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("查询与替换");
            alert.setHeaderText("查找结果");
            alert.setContentText("后面没有您要查找的内容！");
            alert.showAndWait();
		}
	}
	
	//替换全部功能的实现
	public void replaceAll()
	{
		textArea.positionCaret(0); // 将光标放到编辑区开头
        int a = 0, b = 0, replaceCount = 0;
        while (a > -1) 
        {
            int findStartPos = textArea.getCaretPosition();
            String strA, strB, strC;
            strA = textArea.getText();
            strB = global.search;
            strC = global.replace;
            if (strB.length() == 0) 
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("查询与替换");
                alert.setHeaderText("替换结果");
                alert.setContentText("查找内容不能为空！");
                alert.showAndWait();
                return;
            }
            
    		if(textArea.getSelectedText().isEmpty())
    		{
    			if(global.isCaseIgnored == true)
    			{
    				String caseIgnoredStrA = strA.toLowerCase();
    				String caseIgnoredStrB = strB.toLowerCase();
    				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos);
    			}
    			else
    				a = strA.indexOf(strB, findStartPos);
    		}

    		else
    		{
    			if(global.isCaseIgnored == true)
    			{
    				String caseIgnoredStrA = strA.toLowerCase();
    				String caseIgnoredStrB = strB.toLowerCase();
    				a = caseIgnoredStrA.indexOf(caseIgnoredStrB, findStartPos - caseIgnoredStrB.length() + 1);
    			}
    			else
    				a = strA.indexOf(strB, findStartPos - strB.length() + 1);
    		}
            
            if (a > -1) 
            {
            	textArea.positionCaret(a);
                b = strB.length();
                textArea.selectRange(a, a + b);
            } else 
            {
                if (replaceCount == 0) 
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
                    alert.setTitle("查询与替换");
                    alert.setHeaderText("替换结果");
                    alert.setContentText("找不到您查找的内容");
                    alert.showAndWait(); //模态显示对话框
                } else 
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
                    alert.setTitle("查询与替换");
                    alert.setHeaderText("替换结果");
                    alert.setContentText("成功替换 " + replaceCount + "个匹配内容!");
                    alert.showAndWait(); //模态显示对话框
                }
            }
            if (strC.length() == 0 && !textArea.getSelectedText().isEmpty()) 
            {
            	textArea.replaceSelection("");
                replaceCount++;
            }
            if (strC.length() > 0 && !textArea.getSelectedText().isEmpty()) 
            {
            	textArea.replaceSelection(strC);
                replaceCount++;
            }
        }
	}
	
	//转到功能界面
	@FXML
	public void myTurnTo() throws IOException
	{
		Parent turntoRoot = FXMLLoader.load(getClass().getResource("turnto.fxml"));
		Scene turntoScene = new Scene(turntoRoot);
		
		turntoStage = new Stage();
		turntoStage.setScene(turntoScene);
		turntoStage.setTitle("转到指定行");
		turntoStage.setResizable(false);
		turntoStage.show();
	}
	
	//转到功能的实现
	public void turnto()
	{
		int turnto = global.turnto;
		int line;
		
        String strA, strB;
        String[] strings;
        strA = textArea.getText();
        strings = strA.split("\n");
        line = strings.length + 1;
        
        textArea.positionCaret(0);
        if(turnto >= line)
        {
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("跳行");
        	alert.setHeaderText("");
        	alert.setContentText("行数超过了总行数");
        	 
        	alert.showAndWait();
        	return;
        }
        
        int i = 0;
        int findStartPos = textArea.getCaretPosition();
        while (findStartPos <= strA.length()) 
        {
        	findStartPos = textArea.getCaretPosition();
            textArea.selectRange(0, findStartPos);
            strB = textArea.getSelectedText();
            strings = strB.split("\n");
            i = strings.length;
            if (i == turnto) 
            {
            	textArea.positionCaret(findStartPos);
                break;
            }
            findStartPos++;
            textArea.positionCaret(findStartPos);
        }
        
	}
	
	//全选
	//已知bug：文本过长时无法全选
	@FXML
	private void selectAll()
	{
		textArea.selectAll();
	}
	
	//插入时间日期
	@FXML
	private void timeAndDate()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		
		String str = 
					(hour + ":" + min + " " 
					+ calendar.get(Calendar.YEAR) + "/" 
					+ (calendar.get(Calendar.MONTH) + 1) 
					+ "/" + calendar.get(Calendar.DAY_OF_MONTH));
		
		textArea.insertText(textArea.getCaretPosition(), str);
	}
	
	//百度搜索功能
	@FXML
	private void baiduSearch()
	{
		String searchString = textArea.getSelectedText();
		try
		{
			java.net.URI uri = java.net.URI.create("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=" + searchString);
			java.awt.Desktop dp = java.awt.Desktop.getDesktop();
			if(dp.isSupported(java.awt.Desktop.Action.BROWSE))
			{
				try
				{
					dp.browse(uri);
				}
				catch(IOException e)
				{
					
				}
			}
		}
		catch(Exception e)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);//创建确认对话框
            alert.setTitle("自动搜索出错！");
            alert.setHeaderText("");
            alert.setContentText("搜索内容过长！请手动搜索！");
            alert.showAndWait();
            try
            {
            	java.net.URI uri = java.net.URI.create("www.baidu.com");
    			java.awt.Desktop dp = java.awt.Desktop.getDesktop();
    			if(dp.isSupported(java.awt.Desktop.Action.BROWSE))
    			{
    				try
    				{
    					dp.browse(uri);
    				}
    				catch(IOException e1)
    				{
    					
    				}
    			}
            }
            catch(Exception e2)
            {
            	
            }
		}
	}
	
	//设置状态栏是否可见
	@FXML
	private void statusLabelSet()
	{
		statusLabel.setVisible(fourthMenu_Status.isSelected());
	}
	
	//放大
	@FXML
	private void zoomin()
	{
		size++;
		zoomPercentage += 10;
		if(size >= 72)
			size = 72;
		if(zoomPercentage >= 500)
			zoomPercentage = 500;
		font = Font.font(global.fontFamily, global.fontWeight, global.fontPosture, size);
		textArea.setFont(font);
	}
	
	//缩小
	@FXML
	private void zoomout()
	{
		size--;
		zoomPercentage -= 10;
		if(size <= 1)
			size = 1;
		if(zoomPercentage <= 10)
			zoomPercentage = 10;
		font = Font.font(global.fontFamily, global.fontWeight, global.fontPosture, size);
		textArea.setFont(font);
	}
	
	//初始化缩放
	@FXML
	private void zoominit()
	{
		size=12;
		zoomPercentage = 100;
		font = Font.font(global.fontFamily, global.fontWeight, global.fontPosture, size);
		textArea.setFont(font);
	}
	
	//自动换行
	@FXML
	private void nextLine()
	{
		textArea.setWrapText(thirdMenu_ChangeLine.isSelected());
	}
	
	//查看帮助
	@FXML
	private void help()
	{
		java.net.URI uri = java.net.URI.create("https://www.bing.com/search?q=%e8%8e%b7%e5%8f%96%e6%9c%89%e5%85%b3+windows+10+%e4%b8%ad%e7%9a%84%e8%ae%b0%e4%ba%8b%e6%9c%ac%e7%9a%84%e5%b8%ae%e5%8a%a9&filters=guid:%224466414-zh-hans-dia%22%20lang:%22zh-hans%22&form=T00032&ocid=HelpPane-BingIA");
        java.awt.Desktop dp = java.awt.Desktop.getDesktop();
        if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) 
        {
            try 
            {
                dp.browse(uri);
            } catch (IOException e) 
            {
            }
        }
	}
	
	//关于记事本
	@FXML
	private void about()
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
        alert.setTitle("记事本");
        alert.setHeaderText("Java程序设计大作业——记事本程序");
        alert.setContentText("		作者：袁佳哲");
        alert.showAndWait();
	}
	
	//字体选择
	//字体设置已知bug：部分字体无法切换常规与粗体（推荐用微软雅黑进行测试）；部分字体斜体无法正常显示
	@FXML
	private void fontChooser()
	{
		Parent fontChooserRoot = null;
		try
		{
			fontChooserRoot = FXMLLoader.load(getClass().getResource("fontSet.fxml"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		Scene fontChooserScene = null;
		if(fontChooserRoot != null)
			fontChooserScene = new Scene(fontChooserRoot);
		fontStage = new Stage();
		fontStage.setTitle("字体");
		fontStage.setResizable(false);
		fontStage.setScene(fontChooserScene);
		fontStage.show();
	}
	
	//字体选择界面取消按钮操作
	public void fontChooserClose()
	{
		fontStage.close();
	}
	
	//字体选择界面确定按钮操作
	public void fontChooserConfirm()
	{
		fontColor = global.fontColor;
		font = global.font;
		size = global.size;
		textArea.setFont(font);
		textArea.setStyle("-fx-text-fill:" + fontColor);
	}
	
	//转到界面取消按钮操作
	public void turntoClose()
	{
		turntoStage.close();
	}

}
