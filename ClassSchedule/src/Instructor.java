//POJO For our Instructors
public class Instructor {
		
	//Variable Declaration for Instructor
	private String instructor;
		
	//Setter for Instructor Name
	public void setInstructor(String instructor) {
		this.instructor = instructor;
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
