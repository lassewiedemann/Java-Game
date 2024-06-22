public class Traveler 

extends     FIGUR
implements  TastenReagierbar, Ticker, MausKlickReagierbar
{
    private double vX;
    private static double v_idle=0 , v_walkR=0.2 , v_walkL=-0.2 , v_runR=0.2 , v_runL=-0.2;
    private int health, spawnTick;
    Welt welt;
    public int score;
    
    public Traveler(Welt weltneu)
    {
        // Zustand "idle"
        super( "idle" , "traveler_idle.gif" );
        welt = weltneu;
        setzeAnimationsgeschwindigkeit( "idle", 0.2 );
        
        super.setzeMittelpunkt(-10,0);
        super.macheAktiv();
        vX = v_idle;
        super.fuegeZustandVonGifHinzu( "walk", "traveler_walk.gif" );
        super.setzeAnimationsgeschwindigkeit( "walk", 0.2 );
                                            
        super.fuegeZustandVonGifHinzu( "run", "traveler_run.gif" );
        super.setzeAnimationsgeschwindigkeit( "run", 0.1 );
        
        super.fuegeZustandVonGifHinzu( "jumpUp", "traveler_jump_1up.gif" );
        super.setzeAnimationsgeschwindigkeit( "jumpUp", 0.1 );
 
        super.fuegeZustandVonGifHinzu( "jumpTurn", "traveler_jump_2midair.gif" );
        super.setzeAnimationsgeschwindigkeit( "jumpTurn", 0.02 );
        
        super.fuegeZustandVonGifHinzu( "jumpDown", "traveler_jump_3down.gif" );
        super.setzeAnimationsgeschwindigkeit( "jumpDown", 0.1 );
        
        super.setzeAutomatischenUebergang( "jumpTurn", "jumpDown" );
        
        starteTickerNeu( 0.04 );
        
        health = 3;
        score = 0;
        spawnTick = 0;
    }
     @Override
    public void tasteLosgelassenReagieren(int code)
    {
            if (code == TASTE.RECHTS ||code == TASTE.LINKS ) 
            {
                vX = v_idle;
            super.setzeZustand( "idle" );

            } 
            
    }
    @Override
    public void tasteReagieren( int code )
    {
        if ( code == TASTE.RECHTS )
        {
            if ( vX == v_runL )  
            { 
                vX = v_walkL; 
                setzeZustand("run"); 
            }
            else if ( vX == v_walkL ) 
            { 
                vX = v_idle;  
                setzeZustand("run"); 
            }
            else if ( vX == v_idle )  
            { 
                vX = v_walkR; 
                setzeZustand("run"); 
                spiegelnHorizontal( false ); 
            }
            else if ( vX == v_walkR ) 
            { 
                vX = v_runR; 
                setzeZustand("run");
            }
        }
        else if ( code == TASTE.LINKS )
        {
            if ( vX == v_runR )  
            { 
                vX = v_walkR;
                setzeZustand( "run" );
            }
            else if ( vX == v_walkR ) 
            { 
                vX = v_idle;
                setzeZustand( "run" );
            }
            else if ( vX == v_idle )  
            { 
                vX = v_walkL;
                setzeZustand( "run" );
                spiegelnHorizontal( true ); 
            }
            else if ( this.vX == v_walkL ) 
            { 
                vX = v_runL; 
                setzeZustand( "run" );
            }
        }
        else if ( code == TASTE.RUNTER )
        {
            vX = v_idle;
            setzeZustand( "run" );
        }
        else if ( code == TASTE.RAUF )
        {
            springe( 12 );
            setzeZustand( "jumpUp" );
        }
    }
    
    @Override
    public void klickReagieren( double x , double y ) 
    {
        
        //System.out.println( "Klick bei (" + x  + ", " + y + ")." );
        
        for(int i = 0; i < welt.gegnerCount; i++){
            double abstandX = this.berechneAbstandX(welt.gegner[i]);
            double abstandY = this.berechneAbstandY(welt.gegner[i]);
            
            double sumAbstandX = Math.abs(abstandX);
            double sumAbstandY = Math.abs(abstandY);
            
            double a2b2 = Math.pow(sumAbstandX, 2) + Math.pow(sumAbstandY, 2);
            double range = Math.sqrt(a2b2);
            
            System.out.println(sumAbstandX);
            System.out.println(sumAbstandY);
            
            System.out.println(range);
            
            if(range <= 5 && welt.gegner[i].beinhaltetPunkt(x, y)){
                welt.gegner[i].setzeSichtbar(false);
                welt.gegner[i].verschiebenUm(0, -1000);
                score++;
                welt.text.setzeInhalt("Score: " + score);
            }
        }
        
        
        
    }
    
    @Override
    public void tick()
    {   
        if (vX < 0 && M_x <= -24 || vX >0 && M_x >= 24)
        {
           
        }
        else
        {
            verschiebenUm( this.vX , 0 );
        }
        if ( nenneAktuellenZustand() == "jumpUp" && nenneGeschwindigkeitY()<0 )
        {
            super.setzeZustand( "jumpTurn" );
        }
        if (nenneAktuellenZustand() == "jumpDown"  &&  steht() )
        {
            if ( this.vX == v_runR || this.vX == v_runL )
            {
                super.setzeZustand( "run" );
            }
            else if ( this.vX == v_walkR || this.vX == v_walkL )
            {
                super.setzeZustand( "run" );
            }
            else if ( this.vX == v_idle )
            {
                super.setzeZustand( "idle" );
            }
        }
        
        if(health <= 0){
           GameOver();
           welt.text1.setzeSichtbar(true);
        }
        
        spawnTick++;
        if (spawnTick % 175 == 0){
            welt.spawngegner();
        }
    }
    
    public void GameOver(){
        //welt.spielfigur.entfernen();
        //welt.HealthBar.entfernen();
        //for(int i = 0; i < welt.gegner.length; i++){
        //    welt.gegner[i].entfernen();
        //}
    }
    
    public int getHealth(){
        return  health;
    }
    
    public void zieheLebenAb(){
        health--;
    }
    
    public double getvX(){
        return this.vX;
    }
    
    public void setvX(double newvX){
        this.vX = newvX;
    }
    
}

