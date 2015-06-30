/*@file: LiftSim/Lift.java */
package LiftSim;

public class Lift
{
	public Etage[]     floors;
	private int        position;
	private int        direction;
	private int        fcount;
	private Wuensche[] wuensche;

	public Lift( int fcount)
	{
		this.fcount = fcount;

		if (this.fcount<2)
			return;

		this.position = 0;
		this.floors = new Etage[fcount];

		this.floors[0] = new Etage(0,-1, "Unten");

		for(int i=1; i<fcount-1; i++)
		{
			this.floors[i] = new Etage(i, 0, "Etage");
		}
		this.floors[fcount-1] = new Etage(fcount-1, 1, "Oben");
	}
	public Lift( int fcount, int spos)
	{
		this(fcount);
		this.position = spos;
	}
}
