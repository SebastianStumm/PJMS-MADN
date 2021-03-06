package classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Die Klasse fuer die KI
 * @author 
 *
 */

public abstract class KI implements Serializable{

	/**
	 * Attribute der Klasse KI
	 */
	private static final long serialVersionUID = 1L;
	protected Spieler spieler;
	protected iBedienerBean spiel;
	private boolean hatUeberlauf;
	private ArrayList<Spielfigur> figurenUeberlauf;
	
	/**
	 * Konstruktor, uebergibt Spieler und Spiel
	 */
	public KI(Spieler spieler, SpielBean spiel){
		if (spieler == null) throw new RuntimeException("Spieler muss vorhanden sein!");	
		this.spieler = spieler;

		if(spiel==null) throw new RuntimeException ("Spiel sollte vorhanden sein");
		this.spiel=spiel;

		hatUeberlauf=false;
	} 
	
	/**
	 * Methode bewegt die Figur zum Endfeld
	 * */
	protected boolean geheAufEndfeld(){
		int neueId;
		for(int i=0; i<4; i++)
		{
			if(spieler.getFigur(i).getPosition().getTyp() == FeldTyp.Normalfeld){
			neueId = spieler.getFigur(i).getPosition().getId() + spiel.getBewegungsWert();
			if (spieler.getFigur(i).getPosition().getId() != spieler.getFigur(i).getFreiPosition()) {
				neueId = spiel.ueberlauf(neueId, i);
				if(spiel.kannEndfeldErreichen(neueId) && spiel.userIstDumm(neueId, i) == false){
					if(spiel.zugGueltigEndfeld(neueId)){
						if(spiel.endfeld(neueId) == neueId){
							
						}else{
							spiel.bewege(i);
							return true;
						}
					}
				}
			}
			}
		} 
		return false;
	}
	
	/**
	 * Methode, die die Spielfiguren raus bewegt
	 */
	protected boolean betreteSpielfeld(){
		
		int neueId;
		
		if(spiel.getBewegungsWert()==6)
		{
			for(int i=0; i<4; i++){
				if(spieler.getFigur(i).getPosition().getTyp() == FeldTyp.Startfeld){
					neueId = spieler.getFigur(i).getFreiPosition();
					if(spiel.userIstDumm(neueId, i)){
						
					}else{
						spiel.bewege(i);
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 *Die Methode schl�gt die gegnerische Figur
	 */
	protected boolean schlageGegner(){

		int neueId;
		
		for(int i=0; i<4; i++){
			if(spieler.getFigur(i).getPosition().getTyp() == FeldTyp.Startfeld){
				neueId = spieler.getFigur(i).getFreiPosition();
				if(!spiel.userIstDumm(neueId, i)){
					if(spiel.figurAufFeld(neueId)){
						spiel.bewege(i);
						return true;
					}
					
				}
			}
			
			if(spieler.getFigur(i).getPosition().getTyp() != FeldTyp.Startfeld && spiel.figurAufFeld(spiel.ueberlauf(spiel.getBewegungsWert()+spieler.getFigur(i).getPosition().getId(), i))){
					neueId = spieler.getFigur(i).getPosition().getId()+spiel.getBewegungsWert();
					if(neueId < 72 && spiel.userIstDumm(neueId, i)){
						
					}else{
						spiel.bewege(i); 
						return true;
					}
				
			}
		}
		return false;
	}

	/**
	 * Die Methode die die Spielfiguren zum Laufen bringt
	 */
	protected boolean laufEinfach(){ 

		int groessteId=spieler.getFigur(0).getPosition().getId();
		int figurId=0; // Figur mit der gr��ten ID
		
		
		for(int i=1; i<4; i++)
		{ 
			int neueId;
			neueId = spieler.getFigur(i).getPosition().getId() + spiel.getBewegungsWert();
			if(spieler.getFigur(i).getPosition().getTyp() != FeldTyp.Startfeld && groessteId<spieler.getFigur(i).getPosition().getId()){
				groessteId=spieler.getFigur(i).getPosition().getId();
				figurId=i;
			}
			neueId = spiel.ueberlauf(neueId, i);
			if (spieler.getFigur(i).getPosition().getTyp() == FeldTyp.Endfeld) {
				if (!spiel.zugGueltigAufEndfeld(neueId, i)) {
					break;
				}
			}else{
				neueId = spieler.getFigur(i).getPosition().getId() + spiel.getBewegungsWert();
				if(spieler.getFigur(i).getPosition().getId() == spieler.getFigur(i).getFreiPosition()){
					if(!spiel.userIstDumm(neueId, i)){
						figurId = i;
						break;
					}else{
						for(int j = 0; j < 4; j++){
							if(spieler.getFigur(j).getPosition().getId() == neueId){
								if(!spiel.userIstDumm(neueId+spiel.getBewegungsWert(), j)){
									figurId = j;
									break;
								}
							}
						}
					}
				}
			}
		}
		
		spiel.bewege(figurId);
		return true;
	}

	/**
	 * F�hrt den eigentlichen Spielzeug der KI aus
	 */
	public abstract String kalkuliereSpielzug();

}
