public class Enemy 

extends     FIGUR
implements  TastenReagierbar, Ticker
{
    private int damageTick;
    private double vX;
    private static double v_idle=0 , v_walkR=0.1 , v_walkL=-0.1 , v_runR=0.2 , v_runL=-0.2;
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
        super.fuegeZustandVonGifHinzu( "walk", "Geist.gif" );
        super.setzeAnimationsgeschwindigkeit( "walk", 0.2 );
                                            
        super.fuegeZustandVonGifHinzu( "run", "Geist.gif" );
        super.setzeAnimationsgeschwindigkeit( "run", 0.1 );
        
        super.fuegeZustandVonGifHinzu( "jumpUp", "Geist.gif" );
        super.setzeAnimationsgeschwindigkeit( "jumpUp", 0.1 );
 
        super.fuegeZustandVonGifHinzu( "jumpTurn", "Geist.gif" );
        super.setzeAnimationsgeschwindigkeit( "jumpTurn", 0.02 );
        
        super.fuegeZustandVonGifHinzu( "jumpDown", "Geist.gif" );
        super.setzeAnimationsgeschwindigkeit( "jumpDown", 0.1 );
        
        super.setzeAutomatischenUebergang( "jumpTurn", "jumpDown" );
        
        setzePosition(spawnX, spawnY);
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
            return;
    }
    
    @Override
    public void tick()
    {
        verschiebenUm( this.vX , 0 );
        
        if(welt.spielfigur.nenneMittelpunktX() < this.nenneMittelpunktX()){
            if(berechneAbstandX(welt.spielfigur) >= 4){
                if(vX != v_walkL){
                    vX = v_walkL;
                    setzeZustand("walk");
                }
            }else{
                if(vX != v_runL){
                    vX = v_runL;
                    setzeZustand("run");
                }
            }
            spiegelnHorizontal( false ); 
        }else if(welt.spielfigur.nenneMittelpunktX() > this.nenneMittelpunktX()){
            if(berechneAbstandX(welt.spielfigur) >= 4){
                if(vX != v_walkR){
                    vX = v_walkR;
                    setzeZustand("walk");
                }
            }else{
                if(vX != v_runR){
                    vX = v_runR;
                    setzeZustand("run");
                }
            }
            spiegelnHorizontal( true ); 
        }
        
        if(this.berechneAbstandX(welt.spielfigur) <= 6 && this.berechneAbstandX(welt.spielfigur) >= 3 && this.nenneMittelpunktY() < welt.spielfigur.nenneMittelpunktY()){
            springe( 5 );
            setzeZustand( "jumpUp" );
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
        
        damageTick++;
        if(damageTick % 25 == 0){
            //System.out.println(damageTick);
            if(this.beruehrt(welt.spielfigur) || this.beruehrt(welt.HealthBar)){
                if(welt.spielfigur.getvX() < 0){
                    //welt.spielfigur.setvX(0.2);
                    verschiebenUm(-1, 0);
                }else {
                    //welt.spielfigur.setvX(-0.2);
                    verschiebenUm(1, 0);
                }
                //welt.spielfigur.tasteLosgelassenReagieren(java.awt.event.KeyEvent.VK_D);
                welt.spielfigur.zieheLebenAb();
            }
        }
    }
    
}

