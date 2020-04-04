//POJO for Course
public class Course {
	
	//Variable Declarations for Course
	private String subject;
	private int courseNum;
	private String courseTitle;
	
	//Setter for Course Subject
	public void setSubject(String subject){
		this.subject = subject;
	}
	//Setter for Course Num
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	//Setter for Course Title
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	//Getter for Course Subject
	public String getSubject() {
		return subject;
	}
	
	//Getter for Course Number
	public int getCourseNum() {
		return courseNum;
	}
	
	//Getter for Course Title
	public String getCourseTitle() {
		return courseTitle;
	}

	//To String function for easier printing
	@Override
	public String toString() {
		return subject + " " + Integer.toString(courseNum) + " " + courseTitle;
	}
}

