import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorCheck {
	
	//Check if a course name follows typical convention
	public void checkCourseName(String cName) {
		Pattern p = Pattern.compile("^[ A-Za-z,&.:-]+$");
		Matcher m = p.matcher(cName);
		boolean isString = m.matches();
		
		if(cName.isEmpty()) {
			System.out.println("Cell is empty, please provide an input.");
			System.exit(0);
		}
		else if(!isString) {
			System.out.println(cName + " is not a valid string, the string must consist of only letters, commas, periods, colons, and ampersands.");
			System.exit(0);
		}
		System.out.println(cName + " is a valid string for a course.");
	}
	//Check if a capacity is within a range of 10-999
	public void checkCap(String cap) {
		Pattern p = Pattern.compile("^[1-9][0-9][0-9]?$");
		Matcher m = p.matcher(cap);
		boolean isString = m.matches();
		
		if(cap.isEmpty()) {
			System.out.println("Cell is empty, please provide an input.");
			System.exit(0);
		}
		else if(!isString) {
			System.out.println(cap + " is not a valid capacity, must be a number from 10-999");
			System.exit(0);
		}
		System.out.println(cap + " is a valid capacity.");
	}
	
	//Check if a instructor has a valid name matching conventions and taking into account the possibility for someone being a 3rd or 4th
	public void checkInstructor(String iName) {
		Pattern p = Pattern.compile("^[A-Z][a-z]+(, )?[A-Z]*[a-z]*( )?[1-9]?$");
		Matcher m = p.matcher(iName);
		boolean isString = m.matches();
		
		if(iName.isEmpty()) {
			System.out.println("Cell is empty, please provide an input.");
			System.exit(0);
		}
		else if(!isString) {
			System.out.println(iName + " is not a valid instructor name.");
			System.exit(0);
		}
		System.out.println(iName + " is a valid name for a instructor.");
	}
	
	//Check if a course number matches from 100-999 
	public void checkCourseNum(String cNum) {
		Pattern p = Pattern.compile("^[1-9][0-9][0-9][ces]{0,2}(/[1-9][0-9][0-9][ces]{0,2})?$");
		Matcher m = p.matcher(cNum);
		boolean isString = m.matches();
		
		if(cNum.isEmpty()) {
			System.out.println("Cell is empty, please provide an input.");
			System.exit(0);
		}
		else if (!isString) {
			System.out.println(cNum + " is not a valid course number, must be from 100-999.");
			System.exit(0);
		}
		System.out.println(cNum + " is a valid course number.");
	}
	//Matches time in course time in format of MW TT or MWF in increments of 30
	public void checkCourseTime(String cTime) {
		Pattern p = Pattern.compile("^(MW|TT|MWF|mw|tt|mwf)(1[0-2]|[1-9])(10|30)?$");
		Matcher m = p.matcher(cTime);
		boolean isString = m.matches();
		
		if(cTime.isEmpty()) {
			System.out.println("Cell is empty, please provide an input.");
			System.exit(0);
		}
		else if (!isString) {
			System.out.println(cTime + " is not a valid course time.");
			System.exit(0);
		}
		System.out.println(cTime + " is a valid course time.");
	}
	
	//Matches time in course time in format of MW TT or MWF in increments of 30
	public void checkRoomName(String rName) {
		Pattern p = Pattern.compile("^[A-Z]{1}[a-z]+ ([0-9]{3})?&? ([A-Z]{1}[a-z]+)? [A-Z]$");
		Matcher m = p.matcher(rName);
		boolean isString = m.matches();
		
		if(rName.isEmpty()) {
			System.out.println("Cell is empty, please provide an input.");
			System.exit(0);
		}
		else if (!isString) {
			System.out.println(rName + " is not a valid classroom name.");
			System.exit(0);
		}
		System.out.println(rName + " is a valid classroom name.");
	}
}
