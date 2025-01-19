package es.ulpgc.dis.MVC.view;

import es.ulpgc.dis.MVC.model.Image;

public interface ImageDisplay {
    void show(Image image);
    Image getCurrentImage();
}
