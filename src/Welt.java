public class Welt 
{
    public Wand[] waende;
    public Boden[] boeden;
    public Boden2[] boeden2;
    public Wand[] wand;
    public Traveler spielfigur;
    public Enemy[] gegner;
    public FIGUR hintergrund;
    public Lebensanzeige HealthBar;
    
    public Welt()
    {
        
        
        hintergrundSetzen("hintergrund 2.png");
        boeden = new Boden[7];
        boeden[0] = new Boden(16, -16,-13);  
        boeden[1] = new Boden(1,-27, -10.6);
        boeden[2] = new Boden(1,27,-10.6);
        boeden[3] = new Boden(1,32,-8.2);
        boeden[4] = new Boden(1,-32,-8.2);
        
        boeden2 = new Boden2[4];
        boeden2[0] =new Boden2(3,-8,-4);
        boeden2[1] = new Boden2(3,5,-4);
        boeden2[2]= new Boden2 (4,18,0);
        boeden2[3]= new Boden2 (4,-25,0);
        
        wand = new Wand[2];
        wand[0] = new Wand(15, -25, -10);
        wand[1] = new Wand(15, 25, -10);
        
        gegner = new Enemy[1];
        gegner[0] = new Enemy(5,5, this);
        
        spielfigur = new Traveler(this);
        HealthBar = new Lebensanzeige(this);
    }
    //Funktion um hintergrund zu aendern (hilfreich fuer evtl mehrere Level/Szenen)
    public void hintergrundSetzen(String fileName){
        FIGUR hintergrund = new FIGUR( fileName );
        hintergrund.setzeMittelpunkt( 0 , 0 );
        hintergrund.skaliere(1);
    }
}