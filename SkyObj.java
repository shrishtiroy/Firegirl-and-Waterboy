package org.cis1200.FiregirlAndWaterboy;

import java.awt.*;

public class SkyObj extends GameObj {

    public SkyObj(int x, int y, int width, int height) {
        super(x, y, width, height, "S");
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.CYAN);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}
