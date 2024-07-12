import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public Welt spielwelt;
    meinSpiel()
    {
        super(1456,816,true);
        
        JFrame frame = new JFrame("Choose Gamemode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton button1 = new JButton("Single");
        JButton button2 = new JButton("Coop");
        JButton button3 = new JButton("Versus");

        centerFrame(frame);
        
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spielwelt = new Welt("Single");
                frame.dispose();
                try {
                    Thread.sleep(150);
                    setzeKamerafokus(spielwelt.hintergrund);
                }
                catch(Exception exception){
            
                }
                setzeErkundungsmodusAktiv(false);
                zeigeKoordinatensystem(false);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spielwelt = new Welt("Coop");
                frame.dispose();
                try {
                    Thread.sleep(150);
                    setzeKamerafokus(spielwelt.hintergrund);
                }
                catch(Exception exception){
            
                }
                setzeErkundungsmodusAktiv(false);
                zeigeKoordinatensystem(false);
            }
        });
        
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spielwelt = new Welt("Versus");
                frame.dispose();
                try {
                    Thread.sleep(150);
                    setzeKamerafokus(spielwelt.hintergrund);
                }
                catch(Exception exception){
            
                }
                setzeErkundungsmodusAktiv(false);
                zeigeKoordinatensystem(false);
            }
        });

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        frame.add(panel);

        frame.setVisible(true);
                
    }
    
    private static void centerFrame(JFrame frame) {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    int w = frame.getSize().width;
    int h = frame.getSize().height;
    int x = (dim.width - w) / 2;
    int y = (dim.height - h) / 2;
    frame.setLocation(x, y);
    }
    
}