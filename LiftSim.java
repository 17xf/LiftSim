/**
 * Das eigentliche Programm. Die Mainfunktion soll eine Simulationsinstanz
 * erstellen und die öffentlichen Funktionen der Simulation Klasse an die
 * Menüs und Buttons binden.
 * Geplant ist noch eine GUI Klasse sie dies übernimmt.
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

		/**
		 * User input
		 */
		Console stdin = System.console();
		String  cmd   = "";

		/**
		 * Simulations Instanz erstellen und Etagenzahl als Parameter übergeben
		 */
		Simulation sim = new Simulation(7);

		/**
		 * Drei Passagiere hinzufügen. Zwei mit definierter Start- und Zieletage. Einen Random.
		 */
		sim.newPassenger(0,4, false);
		sim.newPassenger(3,6, true); // Passagier hat Priorität
		sim.newRandPassenger();

		/**
		 * Erste Statusausgabe
		 */
		sim.printStatus();
		while (true){
			cmd = stdin.readLine();
			/*
			if ((int)(Math.random() * 6)>3)
				sim.newRandPassenger();
			*/
			if (cmd != "\n")
				System.out.println("Command: |" + cmd + "|");
			if (cmd == "n"){
				sim.newRandPassenger();
				System.out.println("Random passenger added");
			}


//			sim.printStatusList();
			sim.nextStep();
			sim.printStatus();
			/**
			 * Alternativ erfolgen hier verschiedenen Aufrufe von Simulations Methoden
			 * um Informationen über die Verschiedene Statuse der Subjekte zu erhalten.
			 * Bsp: sim.getElvevatorDoorState();
			 */
		}
	}
}
