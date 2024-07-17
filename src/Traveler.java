/**
                         _                        _            
  /\  /\__ _ _   _ _ __ | |_    /\  /\_   _ _ __ | |_ ___ _ __ 
 / /_/ / _` | | | | '_ \| __|  / /_/ / | | | '_ \| __/ _ \ '__|
/ __  / (_| | |_| | | | | |_  / __  /| |_| | | | | ||  __/ |   
\/ /_/ \__,_|\__,_|_| |_|\__| \/ /_/  \__,_|_| |_|\__\___|_|   
                                                               
Datei: Traveler
Fertigstellungsdatum: 23. Juni 2024
Autoren: Lasse Wiedemann, Marinus Urch, Tilo Engelbrecht, Natasha Erhart Schlabitz, Judith Krumme
                                                    
*/

//----------------------------------------Traveler-Klasse----------------------------------------//

public abstract class Traveler extends FIGUR implements TastenReagierbar, Ticker, MausKlickReagierbar {
    protected double vX;  // Geschwindigkeit in X-Richtung
    protected static double v_idle = 0, v_walkR = 0.2, v_walkL = -0.2, v_runR = 0.2, v_runL = -0.2;  // Geschwindigkeitskonstanten
    protected int health;  // Lebenspunkte und Zähler für den Spawn
    Welt welt;  // Referenz auf die Spielwelt
    public int score;  // Punktzahl
    public boolean gameOver;  // Spiel vorbei Flagge
    
    // Konstruktor für Traveler-Klasse
    public Traveler(Welt weltneu) {
        //----------------------------------------Konstruktor----------------------------------------//
        // Initialisierung im Zustand "idle"
        super("idle", "hansIdle.gif");
        welt = weltneu;
        setzeAnimationsgeschwindigkeit("idle", 0.2);

        super.setzeMittelpunkt(-10, 0);  // Setzen des Mittelpunkts der Figur
        super.macheAktiv();  // Aktivierung der Figur
        vX = v_idle;

        // Hinzufügen der Zustände und Setzen der Animationsgeschwindigkeit für verschiedene Zustände
        //----------------------------------------Zustände hinzufügen----------------------------------------//
        super.fuegeZustandVonGifHinzu("walk", "hansRun.gif");
        super.setzeAnimationsgeschwindigkeit("walk", 0.2);

        super.fuegeZustandVonGifHinzu("run", "hansRun.gif");
        super.setzeAnimationsgeschwindigkeit("run", 0.1);

        super.fuegeZustandVonGifHinzu("jumpUp", "hansIdle.gif");
        super.setzeAnimationsgeschwindigkeit("jumpUp", 0.1);

        super.fuegeZustandVonGifHinzu("jumpTurn", "hansIdle.gif");
        super.setzeAnimationsgeschwindigkeit("jumpTurn", 0.02);

        super.fuegeZustandVonGifHinzu("jumpDown", "hansIdle.gif");
        super.setzeAnimationsgeschwindigkeit("jumpDown", 0.1);

        super.setzeAutomatischenUebergang("jumpTurn", "jumpDown");  // Automatischer Übergang von jumpTurn zu jumpDown

        starteTickerNeu(0.04);  // Starten des Tickers mit einer Rate von 0.04

        health = 3;  // Startlebenspunkte
        score = 0;  // Startpunktzahl

        skaliere(.3);  // Skalierung der Figur

        gameOver = false;  // Spiel ist nicht vorbei
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

    // Reaktion auf gedrückte Tasten
    @Override
    public void tasteReagieren(int code) {
        //----------------------------------------tasteReagieren-Methode----------------------------------------//
        if (code == TASTE.RECHTS) {
            if (vX == v_runL) {
                vX = v_walkL;
                setzeZustand("run");
            } else if (vX == v_walkL) {
                vX = v_idle;
                setzeZustand("run");
            } else if (vX == v_idle) {
                vX = v_walkR;
                setzeZustand("run");
                spiegelnHorizontal(false);
            } else if (vX == v_walkR) {
                vX = v_runR;
                setzeZustand("run");
            }
        } else if (code == TASTE.LINKS) {
            if (vX == v_runR) {
                vX = v_walkR;
                setzeZustand("run");
            } else if (vX == v_walkR) {
                vX = v_idle;
                setzeZustand("run");
            } else if (vX == v_idle) {
                vX = v_walkL;
                setzeZustand("run");
                spiegelnHorizontal(true);
            } else if (this.vX == v_walkL) {
                vX = v_runL;
                setzeZustand("run");
            }
        } else if (code == TASTE.RUNTER) {
            vX = v_idle;
            setzeZustand("run");
        } else if (code == TASTE.RAUF) {
            springe(12);
            setzeZustand("jumpUp");
        }
    }

    // Reaktion auf Mausklicks
    @Override
    public void klickReagieren(double x, double y) {
        //----------------------------------------klickReagieren-Methode----------------------------------------//
        for (int i = 0; i < welt.gegnerCount; i++) {
            double abstandX = this.berechneAbstandX(welt.gegner[i]);
            double abstandY = this.berechneAbstandY(welt.gegner[i]);

            double sumAbstandX = Math.abs(abstandX);
            double sumAbstandY = Math.abs(abstandY);

            double a2b2 = Math.pow(sumAbstandX, 2) + Math.pow(sumAbstandY, 2);
            double range = Math.sqrt(a2b2);

            if (range <= 5 && welt.gegner[i].beinhaltetPunkt(x, y)) {
                killEnemy(welt.gegner[i]);
            }
        }
    }

    public void killEnemy(Enemy enemy){
            //enemy.setzeSichtbar(false);
            //enemy.verschiebenUm(0, -1000);
            enemy.entfernen();
            score++;
            welt.text.setzeInhalt("Score: " + (welt.spielfigur[0].score + welt.spielfigur[1].score));
    }
    
    // Tick-Methode für Bewegung und Verhalten der Figur
    @Override
    public void tick() {
        //----------------------------------------tick-Methode----------------------------------------//
        if (gameOver)
            return;

        if (vX < 0 && M_x <= -24 || vX > 0 && M_x >= 24) {

        } else {
            verschiebenUm(this.vX, 0);
        }

        // Zustandsübergang während des Sprunges
        if (nenneAktuellenZustand() == "jumpUp" && nenneGeschwindigkeitY() < 0) {
            super.setzeZustand("jumpTurn");
        }

        // Zustandsübergang nach dem Sprung
        if (nenneAktuellenZustand() == "jumpDown" && steht()) {
            if (vX == v_runR || vX == v_runL || vX == v_walkR || vX == v_walkL || vX == v_idle) {
                super.setzeZustand("idle");
            }
        }

        // Game Over, wenn keine Lebenspunkte mehr vorhanden sind
        if (health <= 0 && !gameOver) {
            GameOver();
        }
    }

    // Methode für das Game Over
    public void GameOver() {
        //----------------------------------------GameOver-Methode----------------------------------------//
        gameOver = true;
        welt.spielfigur[0].verschiebenUm(0, -100);
        welt.spielfigur[1].verschiebenUm(0, -100);
        welt.HealthBar[0].verschiebenUm(0, -100);
        welt.HealthBar[1].verschiebenUm(0, -100);
        for (int i = 0; i < welt.gegnerCount; i++) {
            welt.gegner[i].verschiebenUm(0, -100);
        }
        welt.text1.setzeSichtbar(true);
        welt.spielfigur[0].gameOver = true;
        welt.spielfigur[1].gameOver = true;
        if(welt.mode == "Versus")
        if(this instanceof Player1){
            welt.text1.setzeInhalt("Player 2 hat gewonnen");
            welt.text1.verschiebenUm(-7, 0);
        }else{
            welt.text1.setzeInhalt("Player 1 hat gewonnen");
            welt.text1.verschiebenUm(-7, 0);
        }
    }

    // Methode zum Abrufen der Lebenspunkte
    public int getHealth() {
        return health;
    }

    // Methode zum Abziehen von Lebenspunkten
    public void zieheLebenAb() {
        health--;
    }

    // Methode zum Abrufen der aktuellen Geschwindigkeit in X-Richtung
    public double getvX() {
        return this.vX;
    }

    // Methode zum Setzen der Geschwindigkeit in X-Richtung
    public void setvX(double newvX) {
        this.vX = newvX;
    }
}
