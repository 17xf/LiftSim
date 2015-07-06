/**
 *
 */
package LiftSim;
import java.util.HashSet;

public class Elevator
{
	private Movement direction;
	private int      fcount;
	private Floors   floors;
	private int      load;
	private boolean  open;
	private int      position;

	private HashSet<Integer> wishes;

	/**
	 * Konstruktor
	 * @param fcount Anzahl der Etagen
	 */
	public Elevator(int fcount, Floors floors)
	{
		this.direction = Movement.STOP;
		this.floors    = floors;
		this.fcount    = fcount;
		this.load      = 0;
		this.open      = false;
		this.position  = 0;

		this.wishes    = new HashSet<Integer>();

		if (this.fcount<2)
			return;
	}
	/**
	 * Überladener Konstruktor. Zusätzlicher Parameter für die Startposition.
	 * @param fcount Anzahl der Etagen
	 * @param spos   Startposition
	 */
	public Elevator(int fcount, Floors floors, int spos)
	{
		this(fcount, floors);
		this.position = spos;
	}

	public static final int DIR_DOWN = -1;
	public static final int DIR_UP   =  1;
	public static final int DIR_STOP =  0;

	/**
	 * Ändert die Position des Liftes. 
	 * @param dir Richtung -N Runter; +N Hoch
	 */
	public boolean move(Movement dir)
	{
		if (this.open){
			System.out.println("Elevator: Move: Not moved because door open");
			return false;
		}
		if (this.overload()){
			System.out.println("Elevator: Move: Not moved because overload");
			return false;
		}
		int tmpPos = this.position + movToRelPos(dir);
		if (tmpPos < 0 || tmpPos > fcount-1){
			System.out.println("Elevator: Move: Not moved because else out of border");
			return false;
		}
		this.position += movToRelPos(dir);
		//this.direction = dir > 0 ? DIR_UP : dir == 0 ? DIR_STOP : DIR_DOWN;
		System.out.println("Elevator: Move: Moved in dir. New position: " + this.position);
		return true;
	}
	private int movToRelPos(Movement dir)
	{
		return dir == Movement.DOWN ? -1 : dir == Movement.UP ? +1 : 0;
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
	public void doAction()
	{
		if (this.open){
			this.open = false;
			System.out.println("Elevator: Door close");
			return;
		}
		CallDirection fdir = this.direction == Movement.UP ? CallDirection.UP : this.direction == Movement.DOWN ? CallDirection.DOWN : CallDirection.BOTH;

		if (this.floors.isCallSet(this.position, fdir)){
			this.open = true;
			System.out.println("Elevator: open door because call in dir here");
			this.floors.delCall(this.position, fdir);
			System.out.println("Elevator: Delete Call in dir here");
		}
		if (this.wishes.contains(this.position)){
			this.open = true;
			System.out.println("Elevator: open door because wishes");
			this.wishes.remove(this.position);
			System.out.println("Elevator: Delete Wish in dir here");
		}
		calcNewDirection();
		System.out.println("Elevator: New Direction " + this.direction);
		//this.direction = DIR_UP;
		System.out.println("Elevator: Move");
		this.move(this.direction);
	}
	/**
	 * if call exist in current direction return
	 * if wishes exist in current direction return
	 * if no calls and no wishes direction = null
	 * if no calls and no wishes in direction but calls and wishes in reverse direction direction = reverse
	 */
	private Movement calcNewDirection()
	{
	/*	if (this.direction == DIR_STOP)
			this.direction = DIR_UP;
			*/
		if (isWishInDir(this.direction))
			return this.direction;
		if (this.floors.isCallInDir(this.position, this.direction))
		if (isWishInDir(this.direction) || this.floors.isCallInDir(this.position, this.direction)){
			System.out.println("Direction: " + this.direction + " because wish or call in dir");
			return this.direction != 0 ? this.direction : 1;
		}
		if (isWishInDir(-this.direction) || this.floors.isCallInDir(this.position, -this.direction)){
			System.out.println("Direction: " + -this.direction + " because no wish or call in dir but in reverse dir");
			return this.direction != 0? -this.direction : 1;
		}
		System.out.println("Direction: " + DIR_STOP + " because no wish or call at all");
	return DIR_STOP;
	}
	private boolean isWishInDir(Movement dir)
	{
		for (int wish: this.wishes){
		//if (dir == 0) return true;
			if (dir == Movement.DOWN && wish < this.position) return true;
			if (dir == Movement.UP   && wish > this.position) return true;
		}
		System.out.println("No Wishes");
		return false;
	}
	public Movement getDirection()
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
