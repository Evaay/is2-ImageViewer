package es.ulpgc.dis.MVC.io;

import es.ulpgc.dis.MVC.model.Image;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;

public class FileImageLoader implements ImageLoader {
    private final File[] files;

    public FileImageLoader(File ImagesFolder) {
        this.files = ImagesFolder.listFiles(isImage());
    }

    private static final Set<String> imageExtensions = Set.of(".jpg", ".png");

    private static FilenameFilter isImage() {
        return (pathImage, nameImage) -> imageExtensions.stream().anyMatch(nameImage.toLowerCase()::endsWith);
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String getImageName() {
                return files != null ? files[i].getAbsolutePath() : null;
            }

            @Override
            public Image next() {
                return imageAt((i+1) % files.length);
            }

            @Override
            public Image prev() {
                return imageAt((i-1+ files.length) % files.length);
            }
        };
    }
}
