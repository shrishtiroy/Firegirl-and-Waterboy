package org.cis1200;

import org.cis1200.FiregirlAndWaterboy.RunFiregirlAndWaterboy;

import javax.swing.*;

public class Game {
    /**
     * Main method run to start and run the game. Initializes the runnable game
     * class of your choosing and runs it. IMPORTANT: Do NOT delete! You MUST
     * include a main method in your final submission.
     */
    public static void main(String[] args) {
        // Set the game you want to run here
        JOptionPane.showMessageDialog(
                null,
                "HOW TO PLAY\n" +
                        "this is not fireboy and watergirl, this is their " +
                        "evil FRATERNAL twins that are arch nemeses\n"
                        +
                        "\n" +
                        "MOVEMENT: \n" +
                        "FIREGIRL: A, D left and right, W to jump\n" +
                        "WATERBOY: arrow keys to move left and right, up arrow to jump\n" +
                        "POWERUPS: \n" +
                        "FIREGIRL POWERUP: for every other gem you " +
                        "collect you leave a fiery trail behind\n"
                        +
                        "WATERBOY POWERUP: if you collect 5 gems watch all the fire turn to water\n"
                        +
                        "LIMITATIONS: \n" +
                        "FIREGIRL: you cannot move in the dark blue you will die\n" +
                        "WATERBOY: you cannot move in the red you will die" +
                        "\n" +
                        "OBJECTIVE:\n" +
                        "FIREGIRL: get to the fire door before waterboy\n" +
                        "WATERBOY: get to the water door before firegirl\n" +

                        "ARE YOU READY TO BATTLE?",
                "FIREGIRL AND WATERBOY", JOptionPane.INFORMATION_MESSAGE
        );
        Runnable game = new RunFiregirlAndWaterboy();

        SwingUtilities.invokeLater(game);
    }
}
