package lab5_3;

public class monthEnumTest 
{
	public static void main(String[] args)
	{
		for(monthEnum.Month m : monthEnum.Month.values())
		{
			System.out.println(m.getAbbr());
			System.out.println(m.getFullName());
		}
	}
}
