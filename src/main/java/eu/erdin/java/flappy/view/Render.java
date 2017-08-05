package eu.erdin.java.flappy.view;


import eu.erdin.java.flappy.Util;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;

public class Render {
    private int x;
    private int y;
    private Image image;
    private AffineTransform transform;
    private List<Label> labels = new LinkedList<>();

    public Render(){

    }

    public Render(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.image = Util.loadImage(imagePath);
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public AffineTransform getTransform() {
        return transform;
    }

    public void setTransform(AffineTransform transform) {
        this.transform = transform;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }
}
