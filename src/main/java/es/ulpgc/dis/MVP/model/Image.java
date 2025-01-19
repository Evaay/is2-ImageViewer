package es.ulpgc.dis.MVP.model;

public interface Image {
    String getImageName();
    Image next();
    Image prev();

}
