package LiftSim;

public class Passenger
{
	private int      destination;
	private Elevator elevator;
	private Floors   floors;
	private int      position;

	/**
	 * Konstruktor
	 * @param spos     Startposition
	 * @param dest     Ziel
	 * @param Elevator Referenz zur Fahrstuhlklasse
	 */
	public Passenger(int spos, int dest, Elevator elevator, Floors floors)
	{
		this.position    = spos;
		this.destination = dest;
		this.elevator    = elevator;
		this.floors      = floors;
	}
	public void doAction(Floor floor)
	{
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
		if (canEnter() && (this.elevator.getDirection() == (this.position > this.destination ? Elevator.DIR_DOWN : Elevator.DIR_UP) || this.elevator.getDirection() == Elevator.DIR_STOP)){
			this.position = this.elevator.passengerIn();
			if (this.position == Simulation.POS_INSIDE)
				this.elevator.setWish(this.destination);
		}
		if (this.position != Simulation.POS_INSIDE)
			this.floors.setCall(this.position, this.position > this.destination ? Floor.DIR_DOWN : Floor.DIR_UP);
	}
	public int getDestination()
	{
		return this.destination;
	}
	public int getPosition()
	{
		return this.position;
	}

	private boolean canEnter()
	{
		//ask if there is capacity this.elevator.isSpace();
		return this.elevator.getPosition() == this.position && this.elevator.isOpen();
	}
}

