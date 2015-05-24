/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkywayGIU.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import milkyway_logic.gameplanner.Game;
import milkyway_logic.states.*;
import util.Constants;

/**
 *
 * @author Diogo
 */
public class GamePanel extends JPanel implements Observer {

    GameGrid grid;
    Game game;

    public GamePanel(Game game) {
        this.game = game;
        setLayout(new BorderLayout());
        setupComponents();
        setupLayout();
        registerObservers();
    }

    private void setupComponents() {
        grid = new GameGrid(game);
    }

    private void setupLayout() {
        JPanel centro = new JPanel();
        centro.setLayout(new BorderLayout());
        centro.add(grid, BorderLayout.NORTH);

        add(centro, BorderLayout.WEST);
        validate();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);

    }

    private void registerObservers() {
        grid.registerObservers();
    }

    class GameCell extends JPanel implements Observer {

        int row, col;
        Game game;
        String name_planet;

        GameCell(Game j, int r, int c, String name) {
            row = r;
            col = c;
            this.game = j;
            name_planet = name;

            setPreferredSize(new Dimension(80, 80));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent ev) {
                    System.out.println(game.getState());
                    if (game.getState() instanceof Move) {
                        game.move(row, col);
                        System.out.println("CLICK!");
                    }
                }
            });
        }

        @Override
        public void paintBorder(Graphics g) {
            super.paintBorder(g);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        @Override
        public void paintComponent(Graphics g) {
            try {
                drawIcon(g, name_planet);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void update(Observable o, Object arg) {
            repaint();
        }

        private void drawIcon(Graphics g, String name) throws IOException {
            Image img = getCardImage();
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }

        private Image getCardImage() throws IOException {
            if (!name_planet.equals("null") && name_planet != null) {
                System.out.println("nome -> " + name_planet);
                Image img = ImageIO.read(getClass().getResource("/images/" + name_planet + ".png"));
                this.name_planet = "";
                return img;
            } else {
                Image img = ImageIO.read(getClass().getResource("/images/Null.png"));
                this.name_planet = "";
                return img;
            }
        }
    };

    final class GameGrid extends JPanel {

        Game game;
        ArrayList<GameCell> cells = new ArrayList<>();

        GameGrid(Game j) {

            game = j;
            System.out.println(this + " " + game);
            setupLayout();

        }

        void registerObservers() {
            for (GameCell cell : cells) {
                game.addObserver(cell);
            }

        }

        void setupLayout() {
            GameCell cell;
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            for (int i = 0; i < 7; i++) {
                JPanel p = new JPanel();
                for (int j = 0; j < 9; j++) {
                    System.out.println("Pos (" + i + "," + j + ") -> " + game.getBoard()[i][j]);
                    if (game.getBoard()[i][j] != null) {
                        cell = new GameCell(game, i, j, game.getBoard()[i][j].getPlanetName());
                        if (!game.getBoard()[i][j].getIsTurned()) {
                            cell = new GameCell(game, i, j, "card_down");
                        }

                    } else {
                        cell = new GameCell(game, i, j, "null");
                    }
                    cells.add(cell);
                    p.add(cell);
                }
                add(p);
            }

        }
    };

}
