package lab7_4;

public class StudentTest 
{
	public static void main(String[] args)
	{
		Student student = new Student("Jack", "福建省厦门市");;
		System.out.println(student);
		
		try
		{
			//System.out.println("Try set name to \"Wei Huang\"");
			student.setName("Wei Huang");
		}
		catch(IllegalNameException e)
		{
			//System.out.println("Name set Error!");
			e.printStackTrace();
		}
		
		try
		{
			//System.out.println("Try set address to \"福建厦门\"");
			student.setAddress("福建厦门");
		}
		catch(IllegalAddressException e)
		{
			//System.out.println("Address set Error!");
			e.printStackTrace();
		}
		
		System.out.println(student);
	}
}
