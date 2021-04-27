package lab7_3;

public class Bicycle implements CarbonFootprint
{
	private String bicycleName;
	private double price;
	private double maxSpeed;
	
	public Bicycle(String bicycleName, double price, double maxSpeed) 
	{
		this.bicycleName = bicycleName;
		this.price = price;
		this.maxSpeed = maxSpeed;
	}

	public String getBicycleName() {
		return bicycleName;
	}

	public void setBicycleName(String bicycleName) {
		this.bicycleName = bicycleName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	@Override
	public String toString()
	{
		return String.format("Bicycle's name is %s.%nPrice is %.2f.%nMax speed is %.2f.", 
				getBicycleName(), getPrice(), getMaxSpeed());
	}
	
	@Override
	public double getCarbonFootprint()
	{
		return getMaxSpeed();
	}
}
