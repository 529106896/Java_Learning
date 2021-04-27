package my_project;

import java.util.*;

public class lab2_q1 {
	public static void main(String[] arg)
	{
		Scanner scan = new Scanner(System.in);
		
		int num1 = 0;
		String str1 = "0";
		boolean bool = false;
		double f1 = 0.0;
		
		System.out.print("请输入一个字符串：");
		str1 = scan.nextLine();
		System.out.print("按照print方法输出：" + str1 + '\n');
		System.out.printf("按照printf方法输出：%s\n", str1);
		System.out.println("按照println方法输出：" + str1);
		
		System.out.print("请输入一个整数：");
		num1 = scan.nextInt();
		System.out.print("按照print方法输出：" + num1 + '\n');
		System.out.printf("按照printf方法输出：%d\n", num1);
		System.out.println("按照println方法输出：" + num1);
		
		System.out.print("请输入一个布尔值：");
		bool = scan.nextBoolean();
		System.out.print("按照print方法输出：" + bool + '\n');
		System.out.printf("按照printf方法输出：%b\n", bool);
		System.out.println("按照println方法输出：" + bool);
		
		System.out.print("请输入一个浮点数：");
		f1 = scan.nextDouble();
		System.out.print("按照print方法输出：" + f1 + '\n');
		System.out.printf("按照printf方法输出：%.3f\n", f1);
		System.out.println("按照println方法输出：" + f1);
		
		scan.close();
	}
	
}
