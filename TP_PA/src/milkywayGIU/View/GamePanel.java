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
import milkywayGIU.Model.Model;
import milkyway_logic.gameplanner.Game;
import milkyway_logic.states.*;
import util.Constants;

/**
 *
 * @author Diogo
 */
public class GamePanel extends JPanel implements Observer {

    GameGrid grid;
    Model model;

    public GamePanel(Game game) {

    }

    GamePanel(Model model) {
        this.model = model;
        model.addObserver(this);
        setLayout(new BorderLayout());
        setupComponents();
        setupLayout();
        registerObservers();
    }

    private void setupComponents() {
        grid = new GameGrid(model);
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
        Model model;
        String name_planet;

        GameCell(Model j, int r, int c, String name) {
            row = r;
            col = c;
            this.model = j;
            name_planet = name;

            setPreferredSize(new Dimension(80, 80));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent ev) {
                    System.out.println(model.getState());
                    if (model.getState() instanceof Move) {
                        model.move(row, col);
                        System.out.println("Pos Spaceship(" + model.getPlayer().getSpaceship().getPosX() + "," + model.getPlayer().getSpaceship().getPosY() + ")");
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
            Image img = getCardImage(name);
            Image img_nave = getNave();
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }

        private Image getNave() throws IOException {
            Image img = ImageIO.read(getClass().getResource("/images/Spaceship_v2.png"));
            return img;
        }

        private Image getCardImage(String name_planet) throws IOException {
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

        Model model;
        ArrayList<GameCell> cells = new ArrayList<>();

        GameGrid(Model m) {

            model = m;
            model.constructGame();
            setupLayout();

        }

        void registerObservers() {
            for (GameCell cell : cells) {
                model.addObserver(cell);
            }

        }

        void setupLayout() {
            GameCell cell;
            int x_spaceship = model.getPlayer().getSpaceship().getPosX();
            int y_spaceship = model.getPlayer().getSpaceship().getPosY();
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            for (int i = 0; i < 7; i++) {
                JPanel p = new JPanel();
                for (int j = 0; j < 9; j++) {
                    System.out.println("Pos (" + i + "," + j + ") -> " + model.getBoard()[i][j]);
                    if (model.getBoard()[i][j] != null) {
                        cell = new GameCell(model, i, j, model.getBoard()[i][j].getPlanetName());
                        if (!model.getBoard()[i][j].getIsTurned()) {
                            cell = new GameCell(model, i, j, "card_down");
                        }
                        
                    } else {
                        cell = new GameCell(model, i, j, "null");
                    }
                    
                    cells.add(cell);
                    p.add(cell);
                }
                add(p);
            }

        }
    };

}
