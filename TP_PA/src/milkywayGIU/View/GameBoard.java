package milkywayGIU.View;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;

public class GameBoard extends JPanel {

    private Model model;
    private ArrayList<GameCell> cells = new ArrayList<>();

    GameBoard(Model model) {

        this.model = model;
        this.model.constructGame();
        this.paintComponent(getGraphics());
    }

    void registerObservers() {
        for (GameCell cell : cells) {
            model.addObserver(cell);
        }
    }

    @Override
    public void update(Graphics g) {
        
    }

    @Override
    public void paintComponent(Graphics g) {
        cells.clear();
        GameCell cell;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        for (int i = 0; i < 7; i++) {
            JPanel cellPanel = new JPanel();
            for (int j = 0; j < 9; j++) {
                System.out.println("Pos (" + i + "," + j + ") -> " + model.getBoard()[i][j]);

                if (model.getBoard()[i][j] != null) {
                    cell = new GameCell(model, i, j, model.getBoard()[i][j].getPlanetName());

                    if (!model.getBoard()[i][j].getIsTurned()) {
                        cell = new GameCell(model, i, j, "card_down");
                    }

                } else {
                    cell = new GameCell(model, i, j, "null");
                    cell.setVisible(true);

                }

                cells.add(cell);
                cellPanel.add(cell);

            }
            add(cellPanel);
        }
    }
}
