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
        this.setzeErkundungsmodusAktiv(true);
        
        //Koordinatensystem fuer Leveldesign
        this.zeigeKoordinatensystem(true);
    }
}