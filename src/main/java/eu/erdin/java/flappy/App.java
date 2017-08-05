package eu.erdin.java.flappy;


import eu.erdin.java.flappy.bot.FlappyBot;
import eu.erdin.java.flappy.view.GamePanel;

import javax.swing.*;

public class App {

    public static int WIDTH = 500;
    public static int HEIGHT = 500;

    public static void main(FlappyBot bot) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel(bot);
        frame.add(panel);
    }
}
