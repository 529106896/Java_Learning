package lab5_4;

public class BasePlusCommissionEmployee 
{
	private CommissionEmployee commissionEmployee;
	private double baseSalary;
	
	public BasePlusCommissionEmployee(CommissionEmployee commissionEmployee, double baseSalary)
	{
		this.commissionEmployee = commissionEmployee;
		if(baseSalary < 0)
			throw new IllegalArgumentException("Base salary must be >= 0.0");
		
		this.baseSalary = baseSalary;
	}
	
	public String getFirstName() 
	{
		return commissionEmployee.getFirstName();
	}
	
	public String getLastName() 
	{
		return commissionEmployee.getLastName();
	}
	
	public String getSocialSecurityNumber() 
	{
		return commissionEmployee.getSocialSecurityNumber();
	}

	public double getGrossSales() 
	{
		return commissionEmployee.getGrossSales();
	}
	
	public double getCommissionRate() 
	{
		return commissionEmployee.getCommissionRate();
	}
	
	public void setGrossSales(double grossSales) 
	{
		commissionEmployee.setGrossSales(grossSales);
	}

	public void setCommissionRate(double commissionRate) 
	{
		commissionEmployee.setCommissionRate(commissionRate);
	}
	
	public void setBaseSalary(double baseSalary)
	{
	   if (baseSalary < 0.0)                   
	      throw new IllegalArgumentException(
	         "Base salary must be >= 0.0");  
	 
	   this.baseSalary = baseSalary;                
	} 
	
	public double getBaseSalary()
	{
	   return baseSalary;
	}
	
	public double earnings()
	{
	   return getBaseSalary() + commissionEmployee.earnings();
	}
	
	@Override
	public String toString()
	{
	   return String.format("%s %s%n%s: %.2f", "base-salaried",
	      commissionEmployee.toString(), "base salary", getBaseSalary());   
	}


}
