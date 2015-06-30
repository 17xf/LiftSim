package LiftSim;

public class Simulation
{
	private Lift lift;
	private int  anzEtagen;
	public Simulation(int anzEtagen)
	{
		  this.lift = new Lift(anzEtagen);
		  this.anzEtagen = anzEtagen;
	}
	public void printStatus()
	{
		int[] etagenStatus;
		//= new int[this.anzEtagen];
		etagenStatus = this.lift.getEtagenStatus();
		for (int i=this.anzEtagen-1; i>=0; i--){
			System.out.println("Etage "+i+" Status: "+etagenStatus[i]);
		}
	}
}
