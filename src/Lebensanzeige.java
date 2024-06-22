public class Lebensanzeige 

extends     FIGUR
implements  Ticker
{
    Welt welt;
    
    Lebensanzeige(Welt weltneu)
    {
        // Zustand "idle"
        super( "idle" , "health3.gif" );
        
        super.setzeMittelpunkt(0,0);
        super.machePassiv();
        
        super.fuegeZustandVonGifHinzu( "3_Leben", "health3.gif" );
        super.setzeAnimationsgeschwindigkeit( "3_Leben", 1 );
                                            
        super.fuegeZustandVonGifHinzu( "2_Leben", "health2.gif" );
        super.setzeAnimationsgeschwindigkeit( "2_Leben", 1 );
        
        super.fuegeZustandVonGifHinzu( "1_Leben", "health1.gif" );
        super.setzeAnimationsgeschwindigkeit( "1_Leben", 1 );
 
        super.fuegeZustandVonGifHinzu( "0_Leben", "health0.gif" );
        super.setzeAnimationsgeschwindigkeit( "0_Leben", 1 );
        
        starteTickerNeu( 0.04 );
        
        welt = weltneu;
        
        skaliere(1.5);
        
        setzeEbene(1);
    }
    
    @Override
    public void tick()  
    {
        this.setzePosition(welt.spielfigur.nennePosition().getX(), welt.spielfigur.nennePosition().getY()+3);
        int TravelerHealth = welt.spielfigur.getHealth(); 
        if(TravelerHealth >= 3){
            setzeZustand("3_Leben");
        }else if(TravelerHealth == 2){
            setzeZustand("2_Leben");
        }else if(TravelerHealth == 1){
            setzeZustand("1_Leben");
        }else if(TravelerHealth <= 0){
            setzeZustand("0_Leben");
        }
    }
}

