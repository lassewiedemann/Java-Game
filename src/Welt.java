import java.util.Random;

public class Welt
{
    public Wand[] waende;
    public Boden[] boeden;
    public Boden2[] boeden2;
    public Traveler spielfigur;
    public Enemy[] gegner;
    public FIGUR hintergrund;
    public Lebensanzeige HealthBar;
    public TEXT text;
    public TEXT text1;
    public int gegnerCount;
    private Random generator;
    public int random;
    
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
        
        gegner = new Enemy[4096];
        gegnerCount = 0;
        
        spielfigur = new Traveler(this);
        HealthBar = new Lebensanzeige(this);
        
        text = new TEXT(-20, 10, 2, "" + spielfigur.score);
        text1 = new TEXT(0, 0, 4, "GAME OVER");
        text1.setzeSichtbar(false);
        
        generator = new Random();
    }
    
    public void spawngegner(){
        random = generator.nextInt(4)+3;
        for (int i = 0; i < random; i++){
            gegner[gegnerCount + i] = new Enemy(getRandomCoordinate(-25, 25), getRandomCoordinate(-12, 9), this);
        }
        gegnerCount += random;
    }
    
    private int getRandomCoordinate(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    //Funktion um hintergrund zu aendern (hilfreich fuer evtl mehrere Level/Szenen)
    public void hintergrundSetzen(String fileName){
        FIGUR hintergrund = new FIGUR( fileName );
        hintergrund.setzeMittelpunkt( 0 , 0 );
        hintergrund.skaliere(1);
    }
}