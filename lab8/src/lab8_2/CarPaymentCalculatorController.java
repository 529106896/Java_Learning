package lab8_2;

import java.text.NumberFormat;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CarPaymentCalculatorController 
{
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
	
	private double loanRate = 0.05;
	
	private double[] loanRateArray = new double[] {0.0475, 0.0485, 0.0495, 0.0505};
	
	@FXML
	private TextField carPriceTextField;		//汽车价格文字框
	
	@FXML
	private TextField downPaymentTextField;		//首付金额文字框
	
	@FXML
	private TextField loanDurationTextField;	//贷款持续时间文字框
	
	@FXML
	private TextField monthlyPaymentTextField;	//每月应还款文字框
	
	@FXML
	private ChoiceBox<String> loanRateChoiceBox;		//贷款利率选择框
	
	
	
	@FXML
	private void calculateButtonPressed(ActionEvent event)
	{
		try
		{
			double carPrice = Double.parseDouble(carPriceTextField.getText());
			double downPayment = Double.parseDouble(downPaymentTextField.getText());
			double loan = carPrice - downPayment;
			double monthlyLoanRate = loanRate / 12;
			int loanDuration = 0;
			
			if(loanRate == 0.0475)
				loanDuration = 24;
			else if(loanRate == 0.0485)
				loanDuration = 36;
			else if(loanRate == 0.0495)
				loanDuration = 48;
			else if(loanRate == 0.0505)
				loanDuration = 60;
			
			//采用等额本息方法计算每月还款金额
			//每月还款 = 贷款本金 × 月利率 × （ 1 ＋月利率）＾还款月数］ ÷ ［（ 1 ＋月利率）＾还款月数－ 1 ］
			double monthlyPayment = (loan * monthlyLoanRate * Math.pow((1 + monthlyLoanRate), loanDuration))
					/ (Math.pow((1 + monthlyLoanRate), loanDuration) - 1);
			
			if(loanDuration == 0)
			{
				loanDurationTextField.setText("请选择贷款利率！");
				monthlyPaymentTextField.setText("请选择贷款利率！");
			}
			else
			{
				loanDurationTextField.setText(loanDuration + "个月");
				monthlyPaymentTextField.setText(currency.format(monthlyPayment));
			}
			
		}
		catch(NumberFormatException ex)
		{
			carPriceTextField.setText("请输入车价");
			carPriceTextField.selectAll();
			carPriceTextField.requestFocus();
			
			downPaymentTextField.setText("请输入首付金额");
			downPaymentTextField.selectAll();
			downPaymentTextField.requestFocus();
		}
	}
	
	//选择框
	public void initialize()
	{
		loanRateChoiceBox.setItems(FXCollections.observableArrayList("两年期 4.75%", "三年期 4.85%", "四年期 4.95%", "五年期 5.05%"));
		
		loanRateChoiceBox.getSelectionModel().selectedIndexProperty().addListener
		(
			(ObservableValue<? extends Number>ov, Number oldValue, Number newValue) ->{
				loanRate = loanRateArray[newValue.intValue()];
			}
		);
	}
}
