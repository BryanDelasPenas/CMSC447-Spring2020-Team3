import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

//Planning Entity for Section
public class Section {
	
	//Variable Declarations
	private String day;
	private String time;
	private String sectionNum;
	private int capacity;
	private String version;
	private Instructor instructor;
	private Course course;
	
	//Planning Variable
	@PlanningVariable(valueRangeProviderRefs = "roomRange")
	private Room room;
	
	//Overloaded Constructor
	public Section(Course course, String secNum, String day, String time, Instructor inst, String version, int cap){
		this.course = course;
		this.sectionNum = secNum;
		this.day = day;
		this.time = time;
		this.instructor = inst;
		this.version = version;
		this.capacity = cap;
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
	public String getSectionNum() {
		return sectionNum;
	}
	
	//Getter for Section Capacity
	public int getCapacity() {
		return capacity;
	}
	
	// Getter for Version of Classroom
	public String getVersion()
	{
		return version; 
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
