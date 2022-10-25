
import java.util.Comparator;

public class NameComparator implements Comparator<Student> {
	//Method to compare two students alphabetically by name
	@Override
	public int compare(Student s1, Student s2) {
		//Get the names of each student
		String s1Name = s1.getName();
		String s2Name = s2.getName();
		//Return -1 if the first student's name comes first alphabetically
		if(s1Name.compareTo(s2Name) < 0) {
			return -1;
		}
		//Return 1 if the second student's name comes first alphabetically
		else if(s1Name.compareTo(s2Name) > 0) {
			return 1;
		}
		//Otherwise, return 0 if the each student's name is the same
		else {
			return 0;
		}
	}
}
