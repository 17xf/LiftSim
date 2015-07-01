package LiftSim;

public class Etage
{
   /**
	* call[0] runter
    * call[1] hoch
    */
	public static final int DIR_DOWN = 0;
	public static final int DIR_UP   = 1;
	private boolean[] call;

	private int       position;

	/**
	 * Wo befindet sich die Etage? Ganz Oben? Unten? Dazwischen? 
	 * Wäre nicht nötig wenn man eine Liste verwenden würden. 
	 *  1 = ganz oben
	 *  0 = mitte
	 * -1 ganz unten
	 */
	private int ext;

	/**
	 * Beschreibung der Etage z.b.: Ergdeschoß, Keller...
	 */
	private String description;            

	/**
	 * Konstruktor
	 * @param pos         Position der Etage
	 * @param ext         Relation der Etage zur anderen
	 * @param description Lesbare Etagen Beschreibung
	 */
	public Etage(int pos, int ext, String description)
	{
		this.call  = new boolean[2];

		this.call[DIR_DOWN]        = false;
		this.call[DIR_UP]          = false;

		this.ext           = ext;
		this.position      = pos;
		this.description   = description;
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
	 * Wird aufgerufen wenn eine Instanz dieser Klasse wie ein String behandelt wird.
	 * Bsp.: System.out.println(etage);
	 */
	public String toString()
	{
		return this.description;
	}
}

