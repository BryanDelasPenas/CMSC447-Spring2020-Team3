import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

//Planning Entity for Section
public class Section {
	
	//Variable Declarations
	private String day;
	private String time;
	private int sectionNum;
	private int capacity;
	private Instructor instructor;
	private Course course;
	
	//Planning Variable
	@PlanningVariable(valueRangeProviderRefs = "roomRange")
	private Room room;
	
	//Planning Id
	@PlanningId
	private Long id;
	
	//Setter for Section ID
	public void setId(long id) {
		this.id = id;
	}
	
	//Setter for Section Day
	public void setDay(String day) {
		this.day = day;
	}
	
	//Setter for Section Time
	public void setTime(String time) {
		this.time = time;
	}
	
	//Setter for Section Number
	public void setSectionNum(int sectionNo) {
		this.sectionNum = sectionNo;
	}
	
	//Setter for Section Capacity
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	//Setter for Section Instructor
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	//Setter for Section Course Assignment
	public void setCourse(Course course) {
		this.course = course;
	}
	
	//Getter for Section ID
	public long getId() {
		return id;
	}
	
	//Getter for Section Day
	public String getDay() {
		return day;
	}
	
	//Getter for Section Time
	public String getTime() {
		return time;
	}
	
	//Getter for Section Number
	public int getSectionNum() {
		return sectionNum;
	}
	
	//Getter for Section Capacity
	public int getCapacity() {
		return capacity;
	}
	
	//Getter for Section Instructor
	public Instructor getInstructor() {
		return instructor;
	}
	
	//Getter for Section Course Information
	public Course getCourse() {
		return course;
	}
	
	//Getter for Section Room Information
	public Room getRoom() {
		return room;
	}
}
