package LiftSim;

public class Passenger
{
	private int position;
	private int destination;

	/**
	 * Konstruktor
	 * @param spos Startposition
	 * @param dest Ziel
	 */
	public Passenger(int spos, int dest)
	{
		this.position    = spos;
		this.destination = dest;
	}
	public void doAction(Lift elev)
	{
		if (this.position == this.destination)
			return; //done
		// not at destination
		if (this.position != Simulation.POS_INSIDE )
			return;
	}
}

