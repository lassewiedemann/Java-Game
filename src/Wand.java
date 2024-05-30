
/**
 * Wand
 */
public class Wand
{

    /**
     * Konstruktor für Objekte der Klasse Boden
     */
    public Wand(int anzahlElemente, double startX, double startY)
    {
        for ( int i=0 ; i<anzahlElemente ; i=i+1 )
        {
            FIGUR f = new FIGUR("Mauer1.png");
            f.setzeMittelpunkt( startX , startY+i*2.13 );
            f.machePassiv();
        }
    }

  
}
