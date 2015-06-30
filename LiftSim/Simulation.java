package LiftSim;

public class Simulation
{
	private Lift lift;
	public Simulation(int anzEtagen)
	{
		  this.lift = new Lift(anzEtagen);
	}
	public void printStatus()
	{
		int[] etagenStatus = this.lift.getEtagenStatus();
	}
}
