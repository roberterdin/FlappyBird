package eu.erdin.java.flappy.view;


import eu.erdin.java.flappy.Game;
import eu.erdin.java.flappy.bot.FlappyBot;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private Game game;

    public GamePanel(FlappyBot bot) {
        game = new Game(bot);
        new Thread(this).start();
    }

    public void update() {
        game.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();

        Graphics2D g2D = (Graphics2D) g;
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        for (Render r : game.getRenders()) {
            if (r.getTransform() != null) {
                g2D.drawImage(r.getImage(), r.getTransform(), null);
                r.getLabels().forEach(l -> g2D.drawString(l.getText(),l.getX(),l.getY()));
            } else {
                g.drawImage(r.getImage(), r.getX(), r.getY(), null);
                r.getLabels().forEach(l -> g.drawString(l.getText(),l.getX(),l.getY()));
            }
        }

        g2D.setColor(Color.BLACK);

        if (!game.started) {
            g2D.drawString("Press SPACE to start", 150, 240);
        } else {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            g2D.drawString(Integer.toString(game.score), 10, 465);
            g2D.drawString("y: " + game.getBird().y, 10, 50);
        }

        if (game.gameover) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2D.drawString("Press R to restart", 150, 240);
        }
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(25);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
