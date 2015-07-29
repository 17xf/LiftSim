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
	private int mWish;

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
	 * anhand von `this.direction`
	 */
	private boolean move()
	{
		Movement dir = this.direction;
		if (this.open){
			System.out.println("Elevator: Move: Not moved because door open");
			return false;
		}
		if (this.isOverload()){
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
		/**
		 * Falls dir Tür offen ist: schließen. Ende.
		 */
		if (this.open){
			this.open = false;
			System.out.println("Elevator: Door close");
			return;
		}
		CallDirection fdir = this.direction == Movement.UP ? CallDirection.UP : this.direction == Movement.DOWN ? CallDirection.DOWN : CallDirection.UP;

		if (this.floors.isCallSet(this.position, fdir)){
			this.open = true;
			System.out.println("Elevator: open door because call in dir here");
			this.floors.delCall(this.position, fdir);
			System.out.println("Elevator: Delete call in dir here");
		}
		if (this.wishes.contains(this.position)){
			this.open = true;
			System.out.println("Elevator: open door because wishes");
			this.wishes.remove(this.position);
			System.out.println("Elevator: Delete Wish in dir here");
		}
		calcNewDirection();
		System.out.println("Elevator: New Direction " + this.direction);
		move();
		System.out.println("Elevator: Move");
	}
	/**
	 * if call exist in current direction return
	 * if wishes exist in current direction return
	 * if no calls and no wishes direction = null
	 * if no calls and no wishes in direction but calls and wishes in reverse direction direction = reverse
	 */
	private void calcNewDirection()
	{
		this.direction = this.position == 0 ? Movement.UP : this.position == this.floors.getFloorCount()-1 ? Movement.DOWN : this.direction;
		/*
		this.direction         = this.direction == Movement.STOP ? Movement.UP        : this.direction;
		Movement rDir          = this.direction == Movement.UP   ? Movement.DOWN      : Movement.UP;
		CallDirection cCallDir = this.direction == Movement.UP   ? CallDirection.UP   : CallDirection.DOWN;
		CallDirection rCallDir = this.direction == Movement.UP   ? CallDirection.DOWN : CallDirection.UP;
		// wünsche in Fahrtrichtung -> Weiter in Fahrrichtung
		// Rufe in Fahrtrichtung die in Fahrtrichtung wollen -> Weiter in Fahrtrichtung
		if (this.floors.isCallInDir(this.position, this.direction, cCallDir) || isWishInDir(this.direction))
			return this.direction;
		// Rufe in Fahrtrichtung die in gegen Fahrtrichtung wollen
		if (this.floors.isCallInDir(this.position, this.direction, rCallDir))
			return this.direction;
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
	*/
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
	public boolean isOverload()
	{
		return this.load > 5 ? true : false;
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
