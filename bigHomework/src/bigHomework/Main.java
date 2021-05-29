package bigHomework;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application
{
	public static Map<String, Object> controllers = new HashMap<String, Object>();
	public static Map<String, Object> globals = new HashMap<String, Object>();
	
	Global global = new Global();
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		globals.put("global", global);
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("MainPane.fxml"));
		fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		Parent root = fxmlLoader.load();
		primaryStage.setTitle("myNotePad");
		primaryStage.setScene(new Scene(root));
		primaryStage.getIcons().add(new Image("file:src/bigHomework/icon.png"));
		primaryStage.show();
		
		notepadController controller = fxmlLoader.getController();
		controller.mainStage(primaryStage);
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
				{
					@Override
					public void handle(WindowEvent event)
					{
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);//创建确认对话框
		                alert.setTitle("关闭");
		                alert.setHeaderText("确定要退出？");
		                alert.setContentText("");
		                Optional<ButtonType> result = alert.showAndWait();
		                if (result.get() == ButtonType.OK) 
		                {
		                    if (controller.getStatus() != 3) 
		                    {
		                    	Alert alert1 = new Alert(AlertType.INFORMATION);
		                    	alert1.setTitle("请先保存文件！");
		                    	alert1.setHeaderText(null);
		                    	alert1.setContentText("您还未保存当前编辑，请先保存文件！");
		                    	 
		                    	alert1.showAndWait();
		                    	
		                        if (controller.getCurrentPath() == null) 
		                        {
		                            controller.exitSave();
		                        } else 
		                        {
		                            controller.save();
		                        }
		                    }
		                    primaryStage.close();
		                } 
		                else 
		                {
		                    event.consume();
		                }
		            }
				});
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
}
