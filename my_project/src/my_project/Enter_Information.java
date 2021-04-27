package my_project;

import java.util.Scanner;

public class Enter_Information 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		double num = 0.0;
		double sum = 0.0;
		int count = 0;

		System.out.println("Please enter numbers, enter \"end\" to quit entering:");
		System.out.printf("The %dth number is: ", ++count);
		while(scan.hasNextDouble())
		{
			System.out.printf("The %dth number is: ", ++count);
			num = scan.nextDouble();
			sum += num;
		}
		
		System.out.println("The last number is " + num);
		System.out.println("The amout of numbers is " + (count-1));
		System.out.println("The sum is " + sum);
		System.out.println("The average of data is " + (sum/(count-1)));
		
		scan.close();
	}
}
