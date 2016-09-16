package hw1;

public class Professor {

	private String Name;
	private int Age;
	private String ClassNumber;
	private int Salary;
	
	public Professor(String name, int age, String classNumber, int salary)
	{
		this.setName(name);
		this.setAge(age);
		this.setClassNumber(classNumber);
		this.setSalary(salary);
	}

	
	
	//region "Getters and Setters"
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getClassNumber() {
		return ClassNumber;
	}

	public void setClassNumber(String classNumber) {
		ClassNumber = classNumber;
	}

	public int getSalary() {
		return Salary;
	}

	public void setSalary(int salary) {
		Salary = salary;
	}
	//endRegion
	
	@Override
	public String toString()
	{
		String ProfessorString = "";
		ProfessorString += Name + " " + Age + " " + ClassNumber + " " + Salary;
		return ProfessorString;
	}
}
