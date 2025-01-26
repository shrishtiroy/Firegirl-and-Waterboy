package org.cis1200.FiregirlAndWaterboy;

// imports necessary libraries for Java swing

import javax.swing.*;
import java.awt.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class RunFiregirlAndWaterboy implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for
        // local variables.

        // Top-level frame in which game components live.
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Firegirl and Waterboy");
        frame.setLocation(500, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        status_panel.setLayout(new BoxLayout(status_panel, BoxLayout.Y_AXIS));
        frame.add(status_panel, BorderLayout.SOUTH);

        JLabel wbGems = new JLabel();
        wbGems.setAlignmentX(Component.CENTER_ALIGNMENT);
        status_panel.add(wbGems);
        JLabel fgGems = new JLabel();
        fgGems.setAlignmentX(Component.CENTER_ALIGNMENT);
        status_panel.add(fgGems);

        // Main playing area
        final GameCourt court = new GameCourt(wbGems, fgGems);
        frame.add(court, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> court.reset());
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }
}