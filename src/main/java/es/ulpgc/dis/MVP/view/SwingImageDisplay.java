package es.ulpgc.dis.MVP.view;

import es.ulpgc.dis.MVP.io.ImageDeserializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Shift shift = Shift.Null;
    private Released released = Released.Null;
    private int initShift;
    private final List<bufferedImage> images = new ArrayList<>();
    private final Map<String, BufferedImage> imageCache = new HashMap<>();


    public SwingImageDisplay() {
        this.addMouseListener(mouseListener());
        this.addMouseMotionListener(mouseMotionListener());
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                initShift = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released.offset(e.getX() - initShift);
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }

    private MouseMotionListener mouseMotionListener() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initShift);
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        };
    }

    private record bufferedImage(BufferedImage image, int offset) {}

    @Override
    public void paint(String imageName, int offset) {
        images.add(new bufferedImage(getBufferedImage(imageName), offset));
        repaint(offset, 0, getWidth(), getHeight());
    }

    @Override
    public void clear() {
        images.clear();
    }

    @Override
    public void paint(Graphics g) {
        for (bufferedImage image : images) {
            g.drawImage(image.image(), image.offset(), 0, null);
        }
    }

    private BufferedImage getBufferedImage(String imageName) {
        if (!imageCache.containsKey(imageName)) {
            BufferedImage image = new ImageDeserializer().deserialize(imageName);
            imageCache.put(imageName, image);
        }
        return imageCache.get(imageName);
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift != null ? shift : Shift.Null;
    }

    @Override
    public void on(Released released) {
        this.released = released != null ? released : Released.Null;
    }
}
