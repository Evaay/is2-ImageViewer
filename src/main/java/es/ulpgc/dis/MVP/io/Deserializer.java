package es.ulpgc.dis.MVP.io;

import java.awt.image.BufferedImage;

public interface Deserializer {
    BufferedImage deserialize(String name);
}
