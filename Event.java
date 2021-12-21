// Event class
public class Event implements Comparable<Event> {
	private String type;
	private int player_id;
	private double time;
	private double duration;
	private String arrivalorexit;
	
	public String getType() {
		return type;
	}
	public double getDuration() {
		return duration;
	}
	// Constructor
	public Event(String type, int player_id, double time, double duration,String arrivalorexit) {
		this.type = type;
		this.player_id = player_id;
		this.time = time;
		this.duration=duration;
		this.arrivalorexit=arrivalorexit;
	}
	@Override
	public String toString() {
		return "[type=" + type + " id=" + player_id + ", time=" + time + ", duration="
				+ duration +" type2= "+arrivalorexit+ "]";
	}
	@Override 
	public int compareTo(Event o) {
		if(this.time-o.time>0) {
			return 1;
		}
		else  if(this.time-o.time<0) {
			return -1;
		}
		else if(this.time==o.time){
			if(this.arrivalorexit.equals("e")&&!o.arrivalorexit.equals("e")) {
				return -1;
			}else if(this.arrivalorexit.equals("e")&&this.type.equals("p")&&o.arrivalorexit.equals("e")&&o.type.equals("t")) {
				return -1;
			}
			else {
			if(this.player_id-o.player_id>0) {
				return 1 ;
			}else if(this.player_id-o.player_id<0) {
				return -1;
			}
			else {
				return 0;
			}
		}}
		else {return 0;}
		}
	public String getArrivalorexit() {
		return arrivalorexit;
	}
	
	public int getPlayer_id() {
		return player_id;
	}
	public double getTime() {
		return time;
	}
		
	}
	

