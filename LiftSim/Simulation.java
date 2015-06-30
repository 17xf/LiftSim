package LiftSim;

public class Simulation
{
	private Lift lift;
	private int  fcount;

	public Simulation(int fcount)
	{
		  this.lift = new Lift(fcount);
		  this.fcount = fcount;
	}

	/**
	 * @param fnr Etagennummer
	 * @param dir Richtung: 0 = runter, 1 = hoch)
	 */
	public void newCall(int fnr, int dir )
	{
		this.lift.floors[fnr].setCall(dir);
	}
	public boolean move(int dir)
	{
		return this.lift.move(dir);
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
			elev = this.lift.getPosition() == fnr ? "→ " : "  ";
			call = this.lift.floors[fnr].getCall();
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
