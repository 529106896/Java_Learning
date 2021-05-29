package lab13_3;

public class lab13_3 
{
	public static void main(String[] args)
	{
		Pair<String, Integer> p1 = new Pair<>();
		
		p1.set("Xiamen University", 1921);
		p1.set("Fujian Province", 9999);
		p1.set("Bei Jing", 1949);
		
		System.out.println(p1.get("Xiamen University"));
		System.out.println(p1.get("Bei Jing"));
		
	}
}
