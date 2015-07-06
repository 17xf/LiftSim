package LiftSim;

public class Floor
{
   /**
	* buttons[0] runter
    * buttons[1] hoch
    */
	private boolean[]       buttons;

	public static final int INDEX_DOWN = 0;
	public static final int INDEX_UP   = 1;

	public static final int REL_TOP      =  1;
	public static final int REL_MID      =  0;
	public static final int REL_BOTTOM   = -1;

	private int             position;
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
		this.buttons  = new boolean[2];

		this.buttons[INDEX_DOWN]        = false;
		this.buttons[INDEX_UP]          = false;

		this.ext           = ext;
		this.position      = pos;
	}

	/**
	 * Setzt den Ruf für die gewünschte Richtung auf dieser Etage.
	 * @param dir Richtung die "gedrückt" werden soll. CallDirection.{UP|DOWN}
	 */
	public void setCall(CallDirection dir)
	{
		this.buttons[callToIndex(dir)] = true;
	}

	/**
	 * löscht den "gedrückt" status der gewünschten Richtung. 
	 * @param direction Richtung die "gelöscht" werden soll. CallDirection.{UP|DOWN}
	 */
	public void delCall(CallDirection dir)
	{
		this.buttons[callToIndex(dir)] = false;
	}

	public static final int CALL_NONE = 0;
	public static final int CALL_UP   = 1;
	public static final int CALL_DOWN = 2;
	public static final int CALL_BOTH = 3;
	/**
	 * Gibt den aktuellen Rufstatus zurück.
	 * 	@return CallState.{UP|DOWN|BOTH|NONE}
	 */
	public CallState getCallState()
	{
		if (this.buttons[INDEX_DOWN] && this.buttons[INDEX_UP])
			return CallState.BOTH;
		if (this.buttons[INDEX_DOWN])
			return CallState.DOWN;
		if (this.buttons[INDEX_UP])
			return CallState.UP;
		return CallState.NONE;
	}
	/**
	 * prüft ob eine bestimmte richtung gedrückt wurde
	 * @param dir Richtung = {DIR_UP | DIR_DOWN}
	 */
	public boolean isCallSet(CallDirection dir)
	{
		return this.buttons[callToIndex(dir)];
	}
	private int callToIndex(CallDirection dir)
	{
		if (dir == CallDirection.DOWN)    return INDEX_DOWN;
		else if (dir == CallDirection.UP) return INDEX_UP;
		else throw new IllegalArgumentException( "Nur die Werte CallDirection.{UP|DOWN} erlaubt!" );
	}
}

