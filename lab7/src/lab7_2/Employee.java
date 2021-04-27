package lab7_2;

public abstract class Employee implements Payable 
{
	private final String firstName;
	private final String lastName;
	private final String socialSecurityNumber;

	// constructor
	public Employee(String firstName, String lastName, String socialSecurityNumber) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
	}

	// return first name
	public String getFirstName() 
	{
		return firstName;
	}

	// return last name
	public String getLastName() 
	{
		return lastName;
	}

	// return social security number
	public String getSocialSecurityNumber() 
	{
		return socialSecurityNumber;
	}

	// return String representation of Employee object
	@Override
	public String toString() 
	{
		return String.format("%s %s%nsocial security number: %s", getFirstName(), getLastName(),
				getSocialSecurityNumber());
	}

	// Note: We do not implement Payable method getPaymentAmount here so
	// this class must be declared abstract to avoid a compilation error.
} // end abstract class Employee
