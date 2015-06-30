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
	/**
	 * nur für das debuggen
	 */
	public void printStatus()
	{
		int call;
		String callStr;
		for (int fnr=this.fcount-1; fnr>=0; fnr--){	
			call = this.lift.floors[fnr].getCall();
			switch (call){
				case 0: callStr = "keine Rufe";
						break;
				case 1: callStr = "nach oben";
						break;
				case 2: callStr = "nach unten";
						break;
				case 3: callStr = "in beide Richtungen";
						break;
				default: callStr = "NULL";
			}
			System.out.println("Etage " + fnr + " Status: (" + call + ") " + callStr);
		}
	}
}
