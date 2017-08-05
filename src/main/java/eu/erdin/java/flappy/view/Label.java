package eu.erdin.java.flappy.view;

/**
 * @author robert.erdin@gmail.com
 *         created on 16/07/17.
 */
public class Label {
    private String text;
    private int x;
    private int y;

    public Label(String text, int x, int y){
        this.text = text;
        this.x = x;
        this.y = y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
