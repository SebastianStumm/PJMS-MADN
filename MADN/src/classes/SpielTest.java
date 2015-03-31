package classes;
/**
 * Die Testklasse SpielTest
 * @author Patrick Smuda
 * @version 1.0
 */
public class SpielTest {
	
	
	/**Erstellung vom Spielbrett, Spieler erzeugen
	*/
	public static void main(String[] args) {
		Spielbrett sb = new Spielbrett();
		Spiel s = new Spiel();
		iBediener p = s;
		
		p.spielerHinzufuegen("ROT", 0);
		p.spielerHinzufuegen("BLAU", 1);
		p.spielerHinzufuegen("GELB", 3);
		p.spielerHinzufuegen("GRUEN", 2);
		
		p.beginneSpiel();
		

		
		for (int i = 0; i < 100; i++) {
			p.wuerfeln();
			int x = (int)((Math.random()*4));
			System.out.println(p.getSpielerAmZug() + " ist auf dem Feld " + p.getPosition(x) + " und w�rfelt eine " + p.getBewegungsWert());
			p.bewege(x);	
			
		}
		
		
		
//		Funktioniert mit den neuen �nderungen nichtmehr		
//		for (int i = 0; i < 40; i++) {
//		p.wuerfeln();
//
//		switch (p.getSpielerAmZug()) {
//		case rot:
//			System.out.println("Alte Position von " + p.getSpielerAmZug().getFarbe() + ": " + s1.getFigur(1).getPosition().getId());
//			p.bewege(s1.getFigur(1));
//			System.out.println("Neue Position: " + s1..getFigur(1).getPosition().getId());
//			break;
//
//		case blau:
//			System.out.println("Alte Position von " + p.getSpielerAmZug().getFarbe() + ": " + s2.getFigur(1).getPosition().getId());
//			p.bewege(s2.getFigur(1));
//			System.out.println("Neue Position: " + s2.getFigur(1).getPosition().getId());
//			break;
//	
//		case gruen:
//			System.out.println("Alte Position von " + p.getSpielerAmZug().getFarbe() + ": " + s3.getFigur(1).getPosition().getId());
//			p.bewege(s3.getFigur(1));
//			System.out.println("Neue Position: " + s3.getFigur(1).getPosition().getId());
//			break;
//	
//		case gelb:
//			System.out.println("Alte Position von " + p.getSpielerAmZug().getFarbe() + ": " + s4.getFigur(1).getPosition().getId());
//			p.bewege(s4.getFigur(1));
//			System.out.println("Neue Position: " + s4.getFigur(1).getPosition().getId());
//			break;
//		}
	}
}

