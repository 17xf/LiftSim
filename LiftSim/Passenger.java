package LiftSim;

public class Passenger
{
	private int position;
	private int destination;
	private Elevator elevator;

	/**
	 * Konstruktor
	 * @param spos Startposition
	 * @param dest Ziel
	 */
	public Passenger(int spos, int dest, Elevator elevator)
	{
		this.position    = spos;
		this.destination = dest;
		this.elevator    = elevator;
	}
	public void doAction(Floor floor)
	{
		if (this.position == this.destination)
			return;
		if (this.elevator.getPosition() == this.position && this.elevator.isOpen())
			this.position = this.elevator.passengerIn();
		if (this.position != Simulation.POS_INSIDE)
			floor.setCall(this.position > this.destination ? Floor.DIR_DOWN : Floor.DIR_UP);
	}
	public void doAction()
	{
		if (this.elevator.getPosition() == this.destination && this.elevator.isOpen())
			this.position = this.elevator.passengerOut();
		return;
	}
	public int getDestination()
	{
		return this.destination;
	}
	public int getPosition()
	{
		return this.position;
	}
}

