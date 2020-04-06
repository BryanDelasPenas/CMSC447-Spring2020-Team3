import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CourseScheduler {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		// Read in both excel files, Classroom and Scheduler 
		// Read each file line by line.
		// Only stopping when you reach a comma. Then go to next element  
		// For Classroom, c_element[0] = Building and Room Location 
		//                c_element[1] = The Maximum Capacity of the Room 
	
		// For Schedule, s_element[0] = Subject       s_element[4] = Section Number
	    //               s_element[1] = Course Number s_element[5] = Instructor 
		//               s_element[2] = Course Title  s_element[6] = Time 
		//               s_element[3] = Version       s_element[7] = Maximum Capacity of the Class 
		
		// For each class will have the value to the corresponding element
		// class Course: 
		// 1. setSubject()      = s_element[0]
		// 2. setCourseNum()    = s_element[1]
		// 3. setCourseTittle() = s_element[2]
		
		// class Instructor:
		// 1. setInstructor()   = s_element[5]

		// class Room: 
		// setCapacity()        = c_element[1]
		// setClassroom()       = c_element[0]
		// setSection()         = class Section 

		// class Section:       Note: Since in element[6], the day and the time are combined, example "mw530"
		//                            You have to parse the string in the middle to get the time and the day
		// setDay()             = s_element[6]
		// setTime()            = s_element[6]
		// setSectionNum()      = s_element[4]
		// setCapacity()        = s_element[7]
		// setVersion()         = s_element[3]
		// setInstructor()      = class Instructor
		// setCourse()          = class Course
		
		// Class Relationships 
		// Room -> Course -> Course
		//                -> Instructor 
		
		
		// Create a string for the file name 
		String file_classroom = "src/CSV data files/Classroom.csv";

		// Set the buffered reader, a empty line and the delimiter for the CSV
		BufferedReader br = null; 
		String line_classroom = ""; 
		int iteration = 0;
		SectionPlacement opta_planner = new SectionPlacement();
		try
		{	
			br = new BufferedReader(new FileReader(file_classroom));
			List<Room> class_list = new ArrayList<Room>();
			while ((line_classroom = br.readLine()) != null)
			{
				if(iteration == 0)
				{
					iteration++;
					continue;
				}
				// This keeps the commas inside of the quotes
				String[] data_classroom = line_classroom.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				
				// Removes the starting quotes when neccessary 
				data_classroom[0] = data_classroom[0].replaceAll("^\"|\"$", "");
				
				// Changes data_classroom[1](capacity) into an int
				int cap = Integer.parseInt(data_classroom[1]);
	
				// Prints out for testing purposes
				System.out.println("classroom: " + data_classroom[0] + ", capacity:" + cap);
				class_list.add(new Room(data_classroom[0],cap));
			}
			// Sets the new list for opta_planner to work 
			opta_planner.setRoomList(class_list);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}	
}