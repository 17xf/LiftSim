package LiftSim;

public class Etage
{
   /** ruf[0] runter
    *  ruf[1] hoch
    */
	private boolean[] ruf;

	/* 1 = ganz oben, 0 = mitte, -1 ganz unten
	 */
	private int ext;

	private int pos;

	/** Beschreibung der Etage z.b.: Ergdeschoß, Keller...
	 */
	private String beschr;            
	public Etage(int pos, int ext, String beschr)
	{
		this.ruf  = new boolean[2];
		this.ruf[0] = false;
		this.ruf[1] = false;
		this.ext    = ext;
		this.pos    = pos;
		this.beschr = beschr;
	}
	public void setCall(int direction)
	{
		this.ruf[direction] = true;
	}
	public void entfRuf(int richtung)
	{
		this.ruf[richtung] = false;
	}
	/** down  up  return
	 * 	0     0   = 0
	 * 	0     1   = 1
	 * 	1     0   = 2
	 * 	1     1   = 3
	 *
	 * 	@return 0 = no call; 1 = up; 2 = down; 3 = both;
	 */
	public int getCall()
	{
		int call = 0;
		call += this.ruf[0] ? 2 : 0; // runter
		call += this.ruf[1] ? 1 : 0; // hoch
		return call;
	}
	public String toString()
	{
		return this.beschr;
	}
}

