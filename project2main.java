import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.Scanner;

public class project2main {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en","US"));
		
		// TODO Auto-generated method stub
		File inFile = new File(args[0]);  // args[0] is the input file
		File outFile = new File(args[1]);  // args[1] is the output file
		
		PrintStream outstream1;//for printing outfile
		try {
		        outstream1 = new PrintStream(outFile);
		}catch(FileNotFoundException e2) {
		        e2.printStackTrace();
		        return;
		}
		Scanner reader;//to scan input
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			outstream1.close();
			return;
		}
		//ArrayLists , PQs,Hashmaps to use in algorithm
		ArrayList<Double>waitings=new ArrayList<Double>();
		PriorityQueue<Physioterapist> physioterapists=new PriorityQueue<Physioterapist>();
		HashMap<Integer,Player> playersmap=new HashMap<Integer,Player> ();
		HashMap<Player,Physioterapist>physiomap=new HashMap<Player,Physioterapist>();
		HashMap<Player,Massage>massagemap=new HashMap<Player,Massage>();
		PriorityQueue <Event> events=new PriorityQueue<Event>();
		PriorityQueue<Player> trainingqueue=new PriorityQueue<Player>(new TrainingComparator());
		PriorityQueue<Player> physioqueue=new PriorityQueue<Player>(new PhysioterapyComparator());
		PriorityQueue<Player> massagequeue=new PriorityQueue<Player>(new MassageComparator());
		PriorityQueue <Player> players=new PriorityQueue<Player>(new MostComparator());
		PriorityQueue<Player> threemassages=new PriorityQueue<Player>(new LeastComparator());
		//main variables which produces output
		double finaltime=0;
		int maxTraining=0;
		int maxPhysio=0;
		int maxMassage=0;
		double totalTraining=0;
		double totalwaitingT=0;
		double totalwaitingP=0;
		double totalwaitingM=0;
		int trainingno=0;
		double totalMassage=0;
		int massageno=0;
		double totalPhysio=0;
		int physiono=0;
		double turnaroundsums=0;
		int invalidmassages=0;
		int cancelledattempts=0;
	
		// scans  input file creates player objects
		Integer n=reader.nextInt();
		for(int  i=0;i<n;i++) {
			int id =Integer.parseInt(reader.next());
			int skill=Integer.parseInt(reader.next());
			Player newone=new Player(id,skill);
			players.add(newone);
			playersmap.put(id,newone);
		
		}

		
		Integer a=reader.nextInt();
		//scans and creates event
		for(int  i=0;i<a;i++) {
			String type=reader.next();
			int player=Integer.parseInt(reader.next());
			double arrivaltime=Double.parseDouble(reader.next());
			double duration=Double.parseDouble(reader.next());

			events.add(new Event(type,player,arrivaltime,duration,"a"));
			
		}	
		//scans physioterapist line
		int s_p=reader.nextInt();
		for(int i=0;i<s_p;i++) {
			physioterapists.add(new Physioterapist(i, Double.parseDouble(reader.next())));
			
		}
		//assign coach and masseur numbers
		int s_coach=Integer.parseInt(reader.next());
		int s_masseur=Integer.parseInt(reader.next());
		
		
		//In each loop according to type of event, checks that whether event satisfies the conditions and  do necessary regulations
		while(!events.isEmpty()) {
			
			
			//time sorted poll
			Event ax=events.poll();
			
		
			
			Player currentPlayer=playersmap.get(ax.getPlayer_id());
			//Checks whether player is busy or not, and also there are available coachs and adds queue or creates exit event
			
			if(ax.getType().equals("t") &&ax.getArrivalorexit().equals("a")) {
				if(currentPlayer.isOnProcess()){
					
					cancelledattempts+=1;
				}
				else {	
					currentPlayer.setOnProcess(true);
					currentPlayer.setTrainingEntrance(ax.getTime());
					currentPlayer.setTrainingtime(ax.getDuration());
					if(s_coach==0) {
						trainingqueue.add(currentPlayer);
						// to calculate maximum size of training queue 
						if(trainingqueue.size()>maxTraining) {
								maxTraining=trainingqueue.size();
//			
					}
					}
			
					else if(s_coach>0) {	
						Event exit=new Event("t",ax.getPlayer_id(),(ax.getTime()+ax.getDuration()),ax.getDuration(),"e");
						s_coach+=-1;
						events.add(exit);
					}

		
			}}
			// creates exit event
			else if(ax.getType().equals("t") &&ax.getArrivalorexit().equals("s")) {
				
				events.add(new Event("t",ax.getPlayer_id(),ax.getTime()+ax.getDuration(),ax.getDuration(),"e"));
			}
			// checks training queue and start physiotherapy process
			if(ax.getType().equals("t") &&ax.getArrivalorexit().equals("e")) {
				totalTraining+=ax.getDuration();
				trainingno+=1;
				s_coach+=1;
				totalwaitingT+=ax.getTime()-currentPlayer.getTrainingEntrance()-ax.getDuration();
				currentPlayer.setPhysioEntrance(ax.getTime());
				
				if(!trainingqueue.isEmpty()) {
					
					Player one=trainingqueue.poll();
					s_coach-=1;
					Event exit=new Event("t",one.getId(),ax.getTime(),one.getTrainingtime(),"s");
					events.add(exit);
				}
				if(physioqueue.isEmpty()) {
					if(!physioterapists.isEmpty()) {
						Physioterapist one=physioterapists.poll();
						Event arrivephys=new Event("p",ax.getPlayer_id(),(ax.getTime()),one.getServicetime(),"a");
						physiomap.put(currentPlayer,one);
						events.add(arrivephys);
					}else {
						physioqueue.add(currentPlayer);
						
						
						if(physioqueue.size()>maxPhysio) {
						
							maxPhysio=physioqueue.size();
						}
					}
				}
				
				else if(!physioqueue.isEmpty()){
					physioqueue.add(currentPlayer);
					
					if(physioqueue.size()>maxPhysio) {
						maxPhysio=physioqueue.size();
					}
					if(!physioterapists.isEmpty()) {
						Physioterapist one=physioterapists.poll();
						Player newcomer=physioqueue.poll();
						
						Event arrivephys=new Event("p",newcomer.getId(),(ax.getTime()),one.getServicetime(),"a");
						physiomap.put(newcomer,one);
						events.add(arrivephys);
					}
					
				}
				}

			//creates exit event
			else if(ax.getType().equals("p") &&ax.getArrivalorexit().equals("a")) {
				Event aa=new Event(ax.getType(),ax.getPlayer_id(),ax.getTime()+ax.getDuration(),ax.getDuration(),"e");
				events.add(aa);
			}
			// checks physioterapy processs
			
			else if(ax.getType().equals("p") &&ax.getArrivalorexit().equals("e")) {
				
			
				
				currentPlayer.setOnProcess(false);
				currentPlayer.setExitPhysio(ax.getTime());
				turnaroundsums+=currentPlayer.turnAround();
				
				totalPhysio+=ax.getDuration();
				physiono+=1;
				
				double waiting=ax.getTime()-ax.getDuration()-currentPlayer.getPhysioEntrance();
				
				if(waiting<=0.000000001) {
					waiting=0;
				}

				currentPlayer.increaseWaitingphysio(waiting);	
				totalwaitingP+=waiting;
				physioterapists.add(physiomap.get(currentPlayer));
							
				if(!physioqueue.isEmpty()){
					if(!physioterapists.isEmpty()) {
						Physioterapist one=physioterapists.poll();
						Player newcomer=physioqueue.poll();
						physiomap.put(newcomer,one);
						Event arrivephys=new Event("p",newcomer.getId(),(ax.getTime()),one.getServicetime(),"a");
						events.add(arrivephys);
					}
				}
			}
			
			//checks whether it is invalid or canceled, applies process
				else if(ax.getType().equals("m") &&ax.getArrivalorexit().equals("a")){
					if(currentPlayer.getMassageno()==3) {
						invalidmassages+=1;
					}
					else if(currentPlayer.isOnProcess()) {
						
						cancelledattempts+=1;
						
						
					}else {
						currentPlayer.setMassageEntrance(ax.getTime());
						currentPlayer.setOnProcess(true);
						currentPlayer.massageTime();
						
						if(s_masseur!=0) {
								Massage mass=new Massage("m",ax.getPlayer_id(),ax.getTime()+ax.getDuration(),ax.getDuration(),"e");
								events.add(mass);
								s_masseur-=1;
							}
						else if(s_masseur==0){
							massagequeue.add(currentPlayer);
							if(massagequeue.size()>maxMassage) {
								maxMassage=massagequeue.size();
							}
							massagemap.put(currentPlayer,new Massage("m",ax.getPlayer_id(),ax.getTime(),ax.getDuration(),"a"));							
						}
						}
				}
			//starting massage from queue
				else if(ax.getType().equals("m") &&ax.getArrivalorexit().equals("s")) {
					
					events.add(new Event("m",ax.getPlayer_id(),ax.getTime()+ax.getDuration(),ax.getDuration(),"e"));
					
				}
			//Massage exit process
				else if(ax.getType().equals("m") &&ax.getArrivalorexit().equals("e")){
					//	currentPlayer.setOnMassageProcess(false);
						currentPlayer.setOnProcess(false);
						s_masseur+=1;
						totalMassage+=ax.getDuration();
						massageno+=1;
						
						double wait=ax.getTime()-currentPlayer.getMassageEntrance()-ax.getDuration();
						if(wait<=0.0000000001) {
							wait=0;
						}
						
//						System.out.println(wait+" "+ax);
						waitings.add(wait);
						totalwaitingM+=wait;
						currentPlayer.increaseWaitingmassage(wait);
						if(currentPlayer.getMassageno()==3) {
							threemassages.add(currentPlayer);
							
						}
						if(!massagequeue.isEmpty()) {
							Player massa=massagequeue.poll();
							Massage massage=massagemap.get(massa);
							s_masseur-=1;
							events.add(new Massage("m",massa.getId(),ax.getTime(),massage.getDuration(),"s"));
						}
						
					}
			
			if(events.size()==0) {
				finaltime=ax.getTime();
				
			}

		}
		
		
		
		PriorityQueue <Player> players2=new PriorityQueue<Player>(new MostComparator());
		PriorityQueue <Player> threemassages2=new PriorityQueue<Player>(new LeastComparator());
		for(Player p:players) {
			
			players2.add(p);
		}
		for(Player p:threemassages) {
			threemassages2.add(p);
		
		}
		Player themost=players2.peek();
		Player theleast=playersmap.get(0);
	
		if(!threemassages2.isEmpty()) {
			theleast=threemassages2.peek();
		}
		
		// calculating average
		double avewaitingT=totalwaitingT/trainingno;
		double avewaitingP=totalwaitingP/trainingno;
		double avewaitingM=totalwaitingM/massageno;
		double avetraining=totalTraining/trainingno;
		double avemassage=totalMassage/massageno;
		double avephysio=totalPhysio/physiono;
		double aveturnaround=turnaroundsums/trainingno;
		
	
		//printing file
		outstream1.println( maxTraining);
		outstream1.println(maxPhysio);
		outstream1.println(maxMassage);
		outstream1.println(String.format("%.3f",Math.abs(avewaitingT)));
		outstream1.println(String.format("%.3f",Math.abs(avewaitingP)));
		outstream1.println(String.format("%.3f",Math.abs(avewaitingM)));
		outstream1.println(String.format("%.3f",Math.abs(avetraining)));
		outstream1.println(String.format("%.3f",Math.abs(avephysio)));
		outstream1.println(String.format("%.3f",Math.abs(avemassage)));
		outstream1.println(String.format("%.3f",Math.abs(aveturnaround)));

		outstream1.println(themost.getId()+" "+String.format("%.3f",Math.abs(themost.getWaitingInP())));
		if(!threemassages.isEmpty()) {
			outstream1.println(theleast.getId()+" "+String.format("%.3f",Math.abs(theleast.getWaitingInM())));
		}
		else {
			outstream1.println(-1+" "+-1);
		}
		outstream1.println(invalidmassages);
		outstream1.println(cancelledattempts);
		outstream1.println(String.format("%.3f",finaltime));
		
		reader.close();
		outstream1.close();
	
		}
		
		}



