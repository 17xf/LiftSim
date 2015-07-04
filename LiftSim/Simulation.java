/**
 * @file LiftSim/Simulation.java
 */
package LiftSim;
import java.util.ArrayList;
public class Simulation
{
	public static final int POS_INSIDE = -1;

	private Elevator  elevator;
	private Floors    floors;
	private int       fcount;
	private int       step;

	private ArrayList<Passenger> passenger;

	/**
	 * @param fcount Anzahl der Etagen. Darf nicht kleine als 2 sein.
	 */
	public Simulation(int fcount)
	{
		passenger     = new ArrayList<Passenger>();
		this.elevator = new Elevator(fcount);
		this.fcount   = fcount;
		this.step     = 0;
		this.floors   = new Floors(fcount);
	}

	/**
	 * experimentelle Funktion
	 */
	public void newCall(int fnr, String dir )
	{
		this.floors.setCall(fnr, dir == "down" ? 0 : 1);
	}
	/**
	 * @param fnr Etagennummer
	 * @param dir Richtung: 0 = runter, 1 = hoch)
	 */
	public void newCall(int fnr, int dir )
	{
		this.floors.setCall(fnr, dir);
	}
	public boolean move(int dir)
	{
		return this.elevator.move(dir);
	}
	public void newRandPassenger()
	{
		this.passenger.add(new Passenger((int)(Math.random() * 6), (int)(Math.random() * 6), this.elevator));
	}
	public void newPassenger(int spos, int dest)
	{
		this.passenger.add(new Passenger(spos, dest, this.elevator));
	}
	public void nextStep()
	{
		for(Passenger p: this.passenger){
			if (p.getPosition() == POS_INSIDE){
				p.doAction();
			}else{
				Floor f = this.floors.getFloor(p.getPosition());
				p.doAction(f);
			}
		}
		this.elevator.doAction(this.floors.getFloor(this.elevator.getPosition()));
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
			call = this.floors.getCall(fnr);
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
			System.out.println(fnr + "	" + this.floors.getCall(fnr));
		System.out.println("Pos	Dir	Open	Load	");
		System.out.println(this.elevator.getPosition() + "	" + this.elevator.getDirection() + "	" + this.elevator.isOpen() + "	" + this.elevator.getLoad());
		System.out.println("Wishes");
		for (int w: this.elevator.getWishes())
			System.out.print(w + " ");
	}
}
