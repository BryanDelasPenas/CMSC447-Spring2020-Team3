import java.util.List;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

//Planning Solution Class so OptaPlanner knows this contains all input and outputs
@PlanningSolution
public class SectionPlacement {

	//Annotation for Optaplanner to select these rooms during runtime
	@ProblemFactCollectionProperty
	@ValueRangeProvider(id = "roomRange")
	private List<Room> roomList;
	//Annotate that OptaPlanner can change this during solving
	@PlanningEntityCollectionProperty
	private List<Section> sectionList;
	
	//Annotate to use this for the score
	@PlanningScore
	private HardSoftScore score;
	
	//Setter for roomList
	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}
	
	//Setter for sectionList
	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}
	
	//Getter for list of rooms
	public List<Room> getRoomSlotList(){
		return roomList;
	}
	
	//Getter for the list of Sections
	public List<Section> getSectionList() {
		return sectionList;
	}
	
	//Getter for the Score
	public HardSoftScore getScore() {
		return score;
	}
}
