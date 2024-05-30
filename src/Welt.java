public class Welt 
{
    Wand[] waende;
    Boden[] boeden;
    Traveler spielfigur;
    Enemy[] gegner;
    FIGUR hintergrund;
    
    public Welt()
    {
        hintergrundSetzen("hintergrund 2.png");
        boeden = new Boden[3];
        boeden[0] = new Boden(17, -25,-12);  
        boeden[1] = new Boden(1,-27, -10);
        boeden[2] = new Boden(1,22,-10);
        
        //25.05.2024 Waende hinzugefuegt
        waende = new Wand[1];
        waende[0]= new Wand(2, 0, -10);
        
        //26.05.2024 Gegner hinzugefuegt - nicht fertig
        gegner = new Enemy[1];
        gegner[0] = new Enemy(5,5);
        
        
        spielfigur = new Traveler();
        
    }
    //Funktion um hintergrund zu aendern (hilfreich fuer evtl mehrere Level/Szenen)
    public void hintergrundSetzen(String fileName){
        FIGUR hintergrund = new FIGUR( fileName );
        hintergrund.setzeMittelpunkt( 0 , 0 );
        hintergrund.skaliere(1);
    }
}