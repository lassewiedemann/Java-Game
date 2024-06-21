/**import java.awt.*;
import javax.swing.*;

public class HealthBar
extends Traveler

{
    private int leben;
    private int maxLeben;
    private Image healthBar;
    private Traveler Traveler;

    public HealthBar(int maxLeben, String imagePath) {
        this.maxLeben = maxLeben;
        this.leben = maxLeben;
        this.healthBar = new ImageIcon("gegnerHealth3.png").getImage();
        this.Traveler = Traveler;
        
    }

    public void draw(Graphics g) {
        for (int i = 0; i < leben; i++) {
            g.drawImage(healthBar, 20 + i * 35, 20, null);
        }
    }

    public void loseLife() {
        if (leben > 0) {
            leben--;
        }
    }

    public int getLives() {
        return leben;
    }
}**/