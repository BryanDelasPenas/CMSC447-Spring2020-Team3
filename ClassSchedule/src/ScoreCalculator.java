import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

public class ScoreCalculator implements EasyScoreCalculator<SectionPlacement> {
 
    @Override
    public Score calculateScore(SectionPlacement courseSchedule) {
        int hardScore = 0;
        int softScore = 0;
        Set<String> occupiedRooms = new HashSet<>();
        List<Section> sections = courseSchedule.getSectionList();
        for(Section section : sections) {
        	if(section.getRoom() != null) {
	            Room room = section.getRoom();
	            String using = room.getClassroom() + " : " + section.getTime() + " : " + section.getDay(); // room, time, days
	            // can't have class size > room capacity and 
	            // can't be added to a room that is already in use
	            if(occupiedRooms.contains(using) || section.getCapacity() > room.getCapacity()){
	                hardScore -= 25;
	            }
	            else {
	            	// can add since these constraints are not violated
	                occupiedRooms.add(using);
	                double sectionCapacity = section.getCapacity();
	                double roomCapacity = room.getCapacity();
	                double percent = sectionCapacity/roomCapacity;
	                
	                // check for how full the room is and score appropriately
	                if (percent == 1) { // optimal is having the room full
	                	hardScore -= 0;
	                }
	                else if (percent < 1 && percent >= 0.9) {
	                	hardScore -= 1;
	                }
	                else if (percent < 0.9 && percent >= 0.8) {
	                	hardScore -= 2;
	                }
	                else if (percent < 0.8 && percent >= 0.7) {
	                	hardScore -= 3;
	                }
	                else if (percent < 0.7 && percent >= 0.6) {
	                	hardScore -= 4;
	                }
	                else if (percent < 0.6 && percent >= 0.5) {
	                	hardScore -= 5;
	                }
	                else if (percent < 0.5 && percent >= 0.4) {
	                	hardScore -= 6;
	                }
	                else if (percent < 0.4 && percent >= 0.3) {
	                	hardScore -= 7;
	                }
	                else if (percent < 0.3 && percent >= 0.2) {
	                	hardScore -= 8;
	                }
	                else if (percent < 0.2 && percent >= 0.1) {
	                	hardScore -= 9;
	                }
	                else {
	                	hardScore -= 10;
	                }
	                
	                // score based on location, less optimal further away but all are feasible so less penalty
	                // but location is more important than percent full, so slightly larger penalty
	                if (room.getClassroom().contains("Public Policy")) { //.4mi, 7 mins walking
	                	hardScore -= 19;
	                }
	                else if (room.getClassroom().contains("Physics")) { //.3, 6 mins
	                	hardScore -= 18;
	                }
	                else if (room.getClassroom().contains("Biological Sciences")) { //.2, 4 mins
	                	hardScore -= 17;
	                }
	                else if (room.getClassroom().contains("Interdisciplinary Life")) { //.2mi, 3 mins
	                	hardScore -= 16;
	                }
	                else if (room.getClassroom().contains("Math & Psychology")) { //.1, 3 min
	                	hardScore -= 15;
	                }
	                else if (room.getClassroom().contains("Meyerhoff Chemistry")) { //.1, 2 mins
	                	hardScore -= 14;
	                }
	                else if (room.getClassroom().contains("Janet & Walter Sondheim")) { //443 ft, 2min
	                	hardScore -= 13;
	                }
	                else if (room.getClassroom().contains("Sherman Hall")) { // 39 ft, 1min
	                	hardScore -= 12;
	                }
	                else if (room.getClassroom().contains("Engineering")) { //equally close
	                	hardScore -= 11;
	                }
	                // ITE not needed since it will be 0 score change, best/optimal location
	            	section.setRoom(room);
	            }
        	} 
        	else {
        		hardScore += -1;
        	}
        }
        return HardSoftScore.valueOf(hardScore, softScore);
    }
}