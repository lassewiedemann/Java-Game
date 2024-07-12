/**
                         _                        _            
  /\  /\__ _ _   _ _ __ | |_    /\  /\_   _ _ __ | |_ ___ _ __ 
 / /_/ / _` | | | | '_ \| __|  / /_/ / | | | '_ \| __/ _ \ '__|
/ __  / (_| | |_| | | | | |_  / __  /| |_| | | | | ||  __/ |   
\/ /_/ \__,_|\__,_|_| |_|\__| \/ /_/  \__,_|_| |_|\__\___|_|   
                                                                
Datei: WorldTicker
Fertigstellungsdatum: 23. Juni 2024
Autoren: Lasse Wiedemann, Marinus Urch, Tilo Engelbrecht, Natasha Erhart Schlabitz, Judith Krumme
                                                      
*/
                                            
public class WorldTicker extends FIGUR implements Ticker {
    Welt welt; 
    private int spawnTick;
    
    WorldTicker(Welt weltneu) {
        super("idle", "health3.gif");
        
        super.setzeMittelpunkt(1000, 0);
        super.machePassiv();
        
        starteTickerNeu(7);
        
        spawnTick = 0;
        
        welt = weltneu;
    }
    
    @Override
    public void tick() {
            welt.spawngegner();
    }
}
