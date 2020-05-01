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
	                hardScore -= 1;
	            }
	            else {
	            	// can add since these constraints are not violated
	                occupiedRooms.add(using);
	                double sectionCapacity = section.getCapacity();
	                double roomCapacity = room.getCapacity();
	                double percent = sectionCapacity/roomCapacity;
	                // check for how full the room is and score appropriately
	                if (percent == 1) { // optimal is having the room full
	                	softScore -= 0;
	                }
	                else if (percent < 1 && percent >= 0.9) {
	                	softScore -= 1;
	                }
	                else if (percent < 0.9 && percent >= 0.8) {
	                	softScore -= 2;
	                }
	                else if (percent < 0.8 && percent >= 0.7) {
	                	softScore -= 3;
	                }
	                else if (percent < 0.7 && percent >= 0.6) {
	                	softScore -= 4;
	                }
	                else if (percent < 0.6 && percent >= 0.5) {
	                	softScore -= 5;
	                }
	                else if (percent < 0.5 && percent >= 0.4) {
	                	softScore -= 6;
	                }
	                else if (percent < 0.4 && percent >= 0.3) {
	                	softScore -= 7;
	                }
	                else if (percent < 0.3 && percent >= 0.2) {
	                	softScore -= 8;
	                }
	                else if (percent < 0.2 && percent >= 0.1) {
	                	softScore -= 9;
	                }
	                else {
	                	softScore -= 10;
	                }
	                
	                // score based on location, less optimal further away but all are feasible so less penalty
	                // but location is more important than percent full, so slightly larger penalty
	                softScore -=  (room.getRank() + 11);

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