import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

public abstract class ScoreCalculator 
  implements EasyScoreCalculator<SectionPlacement> {
 
    @Override
    public Score calculateScore(SectionPlacement courseSchedule) {
        int hardScore = 0;
 
        Set<String> occupiedRooms = new HashSet<>();
        List<Section> sections = courseSchedule.getSectionList();
        for(Section section : sections) {
            Room room = section.getRoom();
            String using = room.getClassroom() + " : " + section.getTime() + " : " + section.getDay(); // room, time, days

            // can't have class size > room capacity and 
            // can't be added to a room that is already in use
            if(occupiedRooms.contains(using) || section.getCapacity() > room.getCapacity()){
                hardScore -= 15;
            }
            else {
            	// can add since these constraints are not violated
                occupiedRooms.add(using);
                
                // score based on location, less optimal further away but all are feasible so less penalty
                if (room.getClassroom().contains("Public Policy")) { //.4mi, 7 mins walking
                	hardScore -= 9;
                }
                else if (room.getClassroom().contains("Physics")) { //.3, 6 mins
                	hardScore -= 8;
                }
                else if (room.getClassroom().contains("Biological Sciences")) { //.2, 4 mins
                	hardScore -= 7;
                }
                else if (room.getClassroom().contains("Interdisciplinary Life")) { //.2mi, 3 mins
                	hardScore -= 6;
                }
                else if (room.getClassroom().contains("Math & Psychology")) { //.1, 3 min
                	hardScore -= 5;
                }
                else if (room.getClassroom().contains("Meyerhoff Chemistry")) { //.1, 2 mins
                	hardScore -= 4;
                }
                else if (room.getClassroom().contains("Janet & Walter Sondheim")) { //443 ft, 2min
                	hardScore -= 3;
                }
                else if (room.getClassroom().contains("Sherman Hall")) { // 39 ft, 1min
                	hardScore -= 2;
                }
                else if (room.getClassroom().contains("Engineering")) { //equally close
                	hardScore -= 1;
                }
                // ITE not needed since it will be 0 score change, best/optimal location
            }
        }
 
        
        return HardSoftScore.valueOf(hardScore, 0);
    }
}