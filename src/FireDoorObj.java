package org.cis1200.FiregirlAndWaterboy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FireDoorObj extends GameObj {
    public static final String IMG_FILE = "files/fire door.png";
    private static BufferedImage img;

    public FireDoorObj(int x, int y, int width, int height) {
        super(x, y, width, height, "T");
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);

        // g.setColor(Color.MAGENTA);
        // g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}
