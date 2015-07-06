package LiftSim;

public class Passenger
{
	private int      destination;
	private Elevator elevator;
	private Floors   floors;
	private int      position;
	private boolean  prio;

	/**
	 * Konstruktor
	 * @param spos     Startposition
	 * @param dest     Ziel
	 * @param Elevator Referenz zur Fahrstuhlklasse
	 * @param prio     Vorrang?
	 */
	public Passenger(int spos, int dest, Elevator elevator, Floors floors, boolean prio)
	{
		this.position    = spos;
		this.destination = dest;
		this.elevator    = elevator;
		this.floors      = floors;
		this.prio        = prio;
	}
	public Passenger(int spos, int dest, Elevator elevator, Floors floors)
	{
		this(spos, dest, elevator, floors, false);
	}
	public void doAction()
	{
		if (this.position == Simulation.POS_INSIDE){
			if (this.elevator.getPosition() == this.destination && this.elevator.isOpen()){
				this.position = this.elevator.passengerOut();
				return;
			}
			//redundant? 
			this.elevator.setWish(this.destination);
			return;
		}
		if (this.position == this.destination) return;
		if (canEnter() && (this.elevator.getDirection() == (this.position > this.destination ? Movement.DOWN : Movement.UP) || this.elevator.getDirection() == Movement.STOP)){
			this.position = this.elevator.passengerIn();
			if (this.position == Simulation.POS_INSIDE)
				this.elevator.setWish(this.destination);
		}
		if (this.position != Simulation.POS_INSIDE)
			this.floors.setCall(this.position, this.position > this.destination ? CallDirection.DOWN : CallDirection.UP, this.prio);
	}
	public int getDestination()
	{
		return this.destination;
	}
	public boolean getPrio()
	{
		return this.prio;
	}
	public int getPosition()
	{
		return this.position;
	}

	private boolean canEnter()
	{
		//ask if there is capacity this.elevator.isSpace();
		if (this.elevator.isOverload()) return false;
		return this.elevator.getPosition() == this.position && this.elevator.isOpen();
	}
}

