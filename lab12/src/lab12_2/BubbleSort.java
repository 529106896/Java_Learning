package lab12_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BubbleSort 
{
	public static <T> void print(List<T> list)
	{
		if(list.isEmpty())
			return;
		for(T element : list)
		{
			System.out.print(element + " ");
		}
		System.out.println();
	}
	
	public static <T> void printArray(T[] list)
	{
		if(list.length == 0)
			return;
		for(T element : list)
		{
			System.out.print(element + " ");
		}
		System.out.println();
	}
	
	public static void printArray1(List<? extends Number> list)
	{
		if(list.isEmpty())
			return;
		for(Number element : list)
		{
			System.out.print(element + " ");
		}
		System.out.println();
	}
	
	public static <T extends Comparable<T>> void bubbleSort(List<T> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			for(int j = 0; j < list.size()-i-1; j++)
			{
				if(list.get(j).compareTo(list.get(j+1)) > 0)
				{
					T temp = list.get(j);
					list.set(j, list.get(j+1));
					list.set(j+1, temp);
				}
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		List<Integer> l1 = new ArrayList<>();
		Integer[] arr1 = {9,5,6,1,3,2,1,7,8,9,1,4,2,3,5,7};
		Collections.addAll(l1, arr1);
		
		System.out.println("整型数组排序前：");
		print(l1);
		bubbleSort(l1);
		System.out.println("整形数组排序后：");
		print(l1);

		List<Character> l2 = new ArrayList<>();
		String str1 = "yoGFJrej;lgds;/flkasADWkldkzxc.e;fej;lGresrf/e";
		for(int i = 0; i < str1.length(); i++)
		{
			l2.add(str1.charAt(i));
		}
		System.out.println("字符数组排序前：");
		print(l2);
		bubbleSort(l2);
		System.out.println("字符数组排序后：");
		print(l2);
		
		List<Double> l3 = new ArrayList<>();
		Double[] arr2 = {7.24124, 3.1415926,84651.5211245,1.465312,1.312564532,-412.1513245321};
		Collections.addAll(l3, arr2);
		System.out.println("浮点数组排序前：");
		print(l3);
		bubbleSort(l3);
		System.out.println("浮点数组排序后：");
		print(l3);
		
		List<String> l4 = new ArrayList<>();
		String[] arr3 = {"China", "America", "Austrilia", "Japan", "Russia"};
		Collections.addAll(l4, arr3);
		System.out.println("字符串数组排序前：");
		print(l4);
		bubbleSort(l4);
		System.out.println("字符串数组排序后：");
		print(l4);
		
	}
}
