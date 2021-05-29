package lab13_2;

public class lab13_2 
{
	public static boolean isEqualTo(Object v4, Object v5)
	{
		if(v4.equals(v5))
			return true;
		else
			return false;
	}

	public static void main(String[] args)
	{
		Integer v1 = 2, v2 = 2, v3 = 4;
		if(isEqualTo(v1, v2))
		{
			System.out.println("v1 equals to v2");
		}
		else
			System.out.println("v1 doesn't equal to v2");
		
		if(isEqualTo(v1, v3))
		{
			System.out.println("v1 equals to v3");
		}
		else
			System.out.println("v1 doesn't equal to v3");
		
		Object v4 = new Integer(2);
		Object v5 = new Integer(3);
		Object v6 = new Integer(3);
		
		if(isEqualTo(v4, v5))
		{
			System.out.println("v4 equals to v5");
		}
		else
			System.out.println("v4 doesn't equal to v5");
		
		if(isEqualTo(v5, v6))
		{
			System.out.println("v5 equals to v6");
		}
		else
			System.out.println("v5 doesn't equal to v6");
	}
}
