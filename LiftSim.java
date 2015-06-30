import LiftSim.*;
public class LiftSim
{
	public static void main(String[] args)
	{
		Simulation sim = new Simulation(4);
		Etage e = new Etage(1, 2, "Test");
		System.out.println(e);
	}
}
