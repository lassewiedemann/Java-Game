public class Enemy 

extends     FIGUR
implements  TastenReagierbar, Ticker
{
    private int damageTick;
    private double vX;
    private static double v_idle=0 , v_walkR=0.05 , v_walkL=-0.05 , v_runR=0.2 , v_runL=-0.2;
    Welt welt;
    
    public Enemy(int spawnX, int spawnY, Welt weltneu)
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
        
        //setzePosition(spawnX, spawnY);
        verschiebenUm(5, 0);
        starteTickerNeu( 0.04 );
        
        damageTick = 0;
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
        /**if ( code == TASTE.RECHTS )
        {
            if ( vX == v_runL )  
            { 
                vX = v_walkL; 
                setzeZustand("run"); 
            }
            else if ( vX == v_walkL ) 
            { 
                vX = v_idle;  
                setzeZustand("idle"); 
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
                setzeZustand( "idle" );
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
            setzeZustand( "idle" );
        }
        else if ( code == TASTE.RAUF )
        {
            springe( 10 );
            setzeZustand( "jumpUp" );
            }
            else if(code == TASTE.Y)
            {
                setzePosition(0, 0);
                //System.out.println("x: "+this.M_x +" y: "+this.M_y);
            }
            else if(code == TASTE.F)
            {
                verschiebenUm(0, 10);
            }**/
            return;
    }
    
    @Override
    public void tick()
    {
        verschiebenUm( this.vX , 0 );
        
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
        
        damageTick++;
        if(damageTick % 25 == 0){
            //System.out.println(damageTick);
            if(this.beruehrt(welt.spielfigur)){
                //welt.spielfigur.setvX(-welt.spielfigur.getvX());
                welt.spielfigur.verschiebenUm( -this.vX , 0 );
                welt.spielfigur.zieheLebenAb();
            }
        }
        
    }
}

