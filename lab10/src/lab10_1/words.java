package lab10_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class words 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String line;
		File file = new File("src/lab10_1/report.txt");
		if(!file.exists()) 
		{
			System.err.println("No such file!");
			return;
		}
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(file);
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		Pattern p=Pattern.compile("[.,\"\\?!:—“”(){}]");
		while(scan.hasNextLine())
		{
			line = scan.nextLine();
			String[] lineWords = line.split("[ ]+");
			for(int i = 0; i < lineWords.length; i++)
			{
				Matcher m= p.matcher(lineWords[i]);
				lineWords[i] = m.replaceAll("");
				//System.out.println(lineWords[i]);
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
		Iterator<String> it = hashMap.keySet().iterator();
		while(it.hasNext())
		{
			String word = it.next();
			if(hashMap.get(word) >= 1)
				System.out.println(word);
		}
	}
}
