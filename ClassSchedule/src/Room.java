//POJO for each room
public class Room {
	
	//Variables
	private int capacity;
	private String classroom;
	
	//Setter for Room Capacity
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	//Setter for Room Name
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
	//Getters for Room Capacity
	public int getCapacity() {
		return capacity;
	}
	
	//Getter for Classroom Name
	public String getClassroom() {
		return classroom;
	}
	
	//To String function for ease of printing
	@Override
	public String toString() {
		return classroom + " " + Integer.toString(capacity);
	}
}

