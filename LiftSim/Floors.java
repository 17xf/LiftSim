package LiftSim;
import java.util.ArrayList;

public class Floors
{
	private ArrayList<Floor> floors;

	public Floors(int amount)
	{
		this.floors = new ArrayList<Floor>();

		this.floors.add(new Floor(0, Floor.REL_BOTTOM));
		for (int fnr=1; fnr<=amount-2; fnr++)
			this.floors.add(new Floor(fnr, Floor.REL_MID));
		this.floors.add(new Floor(amount-1, Floor.REL_TOP));
	}
	/**
	 * @param dir -1 down; +1 up;
	 */
	public boolean isCallInDir (int ffpos, int dir)
	{
		while(ffpos >= 0 && ffpos < this.floors.size())
		{
			Floor f = this.floors.get(ffpos);
			if (f.getCall(dir > 0 ? Floor.DIR_UP : Floor.DIR_DOWN))
				return true;
			ffpos += dir;
		}
		return false;
	}

	public void setCall(int fpos, int dir)
	{
		Floor f = this.floors.get(fpos);

		f.setCall(dir);
	}
	public int getCall(int fpos)
	{
		Floor f = this.floors.get(fpos);

		return f.getCall();
	}
	public Floor getFloor(int fpos)
	{
		return this.floors.get(fpos);
	}

}

