package my_project;

public class test 
{
	public static void main(String[] args)
    {
        String str = "123456789";
        StringBuilder temp = new StringBuilder(str.length());
        for(int i = 0; i < str.length(); i++)
        {
        	temp.append('*');
        }
        System.out.println(temp);
    }
}
