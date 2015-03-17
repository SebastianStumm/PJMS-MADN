package classes;
/**
 * Die Klasse f�r die KI
 * @author Johannes M�ndle
 *
 */

class KI {

	private Spieler spieler;
	/**
	 * Konstruktor
	 */
	public KI(Spieler spieler){
		if (spieler == null) throw new RuntimeException("Spieler muss vorhanden sein!");	
		this.spieler = spieler;
	} 

}
