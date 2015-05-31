package milkywayGIU.View;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;

public final class GameBoard extends JPanel {

    private final Model model;
    private ArrayList<GameCell> cells = new ArrayList();

    GameBoard(Model model) {

        this.model = model;
        this.model.constructGame();
        this.cells = new ArrayList();
        this.setLayout(new GridLayout(7, 3));

        setupComponents();
        setupLayout();
    }

    private void setupComponents() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 9; j++) {
                GameCell cell = new GameCell(model, i, j);
                cell.setVisible(true);
                cells.add(cell);
                this.add(cell);
            }
        }
    }

    private void setupLayout() {

    }

    void registerObservers() {
        cells.stream().forEach((cell) -> {
            model.addObserver(cell);
        });
    }
}
