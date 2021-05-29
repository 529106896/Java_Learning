package lab12_3;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lab12_3 
{
	public static void main(String[] args)
	{
		String sentence = "Hello, hello, hello, what's your name? "
				+ "What's your Name?My name is Donald Trump. "
				+ "My my mY Dream dream drEAm is to MAKE AMERICA america GREAT gREAT AGAIN!";
		//System.out.println(sentence);
		String[] Words = sentence.split("[ ]+");
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		Pattern p=Pattern.compile("[;.,\"\\?!:—“”(){}]");
		
		for(int i = 0; i < Words.length; i++)
		{
			Matcher m= p.matcher(Words[i]);
			Words[i] = m.replaceAll("");
			Words[i] = Words[i].toLowerCase();
			if(!Words[i].isEmpty())
			{
				if(!hashMap.containsKey(Words[i]))
				{
					hashMap.put(Words[i], 1);
				}
				else
				{
					int times = hashMap.get(Words[i]);
					times++;
					hashMap.put(Words[i], times);
				}
			}	
		}
		
		System.out.println("句子中重复的单词如下：");
		for(Map.Entry<String, Integer> entry : hashMap.entrySet())
		{
			String word = entry.getKey();
			Integer times = entry.getValue();
			if(times > 1)
				System.out.println("单词：" + word + "  " + "次数：" + times);
		}
		
		
	}
}
