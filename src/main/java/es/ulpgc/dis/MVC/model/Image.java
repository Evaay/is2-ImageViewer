package es.ulpgc.dis.MVC.model;

public interface Image {
    String getImageName();
    Image next();
    Image prev();
}
