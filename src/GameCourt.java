package org.cis1200.FiregirlAndWaterboy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * GameCourt
 *
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */
public class GameCourt extends JPanel {

    // the state of the game logic

    private Waterboy waterboy;
    private Firegirl firegirl;
    private Level level;

    private boolean playing = false; // whether the game is running
    // private final JLabel status; // Current status text, i.e. "Running..."

    private JLabel wbGems;
    private JLabel fgGems;
    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 330;
    public static final int VELOCITY = 4;
    public static final int JUMP_FORCE = 7;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    private Timer timer;

    public GameCourt(JLabel wbGems, JLabel fgGems) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // The timer is an object which triggers an action periodically with the
        // given INTERVAL. We register an ActionListener with this timer, whose
        // actionPerformed() method is called each time the timer triggers. We
        // define a helper method called tick() that actually does everything
        // that should be done in a single time step.

        firegirl = new Firegirl(20, 260, COURT_WIDTH, COURT_HEIGHT);
        waterboy = new Waterboy(0, 260, COURT_WIDTH, COURT_HEIGHT);
        level = new Level(16, 15);

        try {
            level.loadLevel("files/level1.txt");
        } catch (IOException e) {
            System.out.println("Error loading level: " + e.getMessage());
        }

        timer = new Timer(INTERVAL, e -> tick());
        // timer.start(); // MAKE SURE TO START THE TIMER!

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);

        this.wbGems = wbGems;
        this.fgGems = fgGems;

        // This key listener allows the square to move as long as an arrow key
        // is pressed, by changing the square's velocity accordingly. (The tick
        // method below actually moves the square.)
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    waterboy.setVx(-VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    waterboy.setVx(VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    waterboy.setVy(VELOCITY * JUMP_FORCE);
                    waterboy.jump(level);

                }
            }

            public void keyReleased(KeyEvent e) {
                waterboy.setVx(0);

            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    firegirl.setVx(-VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    firegirl.setVx(VELOCITY);
                } else if (e.getKeyCode() == KeyEvent.VK_W) {
                    firegirl.setVy(VELOCITY * JUMP_FORCE);
                    firegirl.jump(level);
                }
            }

            public void keyReleased(KeyEvent e) {
                firegirl.setVx(0);
            }
        });

    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        timer.start();
        waterboy = new Waterboy(20, 260, COURT_WIDTH, COURT_HEIGHT);
        firegirl = new Firegirl(0, 260, COURT_WIDTH, COURT_HEIGHT);
        try {
            level.loadLevel("files/level1.txt");
        } catch (IOException e) {
            System.out.println("Error loading level: " + e.getMessage());
        }

        playing = true;
        // status.setText("Running...");

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    private void handleMovement(Player player) {
        if (player.canEnter(level)) {
            player.move();
        } else if (player.end()) {

            timer.stop();
            int response = JOptionPane.showConfirmDialog(
                    null, player.endMessage() +
                            "\nWaterboy's gems: " + waterboy.getGems() + "\nFiregirl's gems: "
                            + firegirl.getGems() +
                            "\nWould you like to play again? ",
                    "GAME END!", JOptionPane.YES_NO_OPTION
            );
            if (response == JOptionPane.YES_OPTION) {
                reset();
            }
        } else {
            player.setVx(0);
        }

        // if(!player.isGrounded(level)) {
        player.applyGravity(level);
        // }else{
        // player.setVy(0);

        // }

        // if(player.getVy()>0){
        // System.out.println("py: " + player.getPy() + ", maxPy: " +
        // player.getMaxPy());
        // }
    }

    /**
     * This method is called every time the timer defined in the constructor
     * triggers.
     */
    void tick() {
        repaint();
        handleMovement(firegirl);
        handleMovement(waterboy);
        fgGems.setText("firegirl's gems: " + firegirl.getGems());
        wbGems.setText("waterboy's gems: " + waterboy.getGems());

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        level.draw(g);
        waterboy.draw(g);
        firegirl.draw(g);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}