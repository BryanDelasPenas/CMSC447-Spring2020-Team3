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
		Pattern p = Pattern.compile("^[A-Z]{0,1}[a-z]+(, )?[A-Z]*[a-z]*( )?[1-9]?$");
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
	
	//Checks for room name consisting of only characters or numbers and ampersands
	public void checkRoomName(String rName) {
		Pattern p = Pattern.compile("^[A-Za-z0-9 &]+$");
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
	
	//Checks for a section consisting of only a single number
	public void checkCourseSection(String section) {
		Pattern p = Pattern.compile("^[1-9]{1}$");
		Matcher m = p.matcher(section);
		boolean isString = m.matches();
		
		if(section.isEmpty()) {
			System.out.println("Cell is empty, please provide an input.");
			System.exit(0);
		}
		else if (!isString) {
			System.out.println(section + " is not a valid section number.");
			System.exit(0);
		}
		System.out.println(section + " is a valid section number.");
	}
	
	//Checks for version consisting of only a single letter
	public void checkCourseVersion(String version) {
		Pattern p = Pattern.compile("^[a-z]{1}$");
		Matcher m = p.matcher(version);
		boolean isString = m.matches();
		//This is allowed to have an empty string
		if(version.isEmpty()) {
			return;
		}
		if (!isString) {
			System.out.println(version + " is not a valid version.");
			System.exit(0);
		}
		System.out.println(version + " is a valid version.");
	}

	// makes sure the format of the classroom file is correct (CSV and correct number of variables) 
	public void checkClassroomCsv(String data[]) {
		if (data.length != 2) {
			System.out.println("Classroom CSV input does not have a valid length - check delimiter to make sure it's a csv");
			System.exit(0);
		}
		else {
			System.out.println("Classroom CSV has correct delimiter and data length");
			return;
		}
	}
	
	// makes sure the format of the distance from ite file is correct (CSV and correct number of variables) 
	public void checkDistanceCsv(String data[]) {
		if (data.length != 2) {
			System.out.println("Distance from ITE CSV input does not have a valid length - check delimiter to make sure it'ss a csv");
			System.exit(0);
		}
		else {
			System.out.println("Distance from ITE CSV has correct delimiter and data length");
			return;
		}
	}
	
	public void checkScheduleCsv(String data[]) {
		if (data.length < 8) {
			System.out.println("Schedule CSV input does not have a valid length (too short). Make sure there are 8 comma separated columns.");
			System.exit(0);
		}
		if (data.length > 8) {
			System.out.println("Schedule CSV input does not have a valid length (too long). Make sure there are 8 comma separated columns.");
			System.exit(0);
		}
		else {
			System.out.println("Correct number of columns in schedule csv.");
			return;
		}

	}
}
