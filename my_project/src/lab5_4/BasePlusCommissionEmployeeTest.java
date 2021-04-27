package lab5_4;

public class BasePlusCommissionEmployeeTest 
{
	public static void main(String[] args)
	{
		BasePlusCommissionEmployee employee = 
			new BasePlusCommissionEmployee
			(new CommissionEmployee("Jiazhe", "Yuan", "111-11-111", 5000, 0.04), 300);
		
		System.out.println("First Name:" + employee.getFirstName());
		System.out.println("Last Name:" + employee.getLastName());
		System.out.println("Security Number:" + employee.getSocialSecurityNumber());
		System.out.println("Gross Sales:" + employee.getGrossSales());
		System.out.println("Commission rate:" + employee.getCommissionRate());
		System.out.println("Base salary:" + employee.getBaseSalary());
		
		employee.setBaseSalary(1000);
		
		System.out.printf("%n%s:%n%s%n", "更新信息：", employee.toString());
	}
}
