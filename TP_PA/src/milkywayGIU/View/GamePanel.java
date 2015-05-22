/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkywayGIU.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
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
        this.game.constructGame();
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

        GameCell(Game j, int r, int c) {
            row = r;
            col = c;
            this.game = j;

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

            //  drawIcon(j, g);
        }

        @Override
        public void update(Observable o, Object arg) {
            repaint();
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
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            for (int i = 0; i < Constants.BOARD_LIMIT_SUP_X; i++) {
                JPanel p = new JPanel();
                for (int j = 0; j < Constants.BOARD_LIMIT_SUP_Y; j++) {
                    GameCell cell = new GameCell(game, i, j);
                    cells.add(cell);
                    p.add(cell);
                }
                add(p);
            }

        }
    };

}
