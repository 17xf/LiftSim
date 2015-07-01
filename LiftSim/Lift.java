/**
 *
 */
package LiftSim;

public class Lift
{
	private int        position;
	private int        direction;
	private int        fcount;
	private int        load;
	private boolean    open;

	/**
	 * Konstruktor
	 * @param fcount Anzahl der Etagen
	 */
	public Lift( int fcount)
	{
		this.fcount    = fcount;
		this.position  = 0;
		this.direction = 0;
		this.open      = false;
		this.load      = 0;

		if (this.fcount<2)
			return;
	}
	/**
	 * Überladener Konstruktor. Zusätzlicher Parameter für die Startposition.
	 * @param fcount Anzahl der Etagen
	 * @param spos   Startposition
	 */
	public Lift( int fcount, int spos)
	{
		this(fcount);
		this.position = spos;
	}

	public boolean overload()
	{
		return this.load > 10 ? true : false;
	}
	public int getPosition()
	{
		return this.position;
	}
	public static final int DIR_DOWN = -1;
	public static final int DIR_UP   =  1;
	public static final int DIR_STOP =  0;

	/**
	 * Ändert die Position des Liftes. 
	 * @param dir Richtung -N Runter; +N Hoch
	 */
	public boolean move(int dir)
	{
		if (this.open)
			return false;
		if (this.overload())
			return false;
		int tmpPos = this.position + dir;
		if (tmpPos < 0 || tmpPos > fcount-1)
			return false;
		this.position += dir;
		this.direction = dir > 0 ? DIR_UP : dir == 0 ? DIR_STOP : DIR_DOWN;
		return true;
	}
	public void doAction()
	{


	}
	public boolean isOpen()
	{
		return this.open;
	}
	public int goInside()
	{
		this.load += 1;
		return Simulation.POS_INSIDE;
	}
	public int goOutside()
	{
		this.load -= 1;
		return this.position;
	}
}
