/**
 * @file LiftSim/Simulation.java
 */
package LiftSim;
import java.util.ArrayList;
public class Simulation
{
	public static final int POS_INSIDE = -1;

	private Elevator  elevator;
	private int       fcount;
	private Floors    floors;
	private int       step;

	private ArrayList<Passenger> passenger;

	/**
	 * @param fcount Anzahl der Etagen. Darf nicht kleine als 2 sein.
	 */
	public Simulation(int fcount)
	{
		this.floors    = new Floors(fcount);
		this.elevator  = new Elevator(fcount, this.floors);
		this.passenger = new ArrayList<Passenger>();
		this.fcount    = fcount;
		this.step      = 0;
	}

	/**
	 * @param pos Etagennummer
	 * @param dir Richtung: 0 = runter, 1 = hoch)
	 */
	public void newCall(int pos, CallDirection dir, boolean prio)
	{
		this.floors.setCall(pos, dir, prio);
	}
	/*public boolean move()
	{
		return this.elevator.move();
	}
	*/
	public void newRandPassenger()
	{
		this.passenger.add(new Passenger((int)(Math.random() * this.fcount), (int)(Math.random() * this.fcount), this.elevator, this.floors));
	}
	public void delPassenger(int pos, int dest)
	{
		//this.passenger.remove(2);
	}
	public void newPassenger(int pos, int dest, boolean prio)
	{
		this.passenger.add(new Passenger(pos, dest, this.elevator, this.floors, prio));
	}
	public void nextStep()
	{
		for(Passenger p: this.passenger)
			p.doAction();
		this.elevator.doAction();
		this.step++;
	}
	/**
	 * nur für das debuggen
	 */
	public void printStatus()
	{
		System.out.println("===== " + this.step + " =====");
		CallState    call;
		String callStr;
		String elstr;
		String plstr   = ""; //passanger list string
		String inplstr = ""; //inside passanger list string
		String elvd    = ""; //elevator door
		String elvol   = ""; //elevator overload
		String wstr    = "";

		for (int w: this.elevator.getWishes())
			wstr += w + " ";
		for(Passenger pas: this.passenger)
			inplstr += pas.getPosition() == Simulation.POS_INSIDE ? "(" + pas.getDestination() + ")" : "";  
		for (int fnr=this.fcount-1; fnr>=0; fnr--){	
			plstr = "";
			for(Passenger pas: this.passenger){
				String ps = pas.getPrio() ? "*" : "";
				plstr += pas.getPosition() == fnr ? "(" + pas.getDestination() + ps + ")" : "";
			}

			elvd  = this.elevator.isOpen()   ? " " : "#";
			elvol = this.elevator.isOverload() ? "!" : " ";
			elstr = this.elevator.getPosition() == fnr ? elvol + "[" + elvd + "]" : "    ";
			call  = this.floors.getCallState(fnr);
			String prio = this.floors.isCallPrio(fnr, CallDirection.UP) ? "↑" : this.floors.isCallPrio(fnr, CallDirection.DOWN) ? "↓" : "";
			switch (call){
				case NONE: callStr = "  ";
						break;
				case UP:   callStr = "↑ ";
						break;
				case DOWN: callStr = " ↓";
						break;
				case BOTH: callStr = "↑↓";
						break;
				default: callStr = "xx";
			}
			System.out.println( fnr + ": " + elstr + " " + callStr + " " + plstr + " " + prio);
		}
		System.out.println("Inside: " + inplstr);
		System.out.println("Wishes: " + wstr);
	}
	public void printStatusList()
	{
		System.out.println("===== " + this.step + " =====" );
		System.out.println("Pos	Des");
		for(Passenger pas: this.passenger)
			System.out.println(pas.getPosition() + "	" + pas.getDestination());

		System.out.println("Floor	CallState");
		for (int fnr=this.fcount-1; fnr>=0; fnr--)
			System.out.println(fnr + "	" + this.floors.getCallState(fnr));
		System.out.println("Pos	Dir	Open	Load	");
		System.out.println(this.elevator.getPosition() + "	" + this.elevator.getDirection() + "	" + this.elevator.isOpen() + "	" + this.elevator.getLoad());
		System.out.println("Wishes");
		for (int w: this.elevator.getWishes())
			System.out.print(w + " ");
	}
}
