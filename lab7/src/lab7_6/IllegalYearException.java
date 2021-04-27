package lab7_6;

@SuppressWarnings("serial")
public class IllegalYearException extends Exception 
{
	public IllegalYearException()
	{
		super();
	}
	
	public IllegalYearException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public IllegalYearException(String message) 
	{
		super(message);
	}
	
	public IllegalYearException(Throwable cause)
	{
		super(cause);
	}
	
}