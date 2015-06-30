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
	public String toString()
{
	return this.beschr;
	}
}

