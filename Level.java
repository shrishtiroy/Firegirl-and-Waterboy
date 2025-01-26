package org.cis1200.FiregirlAndWaterboy;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Level {
    // used to initialize grid from file
    private GameObj[][] grid;// 2D array: 'F' for fire, 'W' for water, 'G' for ground, 'S' for sky,
                             // 'C' for gems
    private ArrayList<GameObj> fireList = new ArrayList<GameObj>();

    public Level(int rows, int cols) {
        grid = new GameObj[rows][cols];

    }

    public void loadLevel(String levelFilePath) throws IOException {
        // Read level layout from a file
        char[][] gridChar = new char[grid.length][grid[0].length];

        try (BufferedReader reader = new BufferedReader(new FileReader(levelFilePath))) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                gridChar[i] = line.toCharArray();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < gridChar.length; i++) {
            for (int j = 0; j < gridChar[i].length; j++) {
                char tile = gridChar[i][j];
                if (tile == 'F') {
                    GameObj f = new FireObj(j * 20, i * 20, 20, 20);
                    grid[i][j] = f;
                    fireList.add(f);
                } else if (tile == 'W') {
                    GameObj w = new WaterObj(j * 20, i * 20, 20, 20);
                    grid[i][j] = w;
                } else if (tile == 'G') {
                    GameObj gr = new GroundObj(j * 20, i * 20, 20, 20);
                    grid[i][j] = gr;
                } else if (tile == 'C') {
                    GameObj c = new CoinObj(j * 20, i * 20, 20, 20);
                    grid[i][j] = c;
                } else if (tile == 'S') {
                    GameObj s = new SkyObj(j * 20, i * 20, 20, 20);
                    grid[i][j] = s;
                } else if (tile == 'Q') {
                    GameObj q = new WaterDoorObj(j * 20, i * 20, 20, 20);
                    grid[i][j] = q;
                } else if (tile == 'T') {
                    GameObj t = new FireDoorObj(j * 20, i * 20, 20, 20);
                    grid[i][j] = t;
                }
            }
        }
    }

    public GameObj[][] getGrid() {
        return grid;
    }

    public GameObj[] getFireList() {
        return fireList.toArray(new GameObj[fireList.size()]);
    }

    public void collectGem(int px, int py, char tile) {
        if (tile == 'F') {
            grid[py / 20][px / 20] = new FireObj(px, py, 20, 20);

        } else {
            grid[py / 20][px / 20] = new SkyObj(px, py, 20, 20);

        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    grid[i][j].draw(g);
                }
            }
        }
    }

}
