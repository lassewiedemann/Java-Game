/**
                         _                        _            
  /\  /\__ _ _   _ _ __ | |_    /\  /\_   _ _ __ | |_ ___ _ __ 
 / /_/ / _` | | | | '_ \| __|  / /_/ / | | | '_ \| __/ _ \ '__|
/ __  / (_| | |_| | | | | |_  / __  /| |_| | | | | ||  __/ |   
\/ /_/ \__,_|\__,_|_| |_|\__| \/ /_/  \__,_|_| |_|\__\___|_|   
                                                                
Datei: Welt
Fertigstellungsdatum: 23. Juni 2024
Autoren: Lasse Wiedemann, Marinus Urch, Tilo Engelbrecht, Natasha Erhart Schlabitz, Judith Krumme
                                                     
*/


//----------------------------------------Welt-Klasse----------------------------------------//
// Importieren der Random-Klasse für zufällige Generierung
import java.util.Random;

// Öffentliche Klasse Welt
public class Welt {
    public Wand[] waende;  // Array von Wänden
    public Boden[] boeden;  // Array von Böden
    public Boden2[] boeden2;  // Array von weiteren Böden
    public Traveler spielfigur;  // Die Spielfigur
    public Enemy[] gegner;  // Array von Gegnern
    public FIGUR hintergrund;  // Hintergrundfigur
    public Lebensanzeige HealthBar;  // Anzeige der Lebenspunkte
    public TEXT text;  // Textobjekt für die Punkteanzeige
    public TEXT text1;  // Textobjekt für die Game Over-Anzeige
    public int gegnerCount;  // Anzahl der Gegner
    private Random generator;  // Zufallsgenerator
    public int random;  // Zufälliger Wert

    // Konstruktor für die Weltklasse
    public Welt() {
        hintergrundSetzen("hintergrund 2.png");  // Setzen des Hintergrunds

        // Initialisierung der Bodenobjekte
        boeden = new Boden[7];
        boeden[0] = new Boden(16, -16, -13);
        boeden[1] = new Boden(1, -27, -10.6);
        boeden[2] = new Boden(1, 27, -10.6);
        boeden[3] = new Boden(1, 32, -8.2);
        boeden[4] = new Boden(1, -32, -8.2);

        // Initialisierung der weiteren Bodenobjekte
        boeden2 = new Boden2[4];
        boeden2[0] = new Boden2(3, -8, -4);
        boeden2[1] = new Boden2(3, 5, -4);
        boeden2[2] = new Boden2(4, 18, 0);
        boeden2[3] = new Boden2(4, -25, 0);

        gegner = new Enemy[4096];  // Initialisierung des Gegnerarrays
        gegnerCount = 0;  // Startanzahl der Gegner

        spielfigur = new Traveler(this);  // Initialisierung der Spielfigur
        HealthBar = new Lebensanzeige(this);  // Initialisierung der Lebensanzeige

        // Initialisierung der Textobjekte für Score und Game Over
        text = new TEXT(-20, 10, 2, "Score: " + spielfigur.score);
        text1 = new TEXT(0, 0, 4, "GAME OVER");
        text1.setzeSichtbar(false);  // Game Over Text unsichtbar machen

        generator = new Random();  // Initialisierung des Zufallsgenerators
    }

    // Methode zum Spawnen von Gegnern
    public void spawngegner() {
        random = generator.nextInt(4) + 3;  // Zufallszahl für die Anzahl der Gegner
        for (int i = 0; i < random; i++) {
            // Erzeugen neuer Gegner mit zufälligen Koordinaten
            gegner[gegnerCount + i] = new Enemy(getRandomCoordinate(-25, 25), getRandomCoordinate(-12, 9), this);
        }
        gegnerCount += random;  // Erhöhen der Anzahl der Gegner
    }

    // Methode zum Generieren einer zufälligen Koordinate innerhalb eines bestimmten Bereichs
    private int getRandomCoordinate(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // Methode zum Setzen des Hintergrunds
    public void hintergrundSetzen(String fileName) {
        FIGUR hintergrund = new FIGUR(fileName);
        hintergrund.setzeMittelpunkt(0, 0);
        hintergrund.skaliere(1);
    }
}
