package lab12_5;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class lab12_5 
{
	public static void main(String[] args)
	{
		System.out.println
		(
				IntStream.rangeClosed(1, 10).
				map(x -> x*4).
				filter(x -> {return x%3 == 0;}).
				mapToObj(x -> String.valueOf(x)).
				collect(Collectors.joining())
		);
	}
}
