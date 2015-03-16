package classes;
/**
 * Die Klasse Spielfeld
 * @author ""
 * @version 1.0
 **/
public class Spielfeld {
	
	private Spielfigur figur;
	private int id;
	private FeldTyp typ;
	private FarbEnum feldFarbe;
	
	
	//Konstruktor �bergibt den Feldtyp, die Farbe und die ID vom Feld
	public Spielfeld(FeldTyp typ, FarbEnum farbe, int id){
		switch(typ)
		{
		case Normalfeld: this.typ = FeldTyp.Normalfeld; this.id=id;  feldFarbe = farbe;  break;
		case Startfeld: this.typ = FeldTyp.Startfeld; this.id=id;  feldFarbe = farbe;  break;
		case Endfeld: this.typ = FeldTyp.Endfeld; this.id=id;  feldFarbe = farbe;  break;
		default: throw new RuntimeException("Kein g�ltiges Feld konnte erstellt werden!");
		}
	}
	
	//Gibt die Spielfigur zur�ck
	public Spielfigur getFigur(){
		return this.figur;
	}
	
	//
	public void setFigur(Spielfigur figur){
		if(figur!=null){
		this.figur=figur;
		} else throw new RuntimeException ("Andere Spielfigur ist schon drauf");
	}
	
	//Gibt die ID f�r das Feld zur�ck
	public int getId(){ 
		return this.id;
	}
	
	//Gibt den Feldtypen zur�ck
	public FeldTyp getTyp(){ 
		return this.typ;
	}
	
	//Die Methode gibt die Farbe vom Feld zur�ck
	public FarbEnum getFeldFarbe(){ 
		return this.feldFarbe;
	}
	
	
	public String toString(){
		return this.feldFarbe+" "+this.typ;
	}
	
}
