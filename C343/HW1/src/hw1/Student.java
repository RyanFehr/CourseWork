package hw1;

public class Student {

	
	private String Zipcode;
	private String Name;
	private int Salary;
	private Double Gpa;

	public Student(String zipcode, String name, int salary, Double gpa)
	{
		this.setZipcode(zipcode);
		this.setName(name);
		this.setSalary(salary);
		this.setGpa(gpa);
	}

	//region "Getters and Setters"
	public String getZipcode() {
		return Zipcode;
	}

	public void setZipcode(String zipcode) {
		this.Zipcode = zipcode;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public int getSalary() {
		return Salary;
	}

	public void setSalary(int salary) {
		this.Salary = salary;
	}
	
	public Double getGpa() {
		return Gpa;
	}

	public void setGpa(Double gpa) {
		this.Gpa = gpa;
	}
	//endRegion
	
	@Override
	public String toString()
	{
		String StudentString = "";
		StudentString += Zipcode + " " + Name+ " " + Salary+ " " + Gpa;
		return StudentString;
	}
}
