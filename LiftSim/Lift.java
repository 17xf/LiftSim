package LiftSim;

public class Lift
{
	private Etage[]    etage;
	private int        anzEtagen;
	private Wuensche[] wuensche;

	public Lift( int anzEtagen)
	{
		if (anzEtagen<2)
			return;
		this.etage = new Etage[anzEtagen];
		this.anzEtagen = anzEtagen;

		this.etage[0] = new Etage(0,-1, "Unten");

		for(int i=1; i<anzEtagen-1; i++)
		{
			this.etage[i] = new Etage(i, 0, "Etage");
		}
		this.etage[anzEtagen-1] = new Etage(anzEtagen-1, 1, "Oben");
	}
	public int[] getEtagenStatus()
	{
		int[] s = new int[this.anzEtagen];
		for(int i = 0; i<this.anzEtagen; i++){
			s[i] = this.etage[i].getStatus();
		}
		return s;
	}
}
