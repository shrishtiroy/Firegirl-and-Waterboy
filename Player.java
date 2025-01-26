package org.cis1200.FiregirlAndWaterboy;

public abstract class Player extends GameObj {
    private int gems;
    private boolean isJumping = false;
    private boolean gameEnd = false;
    private String endMsg = "";
    private int maxPy;

    public Player(
            String type, int vx, int vy, int px, int py, int width, int height, int courtWidth,
            int courtHeight
    ) {
        super(vx, vy, px, py, width, height, courtWidth, courtHeight, type);

    }

    /**
     * checks if firegirl is touching fire, if waterboy is touching water,
     * if the players are touching coins, or if they are grounded
     * 
     * @param level
     * @return
     */
    public boolean canEnter(Level level) {
        // 'F' for fire, 'W' for water
        boolean canMove = false;
        for (GameObj[] g1 : level.getGrid()) {
            for (GameObj g : g1) {
                if (g != null) {
                    if (g.getType().equals("F") || g.getType().equals("W")) {
                        if (this.willCollideBottom(g)) {
                            return checkValidMove(g);
                        }
                    }
                    if (getVy() > 0 && this.willCollideTop(g)) {
                        switch (g.getType()) {
                            case "G" -> {
                                maxPy = g.getPy() + g.getHeight();
                            }
                            case "W", "F" -> {
                                return checkValidMove(g);
                            }
                            case "C" -> {
                                whenGem(g.getPx(), g.getPy(), level);
                                incGems();

                                return true;
                            }
                            default -> {
                                continue;
                            }
                        }
                    }
                    if ((this.getVx() > 0 && this.willCollideRight(g)) ||
                            (this.getVx() < 0 && this.willCollideLeft(g))) {
                        switch (g.getType()) {
                            case "W", "F", "Q", "T" -> {
                                return checkValidMove(g);
                            }
                            case "C" -> {
                                whenGem(g.getPx(), g.getPy(), level);
                                incGems();

                                return true;
                            }
                            case "G" -> {
                                return false;
                            }
                            case "S" -> {
                                if (isGrounded(level)) {
                                    return true;
                                }
                                if (isJumping) {
                                    return true;
                                }
                            }
                            default -> {
                                continue;
                            }
                        }
                    }

                }
            }
        }

        return canMove;

    }

    /**
     * checks if the player is grounded
     * 
     * @param level
     * @return
     */
    public boolean isGrounded(Level level) {
        for (GameObj[] g1 : level.getGrid()) {
            for (GameObj g : g1) {
                if (g != null) {
                    if (g.getType().equals("G") && this.intersectsBottom(g)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void clipJump(GameObj g) {
        this.setPy(Math.max(this.getPy(), g.getPy() + g.getHeight()));
    }

    /**
     * lets the player jump
     * 
     * @param level
     */
    public void jump(Level level) {
        if (isGrounded(level) && !isJumping) {
            this.setPy(Math.max((this.getPy() - this.getVy()), maxPy));
            maxPy = 0;
            isJumping = true;
            clip();
        }

    }

    /**
     * applies gravity to the player when it is not grounded
     * 
     * @param level
     */
    public void applyGravity(Level level) {
        if (!isGrounded(level)) {
            this.setPy(this.getPy() + 1);
        }
        if (isGrounded(level)) {
            isJumping = false;
            setVy(0);
        }
    }

    public void whenGem(int gPx, int gPy, Level level) {
        level.collectGem(gPx, gPy, 'S');
    }

    public boolean checkValidMove(GameObj g) {
        return true;
    }

    public int getGems() {
        return gems;
    }

    public void incGems() {
        this.gems++;
    }

    public boolean end() {
        return gameEnd;
    }

    public void setGameEnd(boolean e) {
        gameEnd = e;
    }

    public String endMessage() {
        return endMsg;
    }

    public void setEndMsg(String endMsg) {
        this.endMsg = endMsg;
    }

    public void setMaxPy(int maxPy) {
        this.maxPy = maxPy;
    }

    public int getMaxPy() {
        return maxPy;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
}
