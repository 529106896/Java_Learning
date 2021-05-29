package lab12_4;

import java.util.Arrays;
import java.util.Scanner;

public class lab12_4 
{
	public static void main(String[] args)
	{
		//输入一个字符串
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入一行带空格的句子：");
		String line = scan.nextLine();
		scan.close();

		String[] splitWords = line.split("[ ]+");
		
		//句子内单词升序按升序排列输出
		System.out.println("升序输出所有单词：");
		
		//逆序输出
		//Arrays.stream(splitWords).sorted(Comparator.reverseOrder()).forEach(System.out::println);
		
		Arrays.stream(splitWords).sorted().forEach(System.out::println);
	}
}
