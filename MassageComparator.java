import java.util.Comparator;

public class MassageComparator implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		if(o1.getSkill()<o2.getSkill()){
			return 1;
		}else if(o1.getSkill()>o2.getSkill()) {
			return -1;
		}
		// TODO Auto-generated method stub
		else { 
				if(o1.getMassageEntrance()-o2.getMassageEntrance()<0.0000000001) {
					return -1;			
				}else if(o1.getMassageEntrance()-o2.getMassageEntrance()>0.00000000001) {
					return 1;}
				else {
					return 0;
				}
				}
	}
	

}
