import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class meinSpiel extends SPIEL {
    public Welt spielwelt;
    private JLabel scoreLabel;
    private meinSpiel meinSpiel;
    public ScoreManager scoreManager;

    meinSpiel() {
        super(1456, 816, true);

        meinSpiel = this;
        
        scoreManager = new ScoreManager();
        
        JFrame frame = new JFrame("Choose Gamemode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton button1 = new JButton("Single");
        JButton button2 = new JButton("Coop");
        JButton button3 = new JButton("Versus");
        JButton button4 = new JButton("Tutorial");

        JLabel tutorial = new JLabel("<html>Player 1:<br>Du kannst dich mit WASD bewegen<br>Klicke Gegner mit der Maus an um sie zu töten.<br>Dieser Angriff hat eine kleinere Reichweite<br>Player 2:<br>Du kannst dich mit den Pfeiltasten bewegen<br>Mit ENTER wirfst du Messer in die letzte Richtung in diee du dich bewegt hast.<br>Der Angriff geht sehr weit, ist aber schwer richtig einzusetzen.</html>");
        tutorial.setVisible(false);

        scoreLabel = new JLabel("High Score: Loading...");
        updateScoreLabel();

        centerFrame(frame);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spielwelt = new Welt("Single", meinSpiel);
                frame.dispose();
                try {
                    Thread.sleep(150);
                    setzeKamerafokus(spielwelt.hintergrund);
                } catch (Exception exception) {
                }
                setzeErkundungsmodusAktiv(false);
                zeigeKoordinatensystem(false);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spielwelt = new Welt("Coop", meinSpiel);
                frame.dispose();
                try {
                    Thread.sleep(150);
                    setzeKamerafokus(spielwelt.hintergrund);
                } catch (Exception exception) {
                }
                setzeErkundungsmodusAktiv(false);
                zeigeKoordinatensystem(false);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spielwelt = new Welt("Versus", meinSpiel);
                frame.dispose();
                try {
                    Thread.sleep(150);
                    setzeKamerafokus(spielwelt.hintergrund);
                } catch (Exception exception) {
                }
                setzeErkundungsmodusAktiv(false);
                zeigeKoordinatensystem(false);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tutorial.setVisible(true);
                frame.setSize(new Dimension(500, 300)); // Increase the size to fit the tutorial
                frame.revalidate();
                frame.repaint();
            }
        });

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(tutorial);
        panel.add(scoreLabel);

        frame.add(panel);

        frame.setVisible(true);
    }

    private void updateScoreLabel() {
        int score = ScoreManager.getScore();
        if (score == Integer.MIN_VALUE) {
            scoreLabel.setText("High Score: Not available");
        } else {
            scoreLabel.setText("High Score: " + score);
        }
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
