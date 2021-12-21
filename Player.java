//The Player class has several fields, getters setters.
public class Player implements Comparable<Player> {
	private int id;
	private int skill;
	private double trainingtime;
	private int massageno;
	private boolean onProcess;
	private double trainingEntrance;
	private double exitPhysio;
	private double physioEntrance;
	private double massageEntrance;
	private double waitingInP;
	private double waitingInM;
	
	//Constuctor
	public Player(int id, int skill) {	
		this.id = id;
		this.skill = skill;
		this.trainingtime=0;
		this.massageno=0;
		this.exitPhysio=0;
		this.trainingEntrance=0;
		this.massageEntrance=0;
		this.physioEntrance=0;
		this.onProcess=false;
		this.waitingInP=0;
		this.waitingInM=0;
		
		
	}

	public void increaseWaitingmassage(double wait){
		this.waitingInM+=wait;
	}
	public double getWaitingInP() {
		return waitingInP;
	}
	public double getWaitingInM() {
		return waitingInM;
	}
	public void increaseWaitingphysio(double wait){
		this.waitingInP+=wait;
	}
	public double turnAround() {
		return exitPhysio-trainingEntrance;
	}
	public double getPhysioEntrance() {
		return physioEntrance;
	}
	public void setPhysioEntrance(double physioEntrance) {
		this.physioEntrance = physioEntrance;
	}
	public double getMassageEntrance() {
		return massageEntrance;
	}
	public void setMassageEntrance(double massageEntrance) {
		this.massageEntrance = massageEntrance;
	}
	public double getExitPhysio() {
		return exitPhysio;
	}
	public void setExitPhysio(double exitPhysio) {
		this.exitPhysio = exitPhysio;
	}
	public double getTrainingEntrance() {
		return trainingEntrance;
	}
	public void setTrainingEntrance(double trainingEntrance) {
			this.trainingEntrance = trainingEntrance;
	}
	public boolean isOnProcess() {
		return onProcess;
	}

	public void setOnProcess(boolean onProcess) {
		this.onProcess = onProcess;
	}
	public void massageTime(){
		
			this.massageno+=1;
			;
	}
	public int getMassageno() {
		return massageno;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", skill=" + skill + "]";
	}
	public double getTrainingtime() {
		return trainingtime;
	}
	public void setTrainingtime(double trainingtime) {
		this.trainingtime = trainingtime;
	}
	
	@Override
	public int compareTo(Player o) {
		if(this.trainingtime<o.trainingtime) {
		return 1;
	}
		else if(this.trainingtime>o.trainingtime)
			{return -1;
			}
		else{
			return this.id-o.id;
		}}
	public int getId() {
		return id;
	}
	public int getSkill() {
		return skill;
	}
	

}
