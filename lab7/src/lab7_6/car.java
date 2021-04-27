package lab7_6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class car extends vehicle
{
	Scanner scan = new Scanner(System.in);
	//小汽车的厢数
	private String carriage;
	private String carName;
	private String brand;
	private String color;
	private double capacity;
	private int year;
	
	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}
	
	
	public String getCarriage()
	{
		return carriage;
	}

	public void setCarriage(String carriage) 
	{
		this.carriage = carriage;
	}

	public car(String[] inputs)
	{
		this.carName = inputs[0];
		this.brand = inputs[1];
		this.color = inputs[2];
		this.capacity = Double.parseDouble(inputs[3]);
		
		try
		{
			year = Integer.parseInt(inputs[4]);
			if(year <= 0 || year > 9999)
				throw new IllegalYearException();
		}
		catch(IllegalYearException e)
		{
			System.out.println("年份必须介于0-9999！请重新设置！");
			while(true)
			{
				year = scan.nextInt();
				if(year <= 0 || year > 9999)
				{
					System.out.println("年份必须介于0-9999！请重新设置！");
				}
				else
					break;
			}
			
		}
		
		this.carriage = inputs[5];
	}
	
	@Override
	public String toString()
	{
		return String.format("%s 载客量：%.0f人  厢数：%s", super.toString(), capacity, carriage);
	}
	
}
