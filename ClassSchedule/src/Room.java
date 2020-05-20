import java.util.List;

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
	
	//Overloaded constructor for the ranking
	public Room(String name, double distance) {
	   this.classroom = name;
	   this.distance = distance;
	}

	// setter for rank
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	//Setter for distance
	public void setDistance(double distance) {
		this.distance = distance;
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
	
	public static Room makeRoom(String name, int capacity, List<Room> rooms) {
		Room room = new Room(name, capacity);
		for (Room thisRoom : rooms) {
			if (name.equals(thisRoom.getClassroom())) {
				room.setRank(thisRoom.getRank());
				return room;
			}
		}
		// if not in the list
		room.setRank(0);
		return room;
	}
	
	//To String function for ease of printing
	@Override
	public String toString() {
		return classroom + " Capacity: " + Integer.toString(capacity) + " Rank: " + rank + " Distance: " + distance;
	}
}

