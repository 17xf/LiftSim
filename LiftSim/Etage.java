package LiftSim;

public class Etage
{
   /** ruf[0] hoch
    *  ruf[1] runter
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
	public boolean setzeRuf(int richtung)
	{
		this.ruf[richtung] = true;
		return true;
	}
	public boolean entfRuf(int richtung)
	{
		this.ruf[richtung] = false;
		return true;
	}
	/** up  down  return
	 * 	0   0     0
	 * 	0   1     1
	 * 	1   0     2
	 * 	1   1     3
	 */
	public int getStatus()
	{
		int status = 0;
		status += this.ruf[0] ? 2 : 0;
		status += this.ruf[1] ? 1 : 0;
		return status;
	}
	public String toString()
	{
		return this.beschr;
	}
}

