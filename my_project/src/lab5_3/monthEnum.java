package lab5_3;

public class monthEnum 
{
	public enum Month
	{
		JAN("Jan", "January"), FEB("Feb", "Feburary"), 
		MAR("Mar", "March"), APR("Apr", "April"), 
		MAY("May", "May"), JUNE("June", "June"),
		JULY("July", "July"), AUG("Aug", "August"),
		SEPT("Sept", "September"), OCT("Oct", "Octobor"),
		NOV("Nov", "November"), DEC("Dec", "December");
		
		private final String abbr;
		private final String fullName;

		private Month(String abbr, String fullName) 
		{
			this.abbr = abbr;
			this.fullName = fullName;
		}
		
		public String getAbbr()
		{
			return abbr;
		}
		
		public String getFullName()
		{
			return fullName;
		}
		
	}
}
