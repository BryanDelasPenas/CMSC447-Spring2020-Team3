//POJO For our Instructors
public class Instructor {
		
	//Variable Declaration for Instructor
	private String instructor;
	
	//Overloaded Constructor
	Instructor(String name){
		instructor = name;
	}
	
	//Getter for Instructor Name
	public String getInstructor() {
		return instructor;
	}
	
	//To String function for easier printing
	@Override
	public String toString() {
		return instructor;
	}
}
