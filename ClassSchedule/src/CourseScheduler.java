// File Name:   CourseScheduler.java 
// Author:      Team 3
// Date:        04/06/2020
// Description: This is the main class where optimization happens.
//              Read in two excel files, fill in the data and optaplanner optimizes it
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*; 
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import java.io.File;
import java.io.FileNotFoundException;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;

public class CourseScheduler {

	public static void main(String[] args) 
	{
		List<Room> room_list = new ArrayList<Room>();
		List<Section> schedule_list = new ArrayList<Section>();
		// Create a string for the file name 
		String file_classroom = "src/CSV data files/Classroom.csv";

		// Set the buffered reader, 
		BufferedReader br_classroom = null; 

		// Create a empty string
		String line_classroom = ""; 

		// Create a count var to skip the first line 
		int iteration_classroom = 0;
		
		//Block to import classroom information
		try
		{	
			br_classroom = new BufferedReader(new FileReader(file_classroom));
			while ((line_classroom = br_classroom.readLine()) != null)
			{
				// This is made to skip the first cell, title cell.
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
				//System.out.println("Classroom: " + data_classroom[0] + ", Capacity:" + cap);
			room_list.add(new Room(data_classroom[0],cap));
			}
			for(Room r : room_list) {
				System.out.println(r.toString());
			}
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
			// Defintions for the strings and int needed 
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
			int end_time_int=0;
			int capacity_int = 0;
			br_schedule = new BufferedReader(new FileReader(file_schedule));
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
				// Check for m 
				else if(first_element == 'm' || first_element == 'M' )
				{
					// MW
					days_string = "MoWe";
					time_string = data_schedule[6].substring(2,data_schedule[6].length());
				}
				// Check for t
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
				end_time_int = time_int+115;
				capacity_int = Integer.parseInt(capacity_string);
				
				
				// Printing for testing purpose
				//System.out.println("Subject: " + course_string + ", course num: " + course_number_string + ", Course Title: " + course_title_string + ", Version: " + version_string + ", Section: " + section_int + ", Instructor Real Name: " + instructor_string + ", Days: " + days_string + ", Time: " + time_int + "-" + end_time_int + ", Capacity: " + capacity_int);
				
				//Create objects to create new sections
				Course course = new Course(course_string, course_number_string, course_title_string);
				Instructor inst = new Instructor(instructor_string);
				Section section = new Section(course, section_string, days_string, time_string, inst, version_string, capacity_int);
				schedule_list.add(section);
			
			}
			//Print out data to test
			//for(Section s: schedule_list) {
			//	System.out.println(s.toString());
			//}
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		//Create new SectionPlacement Entity for Optaplanner to Solve
		SectionPlacement unsolved = new SectionPlacement(room_list, schedule_list);
		//SolverFactory to use an xml to solve the problem
		SolverFactory<SectionPlacement> solverFactory = SolverFactory.createFromXmlResource("sectionPlacementSolver.xml");
		//Solver to build
		Solver<SectionPlacement> solver = solverFactory.buildSolver();
		//Solved Section Placement
		SectionPlacement solved = solver.solve(unsolved);
		
		List<Section> temp = new ArrayList<Section>();
		//Print out ClassRoom Placement
		temp.addAll(solved.getSectionList());
		
		// Create new file named output.csv
		File output_csv = new File("src/CSV data files/output.csv");
		try
		{
			FileWriter output = new FileWriter(output_csv);
			CSVWriter writer = new CSVWriter(output);
			
			// Write for the headers 
			String [] header = {"Subject", "Course Number", "Course Title", "Course Section", "Day", "Time", "Instructor", "Version" , "Course Capacity", "Room", "Room Capacity"};
			writer.writeNext(header);
			
			for(Section s:temp)
			{
				
				String[] cell = {s.getCourse().getSubject(), s.getCourse().getCourseNum(), s.getCourse().getCourseTitle(), s.getSectionNum(), s.getDay(), s.getTime(), s.getInstructor().toString(), s.getVersion(), Integer.toString(s.getCapacity()), s.getRoom().getClassroom(), Integer.toString(s.getRoom().getCapacity())};
				writer.writeNext(cell);
				// Testing Purposes
				// System.out.println(s.getCourse().toString() + s.toString() + " Room: " + s.getRoom().toString()+ '\n' );
				// System.out.print(s.toString());
				// System.out.print(" Room: " + s.getRoom().toString()+ '\n');
			}	
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();;
		}
	}	
		
}