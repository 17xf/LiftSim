/**
 * 
 */
package LiftSim;

public class Simulation
{
	private Lift    elevator;
	private Etage[] floors;
	private int  fcount;

	public Simulation(int fcount)
	{
		this.elevator = new Lift(fcount);

		this.fcount = fcount;

		/**
		 * Es müssen mindestens 2 Etagen existieren
		 */
		if (this.fcount<2)
			return;

		/**
		 * Allociert Pletz für die Etagen und ruft deren Konstruktoren
		 * mit richtigen Paramatern auf.
		 */
		this.floors = new Etage[fcount];
		this.floors[0] = new Etage(0,-1, "Unten");
		for (int fnr=1; fnr<this.fcount-1; fnr++)
			this.floors[fnr] = new Etage(fnr, 0, "Etage");
		this.floors[fcount-1] = new Etage(fcount-1, 1, "Oben");
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
	/**
	 * nur für das debuggen
	 */
	public void printStatus()
	{
		int call;
		String callStr;
		String elev;
		for (int fnr=this.fcount-1; fnr>=0; fnr--){	
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
			System.out.println( elev + " " + fnr + " " + callStr);
			//System.out.println("Etage " + fnr + " Status: (" + call + ") " + callStr);
		}
	}
}
