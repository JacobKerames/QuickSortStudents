
//Student class
public class Student {
	//Private student fields
	private String name;
	private String address;
	private double GPA;
	
	//Constructor
	Student(String name, String address, double GPA) {
		this.name = name;
		this.address = address;
		this.GPA = GPA;
	}
	
	//Getter methods
	public String getName() {
		return this.name;
	}
	public String getAddress() {
		return this.address;
	}
	public double getGPA() {
		return this.GPA;
	}
}
