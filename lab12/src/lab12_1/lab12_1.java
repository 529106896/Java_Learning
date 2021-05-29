package lab12_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lab12_1 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String line;
		//文章选自帕特里克·亨利于1775年3月23日的著名演讲《不自由，毋宁死》
		File file = new File("src/lab12_1/Give_Me_Liberty_Or_Give_Me_Death.txt");
		if(!file.exists()) 
		{
			System.err.println("No such file!");
			return;
		}
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(file);
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		Pattern p=Pattern.compile("[;.,\"\\?!:—“”(){}]");
		while(scan.hasNextLine())
		{
			line = scan.nextLine();
			String[] lineWords = line.split("[ -]+");
			for(int i = 0; i < lineWords.length; i++)
			{
				Matcher m= p.matcher(lineWords[i]);
				lineWords[i] = m.replaceAll("");
				if(!lineWords[i].isEmpty())
				{
					if(!hashMap.containsKey(lineWords[i]))
					{
						hashMap.put(lineWords[i], 1);
					}
					else
					{
						int times = hashMap.get(lineWords[i]);
						times++;
						hashMap.put(lineWords[i], times);
					}
				}
				
			}
		}
		System.out.println("文章中包含的单词如下：");
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		list.sort(new Comparator<Map.Entry<String, Integer>>()
				{
					@Override
					public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
					{
						return o2.getValue().compareTo(o1.getValue());
					}
				});
		int wordsCount = 0;
		for(Map.Entry<String, Integer> entry : list)
		{
			System.out.println("单词：" + entry.getKey() + "  " + "次数：" + entry.getValue());
			wordsCount += entry.getValue();
		}
		System.out.println("单词数：" + wordsCount);
	}
}
