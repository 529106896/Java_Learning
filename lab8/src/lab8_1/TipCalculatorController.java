package lab8_1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController { 
   // formatters for currency and percentages
   private static final NumberFormat currency = 
      NumberFormat.getCurrencyInstance();
   private static final NumberFormat percent = 
      NumberFormat.getPercentInstance();
   
   private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default
   
   private Boolean splitByPeople = false;	//false default
   
   // GUI controls defined in FXML and used by the controller's code
   @FXML 
   private TextField amountTextField; 

   @FXML
   private Label tipPercentageLabel; 

   @FXML
   private Slider tipPercentageSlider;

   @FXML
   private TextField tipTextField;

   @FXML
   private TextField totalTextField;
   
   @FXML
   private TextField PeopleTextField;
   
   @FXML
   private TextField EvenBillTextField;
   
   @FXML
   private CheckBox cb;

   // calculates and displays the tip and total amounts
   @FXML
   private void calculateButtonPressed(ActionEvent event) {
      try {
         BigDecimal amount = new BigDecimal(amountTextField.getText());
         BigDecimal people = new BigDecimal(1);
         if(splitByPeople == true)
         {
        	 people = new BigDecimal(PeopleTextField.getText());
         }
         BigDecimal tip = amount.multiply(tipPercentage);
         BigDecimal total = amount.add(tip);
         BigDecimal evenBill = total.divide(people);

         tipTextField.setText(currency.format(tip));
         totalTextField.setText(currency.format(total));
         EvenBillTextField.setText(currency.format(evenBill));
      }
      catch (NumberFormatException ex) {
         amountTextField.setText("Enter amount");
         amountTextField.selectAll();
         amountTextField.requestFocus();
         
         PeopleTextField.setText("Enter people");
         PeopleTextField.selectAll();
         PeopleTextField.requestFocus();
      }
   }

   // called by FXMLLoader to initialize the controller
   public void initialize() 
   {
      // 0-4 rounds down, 5-9 rounds up 
      currency.setRoundingMode(RoundingMode.HALF_UP);
      
      // listener for changes to tipPercentageSlider's value
      tipPercentageSlider.valueProperty().addListener
      (
         new ChangeListener<Number>() 
         {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) 
            {
               tipPercentage = 
                  BigDecimal.valueOf(newValue.intValue() / 100.0);
               tipPercentageLabel.setText(percent.format(tipPercentage));
            }
         }
      );
      
      //增加一个checkBox，用来监控是否需要均摊
      cb.selectedProperty().addListener
      (
    	  new ChangeListener<Boolean>() 
    	  {
	    	  @Override
	    	  public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) 
	    	  {
	    		  splitByPeople = newValue;
	    	  }
    	  }
      );
 
   }
}
