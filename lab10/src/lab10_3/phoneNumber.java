package lab10_3;

import java.util.Scanner;

public class phoneNumber 
{
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args)
	{
		System.out.println("请按“区号+电话号码”的格式输入号码，示例：(555)555-5555");
		String input = scan.nextLine();
		String[] tokens = input.split("\\)");
		
		String[] areaCodeGroup = tokens[0].split("\\D");
		String areaCode = areaCodeGroup[1];
		String[] phoneNumber = tokens[1].split("\\D");
		
		System.out.println("区号：" + areaCode);
		System.out.print("电话号码：");
		
		for(String str : phoneNumber)
		{
			System.out.print(str);
		}
		System.out.println();
	}
}
