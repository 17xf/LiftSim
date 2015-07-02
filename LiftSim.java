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

		Simulation sim = new Simulation(5);
		Console c = System.console();
		c.readLine();
		System.out.println("Start");

		while(true)
		{
			String test = c.readLine();
			System.out.println("===================");
			sim.printStatus();

			/*sim.newCall(4, 1);
			sim.newCall(0, 0);
			sim.newCall(2, 0);
			sim.newCall(2, 1);
			*/
			sim.newPassenger(2,4);
			//sim.newPassenger(4,2);
			sim.nextStep();
			//sim.move(+1);
			//sim.run(1);
			//sim.printStatus();
			//sim.run(2);
		}
	}
}
