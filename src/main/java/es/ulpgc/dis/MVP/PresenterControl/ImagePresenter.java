package es.ulpgc.dis.MVP.PresenterControl;

import es.ulpgc.dis.MVP.model.Image;
import es.ulpgc.dis.MVP.view.ImageDisplay;
import es.ulpgc.dis.MVP.view.ImageDisplay.Released;
import es.ulpgc.dis.MVP.view.ImageDisplay.Shift;

public class ImagePresenter {
    private final ImageDisplay display;
    private Image image;

    public ImagePresenter(ImageDisplay display) {
        this.display = display;
        this.display.on((Shift) this::shift);
        this.display.on((Released) this::released);
    }

    private void shift(int slideOffset) {
        display.clear();
        display.paint(image.getImageName(), slideOffset);
        if (slideOffset > 0)
            display.paint(image.prev().getImageName(), slideOffset - display.getWidth());
        else
            display.paint(image.next().getImageName(), display.getWidth() + slideOffset);
    }

    private void released(int slideOffset) {
        if (Math.abs(slideOffset) >= display.getWidth() / 2)
            this.image = slideOffset > 0 ? image.prev() : image.next();
        repaint();
    }

    public void show(Image image) {
        this.image = image;
        repaint();
    }

    private void repaint() {
        this.display.clear();
        this.display.paint(this.image.getImageName(), 0);
    }
}
