//POJO for Course
public class Course {
	
	//Variable Declarations for Course
	private String subject;
	private String courseNum;
	private String courseTitle;
	
	//Overloaded Constructor
	public Course(String sub, String num, String title){
		subject = sub;
		courseNum = num;
		courseTitle = title;
	}
	
	//Getter for Course Subject
	public String getSubject() {
		return subject;
	}
	
	//Getter for Course Number
	public String getCourseNum() {
		return courseNum;
	}
	
	//Getter for Course Title
	public String getCourseTitle() {
		return courseTitle;
	}

	//To String function for easier printing
	@Override
	public String toString() {
		return subject + " " + courseNum + " " + courseTitle;
		
	}
}

