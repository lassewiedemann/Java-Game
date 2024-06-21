
/**
 * Klasse zur Beschreibung eines Bodens
 * 
 * @author Klaus Reinold
 * @version 1.0
 */
public class Boden
{

    /**
     * Konstruktor für Objekte der Klasse Boden
     */
    public Boden(int anzahlElemente, double startX, double startY)
    {
        for ( int i=0 ; i<anzahlElemente ; i=i+1 )
        {
            FIGUR f = new FIGUR("Plattform 1.new.png");
            f.setzeMittelpunkt( startX+i*2.13 , startY );
            f.machePassiv();
        }
    }
  
}
