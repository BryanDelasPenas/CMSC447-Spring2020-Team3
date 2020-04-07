// File Name:   CourseScheduler.java 
// Author:      Team 3
// Date:        04/06/2020
// Description: This is the main class where optimization happens.
//              Read in two excel files, fill in the data and optaplanner optimizes it
// TODO:        Finish schedule.csv 
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
		// Section -> Course 
		//         -> Room 
		//         -> Instructor
		
		// Create a string for the file name 
		String file_classroom = "src/CSV data files/Classroom.csv";

		// Set the buffered reader, 
		BufferedReader br_classroom = null; 

		// Create a empty string
		String line_classroom = ""; 

		// Create a count var to skip the first line 
		int iteration_classroom = 0;
		
		// Create a Section Placement object for when to optimize. 
		SectionPlacement opta_planner = new SectionPlacement();
		try
		{	
			br_classroom = new BufferedReader(new FileReader(file_classroom));
			List<Room> class_list = new ArrayList<Room>();
			while ((line_classroom = br_classroom.readLine()) != null)
			{
				if(iteration_classroom == 0)
				{
					iteration_classroom++;
					continue;
				}
				// This keeps the commas inside of the quotes
				String[] data_classroom = line_classroom.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				
				// Removes the starting quotes when necessary 
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
		
		// Create a filename for schedule file
		String file_schedule = "src/CSV data files/Schedule.csv";
		
		// Create a BR reader for schedule 
		BufferedReader br_schedule = null; 
		
		// Create an empty string for schedule 
		String line_schedule = "";
		
		// Create count var to skip the first line 
		int iteration_schedule = 0;
		try
		{
			String course_string = "";
			String course_number_string = "";
			String course_title_string = "";
			String version_string = "";
			String section_string = "";
			String instructor_string = "";
			String days_string = "";
			String time_string = "";
			String capacity_string = "";
			int section_int = 0;
			int time_int = 0;
			int capacity_int = 0;
			br_schedule = new BufferedReader(new FileReader(file_schedule));
			List<Section> schedule_list = new ArrayList<Section>();
			while((line_schedule = br_schedule.readLine()) != null) 
			{				
				if(iteration_schedule == 0)
				{
					iteration_schedule++;
					continue;
				}
				// This keeps the commas inside of the quotes
				String[] data_schedule = line_schedule.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				
				// Removes the starting quotes when necessary 
				data_schedule[5] = data_schedule[5].replaceAll("^\"|\"$", "");
				
				// Following are ints, Course[1], Section[4], Time[6], Capacity[7] 
				// Following are strings, Subject[0], Course Title[2], Version[3], Instructor[5]; 
				course_string = data_schedule[0];
				course_number_string = data_schedule[1];
				course_title_string = data_schedule[2];
				version_string = data_schedule[3];
				section_string = data_schedule[4];
				instructor_string = data_schedule[5];
						 
				// Gets the second element from string, example, tt530.charAt(3) = 5
				char second_element_time = data_schedule[6].charAt(2); 
				char first_element = data_schedule[6].charAt(0);
				
				// Checks for a MWF day 
				if(second_element_time == 'f' || second_element_time == 'F')
				{
					// MWF 
					days_string = "MoWeFr";
					time_string = data_schedule[6].substring(3,data_schedule[6].length());
				}
				else if(first_element == 'm' || first_element == 'M' )
				{
					// MW
					days_string = "MoWe";
					time_string = data_schedule[6].substring(2,data_schedule[6].length());
				}
				else if(first_element == 't' || first_element == 'T')
				{
					// TT
					days_string = "TuTh";
					time_string = data_schedule[6].substring(2,data_schedule[6].length());
				}
				capacity_string = data_schedule[7];
				
				// Convert the necessary strings to int
				//course_number_int = Integer.parseInt(course_number_string);
				section_int = Integer.parseInt(section_string);
				time_int = Integer.parseInt(time_string);
				capacity_int = Integer.parseInt(capacity_string);
				
				// Printing for testing purpose
				System.out.println("Subject: " + course_string + ", course num: " + course_number_string + ", Course Title: " + course_title_string + ", Version: " + version_string + ", Section: " + section_int + ", Instructor Real Name: " + instructor_string + ", Days: " + days_string + ", Time: " + time_int + ", Capacity: " + capacity_int);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		
		}
	
	}	
}