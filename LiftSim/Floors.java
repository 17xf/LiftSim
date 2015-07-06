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
	public boolean isCallInDir(int pos, Movement dir, CallDirection callDir)
	{
		if (dir == Movement.STOP) throw new IllegalArgumentException( "für dir sind nur Movement.{UP|DOWN} erlaubt" );
		while (pos < this.floors.size() || pos >= 0){
			Floor f = this.floors.get(pos);
			if (f.isCallSet(callDir)) return true;
			pos += dir == Movement.DOWN ? -1 : +1;
		}
		return false;
	}
	public boolean isCallAbove(int pos, CallDirection dir)
	{

		while (pos < this.floors.size()){
			Floor f = this.floors.get(pos);
			if (f.isCallSet(dir)) return true;
			pos++;
		}
		return false;
	}
	public boolean isCallBeneath(int pos, CallDirection dir)
	{
		while (pos >= 0){
			Floor f = this.floors.get(pos);
			if (f.isCallSet(dir)) return true;
			pos--;
		}
		return false;
	}
	/*
	public boolean isCallInDir(int ffpos, CallDirection dir)
	{
		dir = dir != 0 ? dir : 1;
		while(ffpos >= 0 && ffpos < this.floors.size())
		{
			Floor f = this.floors.get(ffpos);
			//if (f.getCall(dir > 0 ? Floor.DIR_UP : Floor.DIR_DOWN))
			if (f.isCallSet(dir))
				return true;
			ffpos += dir;
		}
		return false;
	}
	*/

	public void setCall(int fpos, CallDirection dir)
	{
		Floor f = this.floors.get(fpos);

		f.setCall(dir);
	}
	public boolean isCallSet(int fpos, CallDirection dir)
	{
		Floor f = this.floors.get(fpos);
		return f.isCallSet(dir);
	}
	public CallState getCallState(int fpos)
	{
		Floor f = this.floors.get(fpos);
		return f.getCallState();
	}
	/*
	public Floor getFloor(int fpos)
	{
		return this.floors.get(fpos);
	}
	*/
	public void delCall(int fpos, CallDirection dir)
	{
		Floor f = this.floors.get(fpos);
		f.delCall(dir);
	}
}

