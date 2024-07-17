/**
                         _                        _            
  /\  /\__ _ _   _ _ __ | |_    /\  /\_   _ _ __ | |_ ___ _ __ 
 / /_/ / _` | | | | '_ \| __|  / /_/ / | | | '_ \| __/ _ \ '__|
/ __  / (_| | |_| | | | | |_  / __  /| |_| | | | | ||  __/ |   
\/ /_/ \__,_|\__,_|_| |_|\__| \/ /_/  \__,_|_| |_|\__\___|_|   
                                                                 
Datei: Ult
Fertigstellungsdatum: 23. Juni 2024
Autoren: Lasse Wiedemann, Marinus Urch, Tilo Engelbrecht, Natasha Erhart Schlabitz, Judith Krumme
                                                     
**/
public class Ult extends FIGUR implements Ticker {
    private double vX;
    private static double v_idle = 0, v_runR = 0.2, v_runL = -0.2;
    Welt welt;
    private int ticksExisted;

    public Ult(Welt weltneu) {
        super("idle", "Ult.gif");
        welt = weltneu;
        setzeAnimationsgeschwindigkeit("idle", 0.2);

        super.setzeMittelpunkt(welt.spielfigur[1].nennePosition().getX(), welt.spielfigur[1].nennePosition().getY());
        super.macheNeutral();

        vX = v_idle;
        skaliere(1);
        starteTickerNeu(0.04);

        setzePosition(welt.spielfigur[1].nenneMittelpunktX() , welt.spielfigur[1].nenneActor().getCenter().getY());
        
        ticksExisted = 0;
    }

    @Override
    public void tick() {
        ticksExisted++;
        if(ticksExisted == 50)
            this.entfernen();
        for(int i=0; i<welt.gegnerCount; i++){
            if(this.beruehrt(welt.gegner[i])){
                welt.spielfigur[1].killEnemy(welt.gegner[i]);
                this.entfernen();
            }
        }
    }
}
