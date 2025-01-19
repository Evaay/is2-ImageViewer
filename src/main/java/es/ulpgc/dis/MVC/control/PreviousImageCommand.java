package es.ulpgc.dis.MVC.control;

import es.ulpgc.dis.MVC.view.ImageDisplay;

public class PreviousImageCommand implements Command {
    private final ImageDisplay imageDisplay;

    public PreviousImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.show(imageDisplay.getCurrentImage().prev());
    }
}
