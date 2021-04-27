package lab9_5;

@SuppressWarnings("serial")
public class passwordConfirmException extends Exception 
{
	public passwordConfirmException()
	{
		super();
	}
	
	public passwordConfirmException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public passwordConfirmException(String message) 
	{
		super(message);
	}
	
	public passwordConfirmException(Throwable cause)
	{
		super(cause);
	}
	
}