package lab7_2;

public class PayableInterfaceTest 
{
	public static void main(String[] args)
	{
		Payable[] payableObjects = new Payable[6];
		payableObjects[0] = new Invoice("0001", "wheel", 2, 55.00);
		payableObjects[1] = new Invoice("0002", "glass", 3, 66.00);
		payableObjects[2] = new SalariedEmployee("Jonathan", "Joestar", "111-111", 100.0);
		payableObjects[3] = new HourlyEmployee("Joseph", "Joestar", "222-222", 80.0, 5.0);
		payableObjects[4] = new CommissionEmployee("Kujo", "Jotaro", "333-333", 1000, 0.6);
		payableObjects[5] = new BasePlusCommissionEmployee("Higashikata", "Josuke", "444-444", 5000, 0.04, 300);
		
		System.out.println("Invoices and Employees processed polymorphically:");
		
		for(Payable current : payableObjects)
		{
			//note: instanceof用来判断一个对象是否是一个类的实例
			if(current instanceof BasePlusCommissionEmployee)
			{
				double newBaseSalary = 1.10 * ((BasePlusCommissionEmployee) current).getBaseSalary();
				((BasePlusCommissionEmployee) current).setBaseSalary(newBaseSalary);
				System.out.printf("%nnew base salary with 10%% increase is: $%,.2f", ((BasePlusCommissionEmployee) current).getBaseSalary());
			}
			System.out.printf("%n%s %n%s: $%,.2f%n", current.toString(), // could
					// invoke
					// implicitly
					"payment due", current.getPaymentAmount());
		}
	}
}
