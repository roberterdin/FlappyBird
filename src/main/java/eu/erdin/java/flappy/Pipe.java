package eu.erdin.java.flappy;


import eu.erdin.java.flappy.view.Label;
import eu.erdin.java.flappy.view.Render;

import java.awt.Image;
import java.util.Random;

/**
 * The gap between pipes is always 175px
 */
public class Pipe {

    private static Random RANDOM = new Random();

    public static final String SOUTH = "south";
    public static final String NORTH = "north";
    public static final int HEIGHT = 400;

    public int x;
    public int y;
    public int width;
    public int height;
    public int speed = 3;

    public String orientation;

    private Image image;

    public Pipe(String orientation) {
        this.orientation = orientation;
        reset();
    }

    public void reset() {
        width = 66;
        height = 400;
        x = App.WIDTH + 2;

        if (orientation.equals(SOUTH)) {
            y = -(int)(Math.random() * 120) - height / 2;
        }
    }

    public void update() {
        x -= speed;
    }

    public boolean collides(int _x, int _y, int _width, int _height) {

        int margin = 0;

        if (_x + _width - margin > x && _x + margin < x + width) {
            if (orientation.equals(SOUTH) && _y < y + height) {
                return true;
            } else if (orientation.equals(NORTH) && _y + _height > y) {
                return true;
            }
        }

        return false;
    }


    public Render getRender() {
        Render r = new Render();
        r.setX(x);
        r.setY(y);

        if (image == null) {
            image = Util.loadImage("lib/pipe-" + orientation + ".png");
        }
        r.setImage(image);
        r.getLabels().add(getLabel());

        return r;
    }

    private Label getLabel(){
        if (orientation.equals(SOUTH)){
            return new Label(x + "/" + y, x + 10, y + height + 15);
        }
        return new Label(x + "/" + y, x + 10, y - 10);
    }
}
