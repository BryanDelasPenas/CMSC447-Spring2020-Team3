// File Name:   CourseScheduler.java 
// Author:      Team 3
// Date:        04/06/2020
// Description: This is the main class where optimization happens.
//              Read in two excel files, fill in the data and optaplanner optimizes it
import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;


public class CourseScheduler 
{
	public static void main(String[] args) 
	{
		CSVBuilder createData = new CSVBuilder();
		//Create new SectionPlacement Entity for Optaplanner to Solve
		SectionPlacement unsolved = createData.createSections();
		//SolverFactory to use an xml to solve the problem
		SolverFactory<SectionPlacement> solverFactory = SolverFactory.createFromXmlResource("sectionPlacementSolver.xml");
		//Solver to build
		Solver<SectionPlacement> solver = solverFactory.buildSolver();
		//Solved Section Placement
		SectionPlacement solved = solver.solve(unsolved);
		//Create a temp list of sections
		List<Section> temp = new ArrayList<Section>();
		//Print out ClassRoom Placement
		temp.addAll(solved.getSectionList());
		
		//Check if all classes can be placed
		HardSoftScore finalScore = solved.getScore();
		int Final = finalScore.getHardScore();
		if(Final < 0) {
			System.out.println("Cannot place classrooms optimally as there will be overlap, please modify the input files. Exiting the program.");
			System.exit(0);
		}
		else {
		//Populate the data
		createData.createDataFile(temp);
		}
	}	
		
}