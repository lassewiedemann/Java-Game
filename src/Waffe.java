/**
                         _                        _            
  /\  /\__ _ _   _ _ __ | |_    /\  /\_   _ _ __ | |_ ___ _ __ 
 / /_/ / _` | | | | '_ \| __|  / /_/ / | | | '_ \| __/ _ \ '__|
/ __  / (_| | |_| | | | | |_  / __  /| |_| | | | | ||  __/ |   
\/ /_/ \__,_|\__,_|_| |_|\__| \/ /_/  \__,_|_| |_|\__\___|_|   
                                                                 
Datei: Waffe
Fertigstellungsdatum: 23. Juni 2024
Autoren: Lasse Wiedemann, Marinus Urch, Tilo Engelbrecht, Natasha Erhart Schlabitz, Judith Krumme
                                                     
*/
public class Waffe extends FIGUR implements Ticker {
    private double vX;
    private static double v_idle = 0, v_runR = 0.2, v_runL = -0.2;
    private String direction;
    Welt welt;
    private int ticksExisted;

    public Waffe(Welt weltneu, String direction) {
        super("idle", "Knife.gif");
        welt = weltneu;
        setzeAnimationsgeschwindigkeit("idle", 0.2);

        super.setzeMittelpunkt(welt.spielfigur[1].nennePosition().getX(), welt.spielfigur[1].nennePosition().getY());
        super.macheNeutral();

        vX = v_idle;
        this.direction = direction;
        skaliere(1);
        starteTickerNeu(0.04);

        if(direction == "right" || direction == "left")
            setzeMittelpunkt(welt.spielfigur[1].nenneMittelpunktX() + (direction == "right" ? 1 : -1.5), welt.spielfigur[1].nenneActor().getCenter().getY());
        else if(direction == "up" || direction == "down")            
            setzeMittelpunkt((float)welt.spielfigur[1].nenneMittelpunktX(), (float)(welt.spielfigur[1].nenneMittelpunktY() + (direction == "up" ? 1.5 : -1.5)));
            
        if(direction == "left")
            spiegelnHorizontal(true);
        else if(direction == "up")
            drehenUm(90);
        else if(direction == "down")
            drehenUm(-90);
            
        ticksExisted = 0;
    }

    @Override
    public void tick() {
        if(direction == "right" || direction == "left")
            verschiebenUm((direction == "right" ? v_runR : v_runL), 0);
        else if(direction == "up" || direction == "down")
            verschiebenUm(0, (direction == "up" ? .2 : -.2));
        ticksExisted++;
        if(ticksExisted == 75)
            this.entfernen();
        for(int i=0; i<welt.gegnerCount; i++){
            if(this.beruehrt(welt.gegner[i])){
                welt.spielfigur[1].killEnemy(welt.gegner[i]);
                this.entfernen();
            }
        }
    }
}
