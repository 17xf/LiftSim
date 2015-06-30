package LiftSim;

public class Lift
{
	private Etage[]    etage;
	private Wuensche[] wuensche;
	public Lift( int anzEtagen)
	{
		if (anzEtagen<2)
			return;
		this.etage[0] = new Etage(0,-1, "Unten");

		for(int i=1; i<anzEtagen-1; i++)
		{
			this.etage[i] = new Etage(i, 0, "Etage");
		}
		this.etage[anzEtagen-1] = new Etage(anzEtagen-1, 1, "Oben");
	}
}
