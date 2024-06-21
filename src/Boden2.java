
/**
 * Beschreiben Sie hier die Klasse Boden2.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Boden2
{
     public Boden2(int anzahlElemente, double startX, double startY)
    {
        for ( int i=0 ; i<anzahlElemente ; i=i+1 )
        {
            FIGUR f = new FIGUR("Plattform4.png");
            f.setzeMittelpunkt( startX+i*2.13 , startY );
            f.machePassiv();
        }
    }
  
}
