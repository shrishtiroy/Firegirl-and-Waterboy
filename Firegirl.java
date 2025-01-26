package org.cis1200.FiregirlAndWaterboy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Firegirl extends Player {
    public static final String IMG_FILE = "files/firegirl.png";
    private static BufferedImage img;

    public Firegirl(int px, int py, int courtWidth, int courtHeight) {
        super("Firegirl", 0, 0, px, py, 20, 20, courtWidth, courtHeight);
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    @Override
    public void whenGem(int gPx, int gPy, Level level) {
        if (getGems() > 0 && getGems() % 2 == 1) {
            level.collectGem(gPx, gPy, 'F');
        } else {
            level.collectGem(gPx, gPy, 'S');
        }
    }

    @Override
    public boolean checkValidMove(GameObj g) {
        if (g.getType().equals("W")) {
            setGameEnd(true);
            setEndMsg("Firegirl drowned! #loser\nWaterboy wins!");
            return false;
        } else if (g.getType().equals("T")) {
            setGameEnd(true);
            setEndMsg("FIREGIRL FOR DA WIN!!!");
        }
        return true;

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);

        // g.setColor(Color.ORANGE);
        // g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}
