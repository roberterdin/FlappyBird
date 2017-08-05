package eu.erdin.java.flappy;

/**
 * @author robert.erdin@gmail.com
 */
public class FlappyState {
    // bird
    private final int x; // always the same
    private final int y;

    // the x,y
    private int[][] pipes;

    public FlappyState(int x, int y, int[][] pipes){
        this.x = x;
        this.y = y;
        this.pipes = pipes;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getPipes() {
        return pipes;
    }
}
