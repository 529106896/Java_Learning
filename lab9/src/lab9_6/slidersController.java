package lab9_6;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class slidersController 
{
	@FXML private ImageView imageView1;
	@FXML private Label opacityLevelLabel;
	@FXML private Label sepiaLevelLabel;
	@FXML private Label scalingFactorLabel;
	@FXML private Slider opacityLevelSlider;
	@FXML private Slider sepiaLevelSlider;
	@FXML private Slider scalingFactorSlider;
	private SepiaTone sepiaEffect = new SepiaTone();
	private double opacityLevel = 1.0;
	
	public void initialize()
	{
		Image image1 = new Image("file:src/lab9_6/image.jpg");
		imageView1.setImage(image1);
		
		imageView1.setOpacity(opacityLevel);
		opacityLevelLabel.setText(String.format("%.2f", opacityLevel));
		scalingFactorLabel.setText(String.format("%.2f", 1.0));
		imageView1.setScaleX(1.0);
		imageView1.setScaleY(1.0);
		
		opacityLevelSlider.valueProperty().addListener
		(
				new ChangeListener<Number>()
				{
					@Override
					public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue)
					{
						opacityLevel = newValue.doubleValue() / 100.0;
						imageView1.setOpacity(opacityLevel);
						opacityLevelLabel.setText(String.format("%.2f", opacityLevel));
					}
				}
		);
		
		sepiaLevelSlider.valueProperty().addListener
		(
				new ChangeListener<Number>()
				{
					@Override
					public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue)
					{
						sepiaEffect.setLevel(newValue.doubleValue() / 100.0);
						imageView1.setEffect(sepiaEffect);
						sepiaLevelLabel.setText(String.format("%.2f", newValue.doubleValue() / 100.0));
					}
				}
		);
		
		scalingFactorSlider.valueProperty().addListener
		(
				new ChangeListener<Number>()
				{
					@Override
					public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue)
					{
						imageView1.setScaleX(newValue.doubleValue() / 100.0);
						imageView1.setScaleY(newValue.doubleValue() / 100.0);
						scalingFactorLabel.setText(String.format("%.2f", newValue.doubleValue() / 100.0));
					}
				}
		);
	}
}
