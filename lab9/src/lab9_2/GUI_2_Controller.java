package lab9_2;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUI_2_Controller 
{
	@FXML
	private ImageView imageView1;
	
	@FXML
	private ImageView imageView2;
	
	@FXML
	private ImageView imageView3;
	
	public void initialize()
	{
		Image questionMark = new Image("file:src/lab9_2/questionMark.jpg");
		Image leaf = new Image("file:src/lab9_2/leave.png");
		Image lvhua = new Image("file:src/lab9_2/lvhua.png");
		imageView1.setImage(questionMark);
		imageView2.setImage(leaf);
		imageView3.setImage(lvhua);
	}
}
