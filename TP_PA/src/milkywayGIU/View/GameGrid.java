/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkywayGIU.View;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;

/**
 *
 * @author Diogo
 */
public class GameGrid extends JPanel {

        Model model;
        ArrayList<GameCell> cells = new ArrayList<>();

        GameGrid(Model m) {

            model = m;
            model.constructGame();

        }

        void registerObservers() {
            for (GameCell cell : cells) {
                model.addObserver(cell);
            }
        }

        @Override
        public void update(Graphics g) {
            super.update(g); //To change body of generated methods, choose Tools | Templates.
            //getParent().repaint();

        }

        @Override
        public void paintComponent(Graphics g) {
            GameCell cell;
            cells.clear();
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
    }