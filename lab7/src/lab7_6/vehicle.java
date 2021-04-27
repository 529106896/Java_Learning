package lab7_6;

//�����ؾ���
public class vehicle 
{
	
	private String carName;	//�ؾ�����
	private String brand;	//����Ʒ��
	private String color;	//������ɫ
	private double capacity;	//С�����ؿ����򿨳�������
	private int year;		//�������
	
	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) throws IllegalYearException 
	{
		if(year <= 0 || year > 9999)
			throw new IllegalYearException("Year must be > 0 and <= 9999");
		this.year = year;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s，品牌：%s 颜色：%s 出厂年份：%d", getCarName(), getBrand(), getColor(), getYear());
	}
}
