/*@file: LiftSim/Lift.java */
package LiftSim;

public class Lift
{
	private Etage[]    floors;
	private int        anzEtagen;
	private Wuensche[] wuensche;

	public Lift( int anzEtagen)
	{
		if (anzEtagen<2)
			return;
		this.floors = new Etage[anzEtagen];
		this.anzEtagen = anzEtagen;

		this.floors[0] = new Etage(0,-1, "Unten");

		for(int i=1; i<anzEtagen-1; i++)
		{
			this.floors[i] = new Etage(i, 0, "Etage");
		}
		this.floors[anzEtagen-1] = new Etage(anzEtagen-1, 1, "Oben");
	}
	public int[] getEtagenStatus()
	{
		int[] s = new int[this.anzEtagen];
		for(int i = 0; i<this.anzEtagen; i++){
			s[i] = this.floors[i].getStatus();
		}
		return s;
	}
	/**
	 * fnr floor Number
	 * dir = {0|1}
	 * 0 = hoch
	 * 1 = runter
	 */
	public void newCall(int fnr, int dir)
	{
		floors[fnr].setCall(dir);
	}
}
