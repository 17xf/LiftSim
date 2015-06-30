/*@file: LiftSim.java */

import LiftSim.*;
public class LiftSim
{
	public static void main(String[] args)
	{
		Simulation sim = new Simulation(5);
		sim.printStatus();
		sim.newCall(4, 1);
		sim.newCall(0, 0);
		sim.newCall(2, 0);
		sim.newCall(2, 1);
		//sim.newWish(3);
		System.out.println("===================");
		sim.printStatus();
		//sim.run(1);
		//sim.printStatus();
		//sim.run(2);
	}
}
