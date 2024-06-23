/**
                         _                        _            
  /\  /\__ _ _   _ _ __ | |_    /\  /\_   _ _ __ | |_ ___ _ __ 
 / /_/ / _` | | | | '_ \| __|  / /_/ / | | | '_ \| __/ _ \ '__|
/ __  / (_| | |_| | | | | |_  / __  /| |_| | | | | ||  __/ |   
\/ /_/ \__,_|\__,_|_| |_|\__| \/ /_/  \__,_|_| |_|\__\___|_|   
                                                                     
Datei: meinSpiel
Fertigstellungsdatum: 23. Juni 2024
Autoren: Lasse Wiedemann, Marinus Urch, Tilo Engelbrecht, Natasha Erhart Schlabitz, Judith Krumme
                                                    
*/


class meinSpiel extends SPIEL
{
    meinSpiel()
    {
        super(1456,816,true);
        Welt spielwelt = new Welt();
        //Wartet um Fehler zu vermeiden bei dem spielwelt.hintergrund noch nicht gesetzt ist
        try {
            Thread.sleep(150);
            this.setzeKamerafokus(spielwelt.hintergrund);
        }
        catch(Exception e){
            
        }
        // Erlaubt Kamerazoom als Positionierungshilfe
        //this.setzeErkundungsmodusAktiv(true);
        
        //Koordinatensystem fuer Leveldesign
        //this.zeigeKoordinatensystem(true);
    }
}