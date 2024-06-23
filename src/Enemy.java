/**
                         _                        _            
  /\  /\__ _ _   _ _ __ | |_    /\  /\_   _ _ __ | |_ ___ _ __ 
 / /_/ / _` | | | | '_ \| __|  / /_/ / | | | '_ \| __/ _ \ '__|
/ __  / (_| | |_| | | | | |_  / __  /| |_| | | | | ||  __/ |   
\/ /_/ \__,_|\__,_|_| |_|\__| \/ /_/  \__,_|_| |_|\__\___|_|   
                                                                 
Datei: Enemy
Fertigstellungsdatum: 23. Juni 2024
Autoren: Lasse Wiedemann, Marinus Urch, Tilo Engelbrecht, Natasha Erhart Schlabitz, Judith Krumme
                                                     
*/

//----------------------------------------Enemy-Klasse----------------------------------------//

public class Enemy extends FIGUR implements TastenReagierbar, Ticker {
    private int damageTick;  // Zähler für Schaden
    private double vX;  // Geschwindigkeit in X-Richtung
    private static double v_idle = 0, v_walkR = 0.1, v_walkL = -0.1, v_runR = 0.2, v_runL = -0.2;  // Geschwindigkeitskonstanten
    Welt welt;  // Referenz auf die Spielwelt

    // Konstruktor für Enemy-Klasse
    public Enemy(int spawnX, int spawnY, Welt weltneu) {
        //----------------------------------------Konstruktor----------------------------------------//
        // Initialisierung im Zustand "idle"
        super("idle", "traveler_idle.gif");
        welt = weltneu;
        setzeAnimationsgeschwindigkeit("idle", 0.2);

        super.setzeMittelpunkt(-10, 0);  // Setzen des Mittelpunkts der Figur
        super.macheAktiv();  // Aktivierung der Figur

        vX = v_idle;

        //----------------------------------------Zustände hinzufügen----------------------------------------//
        
        super.fuegeZustandVonGifHinzu("walk", "Geist.gif");
        super.setzeAnimationsgeschwindigkeit("walk", 0.2);

        super.fuegeZustandVonGifHinzu("run", "Geist.gif");
        super.setzeAnimationsgeschwindigkeit("run", 0.1);

        super.fuegeZustandVonGifHinzu("jumpUp", "Geist.gif");
        super.setzeAnimationsgeschwindigkeit("jumpUp", 0.1);

        super.fuegeZustandVonGifHinzu("jumpTurn", "Geist.gif");
        super.setzeAnimationsgeschwindigkeit("jumpTurn", 0.02);

        super.fuegeZustandVonGifHinzu("jumpDown", "Geist.gif");
        super.setzeAnimationsgeschwindigkeit("jumpDown", 0.1);

        super.setzeAutomatischenUebergang("jumpTurn", "jumpDown");  // Automatischer Übergang von jumpTurn zu jumpDown

        setzePosition(spawnX, spawnY);  // Setzen der Startposition
        starteTickerNeu(0.04);  // Starten des Tickers mit einer Rate von 0.04

        damageTick = 0;  // Initialisierung des Schadenszählers
    }

    // Reaktion auf losgelassene Tasten
    @Override
    public void tasteLosgelassenReagieren(int code) {
        //----------------------------------------tasteLosgelassenReagieren-Methode----------------------------------------//
        if (code == TASTE.RECHTS || code == TASTE.LINKS) {
            vX = v_idle;
            super.setzeZustand("idle");
        }
    }

    // Reaktion auf gedrückte Tasten (nicht verwendet)
    @Override
    public void tasteReagieren(int code) {
        //----------------------------------------tasteReagieren-Methode----------------------------------------//
        return;
    }

    // Tick-Methode für Bewegung und Verhalten der Figur
    @Override
    public void tick() {
        //----------------------------------------tick-Methode----------------------------------------//
        verschiebenUm(this.vX, 0);  // Bewegung in X-Richtung

        // Richtungsänderung basierend auf der Position des Spielers
        if (welt.spielfigur.nenneMittelpunktX() < this.nenneMittelpunktX()) {
            //----------------------------------------Spieler ist links----------------------------------------//
            if (berechneAbstandX(welt.spielfigur) >= 4) {
                if (vX != v_walkL) {
                    vX = v_walkL;
                    setzeZustand("walk");
                }
            } else {
                if (vX != v_runL) {
                    vX = v_runL;
                    setzeZustand("run");
                }
            }
            spiegelnHorizontal(false);  // Horizontal spiegeln
        } else if (welt.spielfigur.nenneMittelpunktX() > this.nenneMittelpunktX()) {
            //----------------------------------------Spieler ist rechts----------------------------------------//
            if (berechneAbstandX(welt.spielfigur) >= 4) {
                if (vX != v_walkR) {
                    vX = v_walkR;
                    setzeZustand("walk");
                }
            } else {
                if (vX != v_runR) {
                    vX = v_runR;
                    setzeZustand("run");
                }
            }
            spiegelnHorizontal(true);  // Horizontal spiegeln
        }

        // Springen, wenn bestimmte Bedingungen erfüllt sind
        if (this.berechneAbstandX(welt.spielfigur) <= 6 && this.berechneAbstandX(welt.spielfigur) >= 3 &&
                this.nenneMittelpunktY() < welt.spielfigur.nenneMittelpunktY()) {
            springe(5);
            setzeZustand("jumpUp");
        }

        // Zustandsübergang während des Sprunges
        if (nenneAktuellenZustand() == "jumpUp" && nenneGeschwindigkeitY() < 0) {
            super.setzeZustand("jumpTurn");
        }

        // Zustandsübergang nach dem Sprung
        if (nenneAktuellenZustand() == "jumpDown" && steht()) {
            if (this.vX == v_runR || this.vX == v_runL || this.vX == v_walkR || this.vX == v_walkL || this.vX == v_idle) {
                super.setzeZustand("idle");
            }
        }

        damageTick++;  // Inkrementieren des Schadenszählers

        // Schaden verursachen und Lebenspunkte abziehen, wenn die Figur die Spielfigur oder die HealthBar berührt
        if (damageTick % 25 == 0) {
            //----------------------------------------Schaden und Lebenspunkte----------------------------------------//
            if (this.beruehrt(welt.spielfigur) || this.beruehrt(welt.HealthBar)) {
                if (welt.spielfigur.getvX() < 0) {
                    verschiebenUm(-1, 0);  // Bewegung der Spielfigur nach der Kollision nach links
                } else {
                    verschiebenUm(1, 0);  // Bewegung der Spielfigur nach der Kollision nach rechts
                }
                welt.spielfigur.zieheLebenAb();  // Lebenspunkte der Spielfigur abziehen
            }
        }
    }
}
