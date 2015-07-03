/**
 * Das eigentliche Programm. Die Mainfunktion soll eine Simulationsinstanz
 * erstellen und die öffentlichen Funktionen der Simulation Klasse an die
 * Menüs und Buttons binden.
 * Geplant ist eigentlich noch eine GUI Klasse sie dies ubernimmt.
 * Aussehen sollte es am ande so:
 *     GUI mygui = new GUI(new Simulation(5));
 *     mygui.simulation.start();
 */
import LiftSim.*;
import java.io.Console;
public class LiftSim
{
	public static void main(String[] args)
	{

		String test;
		Console c = System.console();

		Simulation sim = new Simulation(7);

		sim.newPassenger(2,4);
		sim.newPassenger(2,1);
		while(true)
		{
			test = c.readLine();

			sim.printStatusList();
			sim.nextStep();
		}
	}
}
