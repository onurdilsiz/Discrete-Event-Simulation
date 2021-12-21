import java.util.Comparator;

public class TrainingComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		if(o1.getTrainingEntrance()<o2.getTrainingEntrance()){
			return -1;
		}else if(o1.getTrainingEntrance()>o2.getTrainingEntrance()) {
			return 1;
		}
		// TODO Auto-generated method stub
		return 0;
	}
		// TODO Auto-generated method stub
		
	}
	


