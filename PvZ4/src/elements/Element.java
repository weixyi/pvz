package elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Element {
    protected int positionX;
    protected int positionY;
    protected String path;
    protected ImageView img;
    protected int width;
    protected int height;

    public Element(int positionX, int positionY, String path, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;

        this.path = getClass().getResource(path).toString();
    }


    public Element(int positionX, int positionY) {
        // For SUN
        // MODIFIED LATER
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void makeImage(Pane pane){
        img = new ImageView();
        Image im=new Image(path,(double) width,(double) height,false,false);
        img.setImage(im);
        img.setX(this.positionX);
        img.setY(this.positionY);
        pane.getChildren().add(img);
    }

    public void disappear() {
        if (this.img==null) return;
        this.img.setVisible(false);
    }

    public abstract void action();


}
