import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorCheck {
	
	//Check if a course name follows typical convention
	public void checkCourseName(String cName) {
		Pattern p = Pattern.compile("^[ A-Za-z,&.:]+$");
		Matcher m = p.matcher(cName);
		boolean isString = m.matches();
		
		if(!isString) {
			System.out.println(cName + " is not a valid string, the string must consist of only letters");
			System.exit(0);
		}
		System.out.println(cName + " is a valid string for a course.");
	}
	//Check if a capacity is within a range of 10-999
	public void checkCourseCap(String cap) {
		Pattern p = Pattern.compile("^[1-9][0-9][0-9]*+$");
		Matcher m = p.matcher(cap);
		boolean isString = m.matches();
		
		if(!isString) {
			System.out.println(cap + " is not a valid capacity for a room.");
			System.exit(0);
		}
		System.out.println(cap + " is a valid capacity for a room.");
	}
	//Check if a instructor has a valid name matching conventions and taking into account the possibility for someone being a 3rd or 4th
	public void checkInstructor(String iName) {
		Pattern p = Pattern.compile("^[A-Za-z ,1-9]+$");
		Matcher m = p.matcher(iName);
		boolean isString = m.matches();
		
		if(!isString) {
			System.out.println(iName + " is not a valid instructor name.");
			System.exit(0);
		}
		System.out.println(iName + " is a valid name for a instructor.");
	}
	
	//Check if a course number matches from 100-999 
	public void checkCourseNum(String cNum) {
		Pattern p = Pattern.compile("^[1-9][0-9][0-9]+$");
		Matcher m = p.matcher(cNum);
		boolean isString = m.matches();
		
		if(!isString) {
			System.out.println(cNum + " is not a valid course number.");
			System.exit(0);
		}
		System.out.println(cNum + " is a valid course number.");
	}
	
	
}
