
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class SortStudents {
	public static int partition(LinkedList<Student> students, int i, int k) {
		//Declare and initialize variables
		int l = i;
		int h = k;
		int midpoint;
		Student pivot;
		Student temp;
		boolean done = false;
		
		//Create an instance of the name comparator
		NameComparator nameCompare = new NameComparator();
		
		//Choose the middle element as the pivot
		midpoint = i + (k - i) / 2;
		pivot = students.get(midpoint);
		
		while(!done) {
			//Increment l while the name of the student at index of l comes before the name of the student of pivot
			while(nameCompare.compare(students.get(l), pivot) == -1) {
				++l;
			}
			//Decrement h while the name of the student of pivot comes before the the name of the student at the index h
			while(nameCompare.compare(pivot, students.get(h)) == -1) {
				--h;
			}
			//If there is one or less elements left in the list, all students are partitioned.
			if(l >= h) {
				done = true;
			}
			else {
				//Swap the student at the index of l with the student at the index of h
				temp = students.get(l);
				students.set(l, students.get(h));
				students.set(h, temp);
				
				++l;
				--h;
			}
		}
		return h;
	}
	public static void quickSort(LinkedList<Student> students, int i, int k) {
		int j;
		
		//Check if list is already sorted
		if(i >= k) {
			return;
		}
		//Partition and set j to the location of last item in low partition
		j = partition(students, i, k);
		
		//Recursively sort high and low partitions
		quickSort(students, i, j);
		quickSort(students, j + 1, k);
	}
	public static void main(String[] args) {
		//Initialize variables
		String studentName;
		String studentAddr;
		double studentGPA;
		char userInput = 'Y';
		boolean done = false;
		
		//Create LinkedList to store Students objects
		LinkedList<Student> students = new LinkedList<Student>();
		//Create Scanner
		Scanner sc = new Scanner(System.in);
		//Create file objects
		FileOutputStream fileStream;
		PrintWriter outFS;
		
		//Loop for user to enter students' data
		while(!done) {
			switch(userInput) {
				//If the user input is 'Y', allow the user to add a student
				case 'Y':
					try {
						//Get student name
						System.out.println("Enter the student name: ");
						studentName = sc.nextLine();
						//Get student address
						System.out.println("Enter the student address: ");
						studentAddr = sc.nextLine();
						//Get student GPA
						System.out.println("Enter the student GPA: ");
						studentGPA = Double.parseDouble(sc.nextLine());
						
						//Validate GPA. Request a GPA until a GPA between 0 and 4 has been entered
						while(studentGPA > 4.0 || studentGPA < 0.0) {
							System.out.println("Invalid GPA. Please enter a valid GPA between 0 and 4: ");
							studentGPA = Double.parseDouble(sc.nextLine());
						}
						
						//Create the student object with the specified parameters
						students.addLast(new Student(studentName, studentAddr, studentGPA));
						//Get value to continue or end loop
						System.out.println("\nWould you like to enter a student? (Y/N)");
						userInput = sc.next().charAt(0);
						sc.nextLine();
					} catch(NumberFormatException e) {
						System.out.println("Invalid GPA. Please try again.\n");
					}
					//Break from switch statement
					break;
				//Handle program exit if user does not want to enter another student
				case 'N':
					//Sort students alphabetically, by name
					quickSort(students, 0, students.size() - 1);
					
					//Output contents to List of Students.txt
					try {
						//Create file
						fileStream = new FileOutputStream("/Users/jacobkerames/Desktop/Programming II/Eclipse/Sort Objects of LinkedList/List of Students.txt");
						outFS = new PrintWriter(fileStream);
						//Create file heading
						outFS.println("Students: \n");
						
						//Iteratively prints the information of each student object in the list
						for(Student student: students) {
							outFS.printf("Name: %s\nAddress: %s\nGPA: %,.2f\n\n", student.getName(), student.getAddress(), student.getGPA());
						}
						
						//Notify user that list of students was saved to file
						System.out.println("\nStudents have been sorted and saved to List of Students.txt");
						//Close PrintWriter
						outFS.close();
						//Close Scanner
						sc.close();
						//End loop
						done = true;
					} catch(IOException e) {
						System.out.println(e.getMessage());
					}
					//Break from switch statement;
					break;
				default:
					//Repeat getting the user input until they enter a valid input
					while(userInput != 'Y' && userInput != 'N') {
						System.out.println("Unable to process request. Please try again.\n");
						System.out.println("\nWould you like to enter a student? (Y/N)");
						userInput = sc.next().charAt(0);
						sc.nextLine();
					}
					//Break from switch statement;
					break;
			}
		}
	}
}
















