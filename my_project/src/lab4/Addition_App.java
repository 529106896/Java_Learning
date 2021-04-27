package lab4;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Addition_App extends Application
{
	public static void main(String[] args)
	{
		launch(args);
	}

	public void start(Stage stage)
	{
		Label label1 = new Label("Number 1");
		Label label2 = new Label("Number 2");
		Label label3 = new Label("Number 3");
		
		TextField textField1 = new TextField();
		TextField textField2 = new TextField();
		TextField textField3 = new TextField();
		
		Button button = new Button("加法");
		
		button.setOnAction
		(
			new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent event)
				{
					int c = Integer.parseInt(textField1.getText()) + Integer.parseInt(textField2.getText());
					textField3.setText("" + c);
				}
			}
		);
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(20));
		hBox.setSpacing(20);
		hBox.setAlignment(Pos.BOTTOM_CENTER);
		hBox.getChildren().addAll(button);
		GridPane rootPane=new GridPane();
		rootPane.setPadding(new Insets(10));
		rootPane.add(label1, 1, 0);
		rootPane.add(textField1, 1, 2);
		rootPane.add(label2, 3, 0);
		rootPane.add(textField2,3, 2);
		rootPane.add(label3, 5, 0);
		rootPane.add(textField3, 5, 2);
		rootPane.add(hBox, 0, 3, 6, 2);
		Scene scene=new Scene(rootPane,400,120);
		stage.setTitle("加法小程序");
		stage.setScene(scene);
		stage.show();
	}
}
