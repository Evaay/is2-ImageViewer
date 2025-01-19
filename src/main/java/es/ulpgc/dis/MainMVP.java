package es.ulpgc.dis;

import es.ulpgc.dis.MVP.PresenterControl.ImagePresenter;
import es.ulpgc.dis.MVP.io.FileImageLoader;
import es.ulpgc.dis.MVP.model.Image;
import es.ulpgc.dis.MVP.view.MainFrame;

import java.io.File;

public class MainMVP {
    public static final String ImagesPath = "src/main/resources/pictures";
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Image initialImage = new FileImageLoader(new File(ImagesPath)).load();
        ImagePresenter presenter = new ImagePresenter(mainFrame.getImageDisplay());
        presenter.show(initialImage);
        mainFrame.setVisible(true);
    }
}
