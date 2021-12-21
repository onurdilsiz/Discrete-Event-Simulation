import java.util.Comparator;

public class PhysioterapyComparator implements Comparator<Player>{
	
	public int compare(Player o1, Player o2) {
		if(o1.getTrainingtime()<o2.getTrainingtime()){
			return 1;
		}else if(o1.getTrainingtime()>o2.getTrainingtime()){
			return -1;
		}
		// TODO Auto-generated method stub
		return 0;
	}
		// TODO Auto-generated method stub
		
	

}
