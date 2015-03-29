package classes;
/**
 * Das Interface iBediener
 * @author 
 * @version 1.0
 */

public interface iBediener {
	
	public void wuerfeln();
	
	public void bewege(int id);
	
	public void spielerHinzufuegen(String name);
	
	public void beginneSpiel();
	
	public String getSpielerAmZug();
	
	}
