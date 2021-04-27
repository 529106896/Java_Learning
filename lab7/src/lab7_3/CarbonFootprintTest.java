package lab7_3;

import java.util.ArrayList;

public class CarbonFootprintTest 
{
	public static void main(String[] args)
	{
		Bicycle bicycle = new Bicycle("捷安特", 800.0, 20.0);
		Building building = new Building("双子塔", 1000.0, 150.0);
		Car car = new Car("奔驰", 900.0, "小型机动车");
		
		ArrayList<CarbonFootprint> testPrint = new ArrayList<>();
		
		testPrint.add(bicycle);
		testPrint.add(car);
		testPrint.add(building);
		
		for(CarbonFootprint temp : testPrint)
		{
			System.out.println(temp);
			System.out.println("Carbon footprint is " + temp.getCarbonFootprint() + "\n");
		}
	}
}
