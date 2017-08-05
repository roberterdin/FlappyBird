package eu.erdin.java.flappy;


import eu.erdin.java.flappy.view.Render;

import java.awt.Image;
import java.awt.geom.AffineTransform;

public class Bird {

    public int x = 100;
    public int y = 150;
    public int width = 45;
    public int height = 32;

    public boolean dead = false;

    public double yvel = 0;
    public double gravity = 0.5;

    public int jumpDelay = 0;
    private double rotation = 0.0;

    private Image image;


    public void update(boolean flap) {
        yvel += gravity;

        // bird can only flap every 10 steps
        if (jumpDelay > 0)
            jumpDelay--;

        if (!dead && flap && jumpDelay <= 0) {
            yvel = -10;
            jumpDelay = 10;
        }

        y += (int)yvel;
    }

    public Render getRender() {
        Render r = new Render();
        r.setX(x);
        r.setY(y);

        if (image == null) {
            image = Util.loadImage("lib/bird.png");     
        }
        r.setImage(image);

        rotation = (90 * (yvel + 20) / 20) - 90;
        rotation = rotation * Math.PI / 180;

        if (rotation > Math.PI / 2)
            rotation = Math.PI / 2;

        r.setTransform(new AffineTransform());
        r.getTransform().translate(x + width / 2, y + height / 2);
        r.getTransform().rotate(rotation);
        r.getTransform().translate(-width / 2, -height / 2);

        return r;
    }
}
