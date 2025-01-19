package es.ulpgc.dis;

import es.ulpgc.dis.MVC.control.NextImageCommand;
import es.ulpgc.dis.MVC.control.PreviousImageCommand;
import es.ulpgc.dis.MVC.io.FileImageLoader;
import es.ulpgc.dis.MVC.model.Image;
import es.ulpgc.dis.MVC.view.MainFrame;

import java.io.File;

public class MainMVC {
    public static final String ImagesPath = "src/main/resources/pictures";
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Image image = new FileImageLoader(new File(ImagesPath)).load();
        mainFrame.initMainFrame(image)
                .add("<", new PreviousImageCommand(mainFrame.imageDisplay()))
                .add(">", new NextImageCommand(mainFrame.imageDisplay()))
                .setVisible(true);
    }
}
