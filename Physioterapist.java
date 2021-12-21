
public class Physioterapist implements Comparable<Physioterapist>{
	private int id;
	private double servicetime;
	public Physioterapist(int id, double servicetime) {
		
		this.id = id;
		this.servicetime = servicetime;
	}
	public double getServicetime() {
		return servicetime;
	}
	@Override
	public String toString() {
		return "Physioterapist [id=" + id + ", servicetime=" + servicetime + "]";
	}
	@Override
	public int compareTo(Physioterapist o) {
		// TODO Auto-generated method stub
		return this.id-o.id;
	}
	
}
