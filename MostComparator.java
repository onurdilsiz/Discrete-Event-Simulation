import java.util.Comparator;

public class MostComparator implements Comparator<Player>{
	
	public int compare(Player o1, Player o2) {
		if(o1.getWaitingInP()-o2.getWaitingInP()<0.0000000000000001){
			return 1;
		}else if(o1.getWaitingInP()-o2.getWaitingInP()>0.000000000000001){
			return -1;
		}
		else{// TODO Auto-generated method stub
		return 0;}
	}

}
