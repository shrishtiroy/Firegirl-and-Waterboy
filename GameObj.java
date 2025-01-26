package org.cis1200.FiregirlAndWaterboy;

import java.awt.*;

/**
 * An object in the game.
 *
 * Game objects exist in the game court. They have a position, velocity, size
 * and bounds. Their velocity controls how they move; their position should
 * always be within their bounds.
 */
public abstract class GameObj {
    /*
     * Current position of the object (in terms of graphics coordinates)
     *
     * Coordinates are given by the upper-left hand corner of the object. This
     * position should always be within bounds:
     * 0 <= px <= maxX 0 <= py <= maxY
     */
    private int px;
    private int py;
    private String type;

    /* Size of object, in pixels. */
    private final int width;
    private final int height;

    /* Velocity: number of pixels to move every time move() is called. */
    private int vx;
    private int vy;
    // private boolean isJumping = false;

    /*
     * Upper bounds of the area in which the object can be positioned. Maximum
     * permissible x, y positions for the upper-left hand corner of the object.
     */
    private final int maxX;
    private final int maxY;

    /**
     * Constructor
     */
    public GameObj(
            int vx, int vy, int px, int py, int width, int height, int courtwidth,
            int courtheight, String type
    ) {
        this.vx = vx;
        this.vy = vy;
        this.px = px;
        this.py = py;
        this.width = width;
        this.height = height;
        this.type = type;

        // take the width and height into account when setting the bounds for
        // the upper left corner of the object.
        this.maxX = courtwidth - width;
        this.maxY = courtheight - height;
    }

    public GameObj(int px, int py, int width, int height, String type) {
        this.px = px;
        this.py = py;
        this.width = width;
        this.height = height;
        this.type = type;
        maxX = 0;
        maxY = 0;
    }

    // **********************************************************************************
    // * GETTERS
    // **********************************************************************************
    public int getPx() {
        return this.px;
    }

    public int getPy() {
        return this.py;
    }

    public int getVx() {
        return this.vx;
    }

    public int getVy() {
        return this.vy;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getType() {
        return this.type;
    }

    // **************************************************************************
    // * SETTERS
    // **************************************************************************
    public void setPx(int px) {
        this.px = px;
        clip();
    }

    public void setPy(int py) {
        this.py = py;
        clip();
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }

    // **************************************************************************
    // * UPDATES AND OTHER METHODS
    // **************************************************************************

    /**
     * Prevents the object from going outside the bounds of the area
     * designated for the object (i.e. Object cannot go outside the active
     * area the user defines for it).
     */
    public void clip() {
        this.px = Math.min(Math.max(this.px, 0), this.maxX);
        this.py = Math.min(Math.max(this.py, 0), this.maxY);
    }

    /**
     * Moves the object by its velocity. Ensures that the object does not go
     * outside its bounds by clipping.
     */

    public void move() {
        this.px += this.vx;
        clip();
    }

    // public void jump(){
    // if(!isJumping){
    // this.py -= this.vy;
    // isJumping = true;
    // clip();
    // }
    // }

    /**
     * Determine whether this game object is currently intersecting another
     * object.
     *
     * Intersection is determined by comparing bounding boxes. If the bounding
     * boxes overlap, then an intersection is considered to occur.
     *
     * @param that The other object
     * @return Whether this object intersects the other object.
     */
    public boolean intersects(GameObj that) {
        return (this.px + this.width >= that.px
                && this.py + this.height >= that.py
                && that.px + that.width >= this.px
                && that.py + that.height >= this.py);
    }

    public boolean intersectsBottom(GameObj that) {
        return (Math.abs(this.py + this.height - that.getPy()) < 1
                && this.px + this.width >= that.getPx()
                && this.px <= that.getPx() + that.getWidth());
    }

    public boolean collidesRight(GameObj that) {
        return (this.px + this.width >= that.getPx()
                && this.px < that.getPx()
                && this.py + this.height > that.getPy()
                && this.py < that.getPy() + that.getHeight());
    }

    public boolean collidesLeft(GameObj that) {
        return (this.px <= that.getPx() + that.getWidth()
                && this.px + this.getWidth() > that.getPx() + that.getWidth()
                && this.py + this.height > that.getPy()
                && this.py < that.getPy() + that.getHeight());

    }

    public boolean willCollideBottom(GameObj that) {
        int thisNextX = this.px + this.vx;
        return (Math.abs(that.getPy() - (this.py + this.height)) < 1
                && thisNextX + this.width > that.getPx()
                && this.px < that.getPx() + that.getWidth());
    }

    public boolean willCollideRight(GameObj that) {
        int thisNextX = this.px + this.vx;
        int thisNextY = this.py + this.vy;
        return (thisNextX + this.width > that.getPx()
                && this.px < that.getPx()
                && (thisNextY + this.height) > that.getPy()
                && thisNextY < (that.getPy() + that.getHeight()));
    }

    public boolean willCollideLeft(GameObj that) {
        int thisNextX = this.px + this.vx;
        int thisNextY = this.py + this.vy;
        return (thisNextX < that.getPx() + that.getWidth()
                && this.px > that.getPx()
                && thisNextY + this.height > that.getPy()
                && thisNextY < that.getPy() + that.getHeight());
    }

    public boolean willCollideTop(GameObj that) {
        int thisNextY = this.py;
        int thisNextX = this.px + this.vx;
        return (this.py - (that.getPy() + that.getHeight()) < 1
                && this.py + this.getHeight() >= that.getPy() + that.getHeight()
                && thisNextX + this.width > that.getPx()
                && thisNextX < that.getPx() + that.getWidth());
    }

    /**
     * Determine whether this game object will intersect another in the next
     * time step, assuming that both objects continue with their current
     * velocity.
     *
     * Intersection is determined by comparing bounding boxes. If the bounding
     * boxes (for the next time step) overlap, then an intersection is
     * considered to occur.
     *
     * @param that The other object
     * @return Whether an intersection will occur.
     */
    public boolean willIntersect(GameObj that) {
        int thisNextX = this.px + this.vx;
        int thisNextY = this.py + this.vy;
        int thatNextX = that.px + that.vx;
        int thatNextY = that.py + that.vy;

        return (thisNextX + this.width >= thatNextX
                && thisNextY + this.height >= thatNextY
                && thatNextX + that.width >= thisNextX
                && thatNextY + that.height >= thisNextY);
    }

    /**
     * Default draw method that provides how the object should be drawn in the
     * GUI. This method does not draw anything. Subclass should override this
     * method based on how their object should appear.
     *
     * @param g The <code>Graphics</code> context used for drawing the object.
     *          Remember graphics contexts that we used in OCaml, it gives the
     *          context in which the object should be drawn (a canvas, a frame,
     *          etc.)
     */
    public abstract void draw(Graphics g);
}