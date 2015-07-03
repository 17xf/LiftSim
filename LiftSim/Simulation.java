/**
 * @file LiftSim/Simulation.java
 */
package LiftSim;
import java.util.ArrayList;
public class Simulation
{
	public static final int POS_INSIDE = -1;

	private Elevator        elevator;
	private Floor[]         floors;
	private int             fcount;
	private int             step;

	private ArrayList<Passenger> passenger;

	public Simulation(int fcount)
	{
		passenger     = new ArrayList<Passenger>();
		this.elevator = new Elevator(fcount);
		this.fcount   = fcount;
		this.step     = 0;

		if (this.fcount<2) // Es müssen mindestens 2 Etagen existieren
			return;

		/**
		 * Allociert Platz für die Etagen und ruft deren Konstruktoren
		 * mit richtigen Paramatern auf.
		 */
		this.floors    = new Floor[fcount];
		this.floors[0] = new Floor(0,Floor.REL_BOTTOM, "Unten");
		for (int fnr=1; fnr<this.fcount-1; fnr++)
			this.floors[fnr] = new Floor(fnr, Floor.REL_MID, "Etage");
		this.floors[fcount-1] = new Floor(fcount-1, Floor.REL_TOP, "Oben");
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
	public void nextStep()
	{
		for(Passenger p: this.passenger){
			if (p.getPosition() == POS_INSIDE)
				p.doAction();
			else
				p.doAction(this.floors[p.getPosition()]);
		}
		this.elevator.doAction(this.floors[this.elevator.getPosition()]);
		this.step++;
	}
	/**
	 * nur für das debuggen
	 */
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
	public void printStatusList()
	{
		System.out.println("===== " + this.step + " =====" );
		System.out.println("Pos	Des");
		for(Passenger pas: this.passenger)
			System.out.println(pas.getPosition() + "	" + pas.getDestination());

		System.out.println("Floor	CallState");
		for (int fnr=this.fcount-1; fnr>=0; fnr--)
			System.out.println(fnr + "	" + this.floors[fnr].getCall());
		System.out.println("Pos	Dir	Open	Load	");
		System.out.println(this.elevator.getPosition() + "	" + this.elevator.getDirection() + "	" + this.elevator.isOpen() + "	" + this.elevator.getLoad());
		System.out.println("Wishes");
		for (int w: this.elevator.getWishes())
			System.out.print(w + " ");
	}
}
