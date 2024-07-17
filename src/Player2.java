public class Player2 extends Traveler
{
    private int lastPressedMovementKey;
    private Waffe messer;
    
    public Player2(Welt weltneu)
    {
        super(weltneu);
        lastPressedMovementKey = 0;
    }
    
    @Override
    public void klickReagieren(double x, double y){
        return;
    }
    
    // Reaktion auf losgelassene Tasten
    @Override
    public void tasteLosgelassenReagieren(int code) {
        //----------------------------------------tasteLosgelassenReagieren-Methode----------------------------------------//
        if (code == TASTE.RECHTS2 || code == TASTE.LINKS2) {
            vX = v_idle;
            super.setzeZustand("idle");
        }
    }
    
    @Override
    public void tasteReagieren(int code) {
        //----------------------------------------tasteReagieren-Methode----------------------------------------//
        if (code == TASTE.RECHTS2) {
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
        } else if (code == TASTE.LINKS2) {
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
        } else if (code == TASTE.RUNTER2) {
            vX = v_idle;
            setzeZustand("run");
        } else if (code == TASTE.RAUF2) {
            springe(12);
            setzeZustand("jumpUp");
        }
        
        if(code == TASTE.RECHTS2)
            lastPressedMovementKey = TASTE.RECHTS2;
        else if(code == TASTE.LINKS2)
            lastPressedMovementKey = TASTE.LINKS2;
        else if(code == TASTE.RAUF2)
            lastPressedMovementKey = TASTE.RAUF2;
        else if(code == TASTE.RUNTER2)
            lastPressedMovementKey = TASTE.RUNTER2;
            
        if(code == TASTE.ENTER){
            messer = null;
            if(lastPressedMovementKey == TASTE.RECHTS2 || lastPressedMovementKey == TASTE.LINKS2)
                messer = new Waffe(welt, (lastPressedMovementKey == TASTE.RECHTS2 ? "right" : "left"));
            else if(lastPressedMovementKey == TASTE.RAUF2 || lastPressedMovementKey == TASTE.RUNTER2)
                messer = new Waffe(welt, (lastPressedMovementKey == TASTE.RAUF2 ? "up" : "down"));
        }
    }
    
    @Override
    public void tick(){
        if(welt.mode == "Single"){
            return;
        }
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
}
