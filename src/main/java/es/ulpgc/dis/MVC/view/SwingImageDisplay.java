package es.ulpgc.dis.MVC.view;

import es.ulpgc.dis.MVC.io.ImageDeserializer;
import es.ulpgc.dis.MVC.model.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;
    private BufferedImage bitImage;

    @Override
    public void show(Image image) {
        this.image = image;
        this.bitImage = getBitImage(image);
        this.repaint();
    }

    private static BufferedImage getBitImage(Image image) {
        return new ImageDeserializer().deserialize(image.getImageName());
    }

    @Override
    public Image getCurrentImage() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        drawImage(g);
    }

    private void drawImage(Graphics g) {
        Resizer resizer = new Resizer(new Dimension(this.getWidth(), this.getHeight()));
        Dimension dimensionResized = resizer.resize(new Dimension(bitImage.getWidth(), bitImage.getHeight()));

        int x = (this.getWidth() - dimensionResized.width) / 2;
        int y = (this.getHeight() - dimensionResized.height) / 2;

        g.drawImage(bitImage, x, y, dimensionResized.width, dimensionResized.height, null);
    }

    public static class Resizer {
        private final Dimension containerSize;

        public Resizer(Dimension containerSize) {
            this.containerSize = containerSize;
        }

        public Dimension resize(Dimension imageSize) {
            double containerDimension = containerSize.getWidth() / containerSize.getHeight();
            double imageDimension = imageSize.getWidth() / imageSize.getHeight();

            if (containerDimension > imageDimension) {
                return fitWidth(containerSize.getHeight(), imageDimension);
            } else {
                return fitHeight(containerSize.getWidth(), imageDimension);
            }
        }
    }

    public static Dimension fitWidth(double containerHeight, double imageDimension) {
        double width = containerHeight * imageDimension;
        return new Dimension((int) width,(int) containerHeight);
    }

    public static Dimension fitHeight(double containerWidth, double imageDimension) {
        double height = containerWidth / imageDimension;
        return new Dimension((int) containerWidth, (int) height);

    }
}
