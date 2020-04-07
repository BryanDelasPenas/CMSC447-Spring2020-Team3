//POJO for each room
public class Room {
	
	//Variables
	private int capacity;
	private String classroom;
	
	//Overload Constructor 
	public Room(String name, int cap)
	{
		capacity = cap;
		classroom = name;
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

