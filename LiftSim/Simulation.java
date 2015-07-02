package LiftSim;
import java.util.ArrayList;
public class Simulation
{
	public static final int POS_INSIDE = -1;

	private Elevator      elevator;
	private Etage[]   floors;
	private ArrayList<Passenger> passenger;
	private int  fcount;

	public Simulation(int fcount)
	{
		passenger     = new ArrayList<Passenger>();
		this.elevator = new Elevator(fcount);
		this.fcount   = fcount;

		if (this.fcount<2) // Es müssen mindestens 2 Etagen existieren
			return;

		/**
		 * Allociert Platz für die Etagen und ruft deren Konstruktoren
		 * mit richtigen Paramatern auf.
		 */
		this.floors    = new Etage[fcount];
		this.floors[0] = new Etage(0,Etage.REL_BOTTOM, "Unten");
		for (int fnr=1; fnr<this.fcount-1; fnr++)
			this.floors[fnr] = new Etage(fnr, Etage.REL_MID, "Etage");
		this.floors[fcount-1] = new Etage(fcount-1, Etage.REL_TOP, "Oben");
	}

	/**
	 * @param fnr Etagennummer
	 * @param dir Richtung: 0 = runter, 1 = hoch)
	 */
	public void newCall(int fnr, int dir )
	{
		this.floors[fnr].setCall(dir);
	}
	public boolean move(int dir)
	{
		return this.elevator.move(dir);
	}
	public void newPassenger(int spos, int dest)
	{
		this.passenger.add(new Passenger(spos, dest, this.elevator));
	}
	/**
	 * nur für das debuggen
	 */
	public void nextStep()
	{
		for(Passenger p: this.passenger)
			p.doAction(this.floors[p.getPosition()]);
		this.elevator.doAction();
	}
	public void printStatus()
	{
		int call;
		String callStr;
		String elev;
		String pashere = " ";
		for (int fnr=this.fcount-1; fnr>=0; fnr--){	
			pashere = " ";
			for(Passenger pas: this.passenger){
				if (pashere == "@")
					break;
				pashere = pas.getPosition() == fnr ? "@" : " ";
			}
			elev = this.elevator.getPosition() == fnr ? "→ " : "  ";
			call = this.floors[fnr].getCall();
			switch (call){
				case 0: callStr = "  ";
						break;
				case 1: callStr = "↑ ";
						break;
				case 2: callStr = " ↓";
						break;
				case 3: callStr = "↑↓";
						break;
				default: callStr = "xx";
			}
			System.out.println( elev + " " + fnr + " " + callStr + pashere + this.elevator.overload());
		}
	}
}
