import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
//Class to Build the Lists of Data
public class CSVBuilder {

	public List<Room> createRooms(){
		ErrorCheck ec = new ErrorCheck();
		// Create a string for the file name 
		String file_distance = "src/CSV data files/Distance_from_ITE.csv";
		// Set the buffered reader, 
		BufferedReader br_rooms = null; 
		// Create a empty string
		String line_rooms = ""; 
		// Create a count var to skip the first line 
		int iteration_rooms = 0;		
		//to hold all of the rooms and rankings
		List<Room> room_rankings = new ArrayList<Room>(); 
		//Block to import room distance information
		try {	
			br_rooms = new BufferedReader(new FileReader(file_distance));
			while ((line_rooms = br_rooms.readLine()) != null)
			{
				// This is made to skip the first cell, title cell.
				if(iteration_rooms == 0)
				{
					iteration_rooms++;
					continue;
				}
				// This keeps the commas inside of the quotes
				String[] data_room = line_rooms.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);				
				ec.checkDistanceCsv(data_room);
				// Removes the starting quotes when necessary 
				String name = data_room[0].replaceAll("^\"|\"$", "");
				ec.checkRoomName(name);
				double distance = Double.parseDouble(data_room[1]);
				// add the room to list which will be used for rankings
				room_rankings.add(new Room(name, distance));
			}
			br_rooms.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		// sort the rooms based on distance away
		int size = room_rankings.size();  
		Room tempRoom;
		for(int i=0; i < size; i++){  
			for(int j=1; j < (size-i); j++) {
				if(room_rankings.get(j-1).getDistance() > room_rankings.get(j).getDistance()){  
					tempRoom = room_rankings.get(j-1);
					room_rankings.set(j-1, room_rankings.get(j));
					room_rankings.set(j, tempRoom);
				}
			}  
		}
		// give each room a rank, the further away the higher the rank
		int rank = 0;
		for (int i = 0; i < room_rankings.size(); i++) {
			tempRoom = room_rankings.get(i);
			System.out.println(tempRoom.toString());
			tempRoom.setRank(rank);
			rank++;
			room_rankings.set(i, tempRoom);
		}
		return room_rankings;

	}
	
	public SectionPlacement createSections(){
		//Create Objects to be used
		CSVBuilder dataCreate = new CSVBuilder();
		List<Room> room_rankings = dataCreate.createRooms();
		List<Room> room_list = new ArrayList<Room>();
		List<Section> schedule_list = new ArrayList<Section>();
		
		//Instantiate Error Checking Class
		ErrorCheck ec = new ErrorCheck();
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
				// check to be sure the csv is correct length & delimiter
				ec.checkClassroomCsv(data_classroom);
				// Removes the starting quotes when necessary 
				data_classroom[0] = data_classroom[0].replaceAll("^\"|\"$", "");
				
				// Changes data_classroom[1](capacity) into an int
				int cap = Integer.parseInt(data_classroom[1]);
				
				Room thisRoom = null;
				thisRoom = Room.makeRoom(data_classroom[0], cap, room_rankings);
				room_list.add(thisRoom);
			}
			br_classroom.close();
			
			//Loop to fix rankings
			for(Room r : room_list) {
				for(Room t: room_rankings) {
					if(r.getClassroom().contains(t.getClassroom())) {
						r.setRank(t.getRank());
						r.setDistance(t.getDistance());
					}
				}
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
				// check for valid input length
				ec.checkScheduleCsv(data_schedule);
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
						 
				//Check if data in csv is in acceptable format
				ec.checkCourseNum(course_number_string);
				ec.checkCourseName(course_title_string);
				ec.checkInstructor(instructor_string);
				ec.checkCourseSection(section_string);
				ec.checkCourseVersion(version_string);
				//Check the time before parsing to make readable
				ec.checkCourseTime(data_schedule[6]);
				
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
				//Check if the course capacity is valid
				ec.checkCap(capacity_string);
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
			br_schedule.close();
					
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		SectionPlacement uns = new SectionPlacement(room_list, schedule_list);
		return uns;
	}
	
	public void createDataFile(List<Section> temp) {
		// Create new file named temp.csv
		File temp_csv = new File("src/CSV data files/temp.csv");
		try
		{
			FileWriter temp_output = new FileWriter(temp_csv);
			CSVWriter writer_temp = new CSVWriter(temp_output);
			
			// Write for the headers 
			String [] header = {"Subject", "Course Number", "Course Title", "Course Section", "Day", "Time", "Instructor", "Version" , "Course Capacity", "Room", "Room Capacity"};
			writer_temp.writeNext(header);
			
			for(Section s:temp)
			{
				
				String[] cell = {s.getCourse().getSubject(), s.getCourse().getCourseNum(), s.getCourse().getCourseTitle(), s.getSectionNum(), s.getDay(), s.getTime(), s.getInstructor().toString(), s.getVersion(), Integer.toString(s.getCapacity()), s.getRoom().getClassroom(), Integer.toString(s.getRoom().getCapacity())};
				writer_temp.writeNext(cell);
			}	
			writer_temp.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();;
		}
		String file_temp = "src/CSV data files/temp.csv";
		String file_output = "src/CSV data files/output.csv";
		// Set the buffered reader, 
		BufferedReader br_temp = null; 
		
		// Create a empty string
		String line_temp = ""; 
		
		try
		{	
			BufferedWriter writer = null;
			writer = new BufferedWriter(new FileWriter(file_output));
			int iteration_output = 0;
			// Write for the headers 
			String [] header = {"Subject", "Course Number", "Course Title", "Course Section", "Day", "Time", "Instructor", "Version" , "Course Capacity", "Room", "Room Capacity"};
			for(String head:header)
			{
				head = head.replaceAll("^\"|\"$", "");
			}
		
			writer.write(String.join(",", header));
			writer.newLine();
			
			br_temp = new BufferedReader(new FileReader(file_temp));
			while ((line_temp = br_temp.readLine()) != null)
			{
				// This is made to skip the first cell, title cell.
				if(iteration_output == 0)
				{
					iteration_output++;
					continue;
				}
				
				// This keeps the commas inside of the quotes
				String[] data_temp = line_temp.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				
				// Removes the starting quotes when necessary only 6 and 9 
				data_temp[0] = data_temp[0].replaceAll("^\"|\"$", "");
				data_temp[1] = data_temp[1].replaceAll("^\"|\"$", "");
				data_temp[2] = data_temp[2].replaceAll("^\"|\"$", "");
				data_temp[3] = data_temp[3].replaceAll("^\"|\"$", "");
				data_temp[4] = data_temp[4].replaceAll("^\"|\"$", "");
				data_temp[5] = data_temp[5].replaceAll("^\"|\"$", "");
				//
				if(data_temp[6].indexOf(',') == -1)
				{
					data_temp[6] = data_temp[6].replaceAll("^\"|\"$", "");
				}
				data_temp[7] = data_temp[7].replaceAll("^\"|\"$", "");
				//
				data_temp[8] = data_temp[8].replaceAll("^\"|\"$", "");
				if(data_temp[9].indexOf(',') == -1)
				{
					data_temp[9] = data_temp[9].replaceAll("^\"|\"$", "");
				}
				data_temp[10] = data_temp[10].replaceAll("^\"|\"$", "");
				writer.write(String.join(",", data_temp));
				writer.newLine();
			}
			br_temp.close();
			writer.close();
			temp_csv.delete();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	
	}
	
}
