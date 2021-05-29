package lab13_3;

import java.util.HashMap;
import java.util.Map;

public class Pair <F, S>
{
	private Map<F, S> elements;
	
	public Pair(int capacity)
	{
		int initCapacity = capacity > 0? capacity : 10;
		elements = new HashMap<F, S>(initCapacity);
	}
	
	public Pair()
	{
		this(10);
	}
	
	public S get(F keyValue)
	{
		return elements.get(keyValue);
	}
	
	public void set(F addKey, S addValue)
	{
		elements.put(addKey, addValue);
	}
}
