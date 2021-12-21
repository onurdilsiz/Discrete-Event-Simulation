import java.util.Comparator;

public class LeastComparator implements Comparator<Player> {
	//CHECK
	public int compare(Player o1, Player o2) {
		if(o1.getWaitingInM()<o2.getWaitingInM()) {
			return -1;
		}else if(o1.getWaitingInM()> o2.getWaitingInM()){
			return 1;
		}
		// TODO Auto-generated method stub
		return o1.getId()-o2.getId();
	}
}
