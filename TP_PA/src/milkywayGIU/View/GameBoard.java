package milkywayGIU.View;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;

public final class GameBoard extends JPanel {

    private Model model;
    private ArrayList<GameCell> cells = new ArrayList();

    GameBoard(Model model) {
        this.model = model;
        this.model.constructGame();
    }

    void registerObservers() {
        cells.stream().forEach((cell) -> {
            model.addObserver(cell);
        });
    }

    @Override
    public void update(Graphics g) {
        System.out.println("Esta a ser chamado update no GAMEBOARD");
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        cells = new ArrayList();
        GameCell cell = null;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        System.out.println("paint component chamado (game board)");
        for (int i = 0; i < 7; i++) {
            JPanel cellPanel = new JPanel();
            for (int j = 0; j < 9; j++) {
                if (model.getBoard()[i][j] != null) {
                    if (!model.getBoard()[i][j].getIsTurned()) {
                        cell = new GameCell(model, i, j, "card_down");
                    }
                    if (model.getBoard()[i][j].getIsTurned()) {
                        cell = new GameCell(model, i, j, model.getBoard()[i][j].getPlanetName());
                    }
                } else {
                    cell = new GameCell(model, i, j, "null");
                }
                cell.setVisible(true);
                cells.add(cell);
                cellPanel.add(cell);
            }
            add(cellPanel);
        }
        registerObservers();
    }
}
