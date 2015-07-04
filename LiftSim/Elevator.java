/**
 *
 */
package LiftSim;
import java.util.HashSet;

public class Elevator
{
	private int     position;
	private int     direction;
	private int     fcount;
	private int     load;
	private boolean open;

	private HashSet<Integer> wishes;

	/**
	 * Konstruktor
	 * @param fcount Anzahl der Etagen
	 */
	public Elevator( int fcount)
	{
		this.fcount    = fcount;
		this.position  = 0;
		this.direction = 0;
		this.open      = false;
		this.load      = 0;

		this.wishes    = new HashSet<Integer>();

		if (this.fcount<2)
			return;
	}
	/**
	 * Überladener Konstruktor. Zusätzlicher Parameter für die Startposition.
	 * @param fcount Anzahl der Etagen
	 * @param spos   Startposition
	 */
	public Elevator( int fcount, int spos)
	{
		this(fcount);
		this.position = spos;
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
	public int passengerIn()
	{
		this.load += 1;
		return Simulation.POS_INSIDE;
	}
	public int passengerOut()
	{
		this.load -= 1;
		return this.position;
	}
	public void doAction(Floor floor)
	{
		if (this.open){
			this.open = false;
			return;
		}
		int fdir = this.direction == DIR_UP ? Floor.CALL_UP : this.direction == DIR_DOWN ? Floor.CALL_DOWN : Floor.CALL_BOTH;
		if (floor.getCall(fdir)){
			this.open = true;
			floor.delCall(fdir);
		}
		if (this.wishes.contains(this.position)){
			this.open = true;
			this.wishes.remove(this.position);
		}
		//calsNewDirection();
		this.direction = DIR_UP;
		this.move(this.direction);
	}
	private int calcDirection()
	{
		/**
		 * if call exist in current direction return
		 * if wishes exist in current direction return
		 * if no calls and no wishes direction = null
		 * if no calls and no wishes in direction but calls and wishes in reverse direction direction = reverse
		 */
		return 1;
	}
	public int getDirection()
	{
		return this.direction;
	}
	public void setWish(int wish)
	{
		if (this.wishes.contains(wish))
			return;
		this.wishes.add(wish);
	}
	public boolean isOpen()
	{
		return this.open;
	}
	public int getLoad()
	{
		return this.load;
	}
	public boolean overload()
	{
		return this.load > 10 ? true : false;
	}
	public int getPosition()
	{
		return this.position;
	}
	public HashSet<Integer> getWishes()
	{
		return this.wishes;
	}
}
