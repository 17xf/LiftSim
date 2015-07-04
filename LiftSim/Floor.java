package LiftSim;

public class Floor
{
   /**
	* call[0] runter
    * call[1] hoch
    */
	public static final int DIR_DOWN = 0;
	public static final int DIR_UP   = 1;
	private boolean[]       call;
	private int             position;

	public static final int REL_TOP      =  1;
	public static final int REL_MID      =  0;
	public static final int REL_BOTTOM   = -1;
	/**
	 * Wo befindet sich die Etage? Ganz Oben? Unten? Dazwischen? 
	 * Wäre nicht nötig wenn man eine Liste verwenden würden. 
	 *  1 = ganz oben
	 *  0 = mitte
	 * -1 ganz unten
	 */
	private int ext;

	/**
	 * Konstruktor
	 * @param pos         Position der Etage
	 * @param ext         Relation der Etage zur anderen
	 * @param description Lesbare Etagen Beschreibung
	 */
	public Floor(int pos, int ext)
	{
		this.call  = new boolean[2];

		this.call[DIR_DOWN]        = false;
		this.call[DIR_UP]          = false;

		this.ext           = ext;
		this.position      = pos;
	}

	/**
	 * Setzt den Ruf für die gewünschte Richtung auf dieser Etage.
	 * @param direction Richtung die "gedrückt" werden soll. 0 = Runter; 1 = Hoch;
	 * @todo Konstanten für Hoch und Runter verwenden
	 */
	public void setCall(int direction)
	{
		this.call[direction] = true;
	}

	/**
	 * löscht den "gedrückt" status der gewünschten Richtung. 
	 * @param direction Richtung die "gelöscht" werden soll. 0 = Runter; 1 = Hoch;
	 */
	public void delCall(int direction)
	{
		if (direction == CALL_BOTH){
			this.call[DIR_DOWN] = false;
			this.call[DIR_UP]   = false;
			return;
		}
		this.call[direction] = false;
	}

	public static final int CALL_NONE = 0;
	public static final int CALL_UP   = 1;
	public static final int CALL_DOWN = 2;
	public static final int CALL_BOTH = 3;
	/**
	 * Gibt den aktuellen Rufstatus zurück.
	 *
	 * down  up  return
	 * 0     0   = 0
	 * 0     1   = 1
	 * 1     0   = 2
	 * 1     1   = 3
	 *
	 * 	@return 0 = no call; 1 = up; 2 = down; 3 = both;
	 */
	public int getCall()
	{
		int call = CALL_NONE;
		call += this.call[DIR_DOWN] ? CALL_DOWN : CALL_NONE;
		call += this.call[DIR_UP]   ? CALL_UP   : CALL_NONE;
		return call;
	}
	/**
	 * prüft ob eine bestimmte richtung gedrückt wurde
	 * @param dir Richtung = {DIR_UP | DIR_DOWN}
	 */
	public boolean getCall(int dir)
	{
		if (dir == CALL_BOTH)
			return this.call[DIR_DOWN] || this.call[DIR_UP];
		return this.call[dir];
	}
}

