package lab7_3;

public class Car implements CarbonFootprint
{
	private String carName;			//车名
	private double emissionVolume;	//排量
	private String type;			//类型（大车/小车）
	
	public Car(String carName, double emissionVolume, String type) 
	{
		this.carName = carName;
		this.emissionVolume = emissionVolume;
		this.type = type;
	}

	public String getCarName() 
	{
		return carName;
	}

	public void setCarName(String carName) 
	{
		this.carName = carName;
	}

	public double getEmissionVolume() 
	{
		return emissionVolume;
	}



	public void setEmissionVolume(double emissionVolume) 
	{
		this.emissionVolume = emissionVolume;
	}



	public String getType() 
	{
		return type;
	}



	public void setType(String type) 
	{
		this.type = type;
	}
	
	@Override
	public String toString()
	{
		return String.format("Car's name is %s.%nEmissionVolume is %.2f.%nType is %s.", 
				getCarName(), getEmissionVolume(), getType());
	}
	
	@Override
	public double getCarbonFootprint()
	{
		return getEmissionVolume();
	}
}
