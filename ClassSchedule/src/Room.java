//POJO for each room
public class Room {
	
	//Variables
	private int capacity;
	private String classroom;
	private double distance;
	private int rank; // used for distance score
	
	
	//Overload Constructor 
	public Room(String name, int cap)
	{
		capacity = cap;
		classroom = name;
	}
	
	// overloaded constructor for the ranking
	public Room(String name, double distance) {
	   this.classroom = name;
	   this.distance = distance;
	}

	// setter for rank
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	//Getter for distance
	public double getDistance() {
		return this.distance;
	}
	
	// Getter for rank
	public int getRank() {
		return this.rank;
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
		return classroom + " Capacity: " + Integer.toString(capacity);
	}
}

