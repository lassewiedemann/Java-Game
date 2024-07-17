/**
                         _                        _            
  /\  /\__ _ _   _ _ __ | |_    /\  /\_   _ _ __ | |_ ___ _ __ 
 / /_/ / _` | | | | '_ \| __|  / /_/ / | | | '_ \| __/ _ \ '__|
/ __  / (_| | |_| | | | | |_  / __  /| |_| | | | | ||  __/ |   
\/ /_/ \__,_|\__,_|_| |_|\__| \/ /_/  \__,_|_| |_|\__\___|_|   
                                                                
Datei: Lebensanzeige
Fertigstellungsdatum: 23. Juni 2024
Autoren: Lasse Wiedemann, Marinus Urch, Tilo Engelbrecht, Natasha Erhart Schlabitz, Judith Krumme
                                                      
*/
                                            

//----------------------------------------Lebensanzeige-Klasse----------------------------------------//
// Öffentliche Klasse Lebensanzeige
public class Lebensanzeige extends FIGUR implements Ticker {
    Welt welt;  // Die Welt, zu der die Lebensanzeige gehört
    Traveler playerToTrack;

    // Konstruktor für die Lebensanzeige
    Lebensanzeige(Welt weltneu, Traveler spieler) {
        super("idle", "health3.gif");  // Initialisierung im Zustand "idle" mit entsprechendem Bild
        
        super.setzeMittelpunkt(0, 0);  // Setzen des Mittelpunkts
        super.macheNeutral();  // Die Lebensanzeige ist passiv (nicht aktiv steuerbar)
        
        // Hinzufügen der Zustände für verschiedene Lebensstände und Setzen der Animationsgeschwindigkeit
        super.fuegeZustandVonGifHinzu("3_Leben", "health3.gif");
        super.setzeAnimationsgeschwindigkeit("3_Leben", 1);
                                            
        super.fuegeZustandVonGifHinzu("2_Leben", "health2.gif");
        super.setzeAnimationsgeschwindigkeit("2_Leben", 1);
        
        super.fuegeZustandVonGifHinzu("1_Leben", "health1.gif");
        super.setzeAnimationsgeschwindigkeit("1_Leben", 1);
 
        super.fuegeZustandVonGifHinzu("0_Leben", "health0.gif");
        super.setzeAnimationsgeschwindigkeit("0_Leben", 1);
        
        starteTickerNeu(0.02);  // Starten des Timers mit einer Tickrate von 0.02 Sekunden
        
        welt = weltneu;  // Zuweisen der Welt
        
        skaliere(1.5);  // Skalieren der Lebensanzeige
        setzeEbene(1);  // Setzen der Ebene auf 1 (über anderen Elementen)
        
        playerToTrack = spieler;
    }
    
    // Überschriebene Methode tick für die Lebensanzeige
    @Override
    public void tick() {
        // Position der Lebensanzeige relativ zur Spielfigur setzen
        this.setzePosition(playerToTrack.nennePosition().getX() - .2, playerToTrack.nennePosition().getY() + 3.5);
        
        int TravelerHealth = playerToTrack.getHealth();  // Gesundheitszustand der Spielfigur abrufen
        
        // Entsprechend dem Gesundheitszustand der Spielfigur den passenden Zustand setzen
        if (TravelerHealth >= 3) {
            setzeZustand("3_Leben");
        } else if (TravelerHealth == 2) {
            setzeZustand("2_Leben");
        } else if (TravelerHealth == 1) {
            setzeZustand("1_Leben");
        } else if (TravelerHealth <= 0) {
            setzeZustand("0_Leben");
        }
    }
}
