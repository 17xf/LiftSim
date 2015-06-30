package LiftSim;

public class Lift
{
	private Etage[]    etage;
	private Wuensche[] wuensche;
	public Lift( int anzEtagen)
	{
		if (anzEtagen<2)
			return;
		for(int i=1; i<anzEtagen; i++)
		{
			this.etage[i] = new Etage(i, 0, "Twst");
		}
	}

}
