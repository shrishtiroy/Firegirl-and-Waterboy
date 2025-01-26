package org.cis1200.FiregirlAndWaterboy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WaterObj extends GameObj {
    public static final String IMG_FILE = "files/water.png";
    private static BufferedImage img;

    public WaterObj(int x, int y, int width, int height) {
        super(x, y, width, height, "W");
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

        // g.setColor(Color.BLUE);
        // g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}