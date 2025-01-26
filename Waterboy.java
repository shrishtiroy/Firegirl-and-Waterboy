package org.cis1200.FiregirlAndWaterboy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Waterboy extends Player {
    public static final String IMG_FILE = "files/waterboy.png";
    private static BufferedImage img;

    public Waterboy(int px, int py, int courtWidth, int courtHeight) {
        super(
                "Waterboy", 0, 0, px, py, 20, 20,
                courtWidth, courtHeight
        );
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    /**
     * waterboy's powerup is when he gets 4 gems then all the fire tiles change to
     * water
     */
    public void whenGem(int gPx, int gPy, Level level) {
        super.whenGem(gPx, gPy, level);

        if (getGems() == 4) {
            GameObj[] fireList = level.getFireList();
            for (GameObj f : fireList) {
                int j = f.getPx() / 20;
                int i = f.getPy() / 20;
                level.getGrid()[i][j] = new WaterObj(f.getPx(), f.getPy(), 20, 20);
            }
        }

    }

    @Override
    public boolean checkValidMove(GameObj g) {
        if (g.getType().equals("F")) {
            setGameEnd(true);
            setEndMsg("Waterboy got too lit in fire! #fail\nFiregirl wins!");
            return false;
        } else if (g.getType().equals("Q")) {
            setGameEnd(true);
            setEndMsg("WATERBOY FOR DA WIN!!");
        }
        return true;

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img, this.getPx(), this.getPy(), this.getWidth(), this.getHeight(), null);

        // g.setColor(Color.GREEN);
        // g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}
