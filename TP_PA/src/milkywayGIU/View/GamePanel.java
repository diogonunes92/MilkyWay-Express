package milkywayGIU.View;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;

public class GamePanel extends JPanel implements Observer {

    GameGrid grid;
    Model model;
    NextPhaseBlock nextPhase;

    GamePanel(Model model) {

        this.model = model;
        this.model.addObserver(this);
        setLayout(new BorderLayout());
        setupComponents();
        setupLayout();
        registerObservers();
    }

    private void setupComponents() {
        grid = new GameGrid(model);
        nextPhase = new NextPhaseBlock(model);
    }

    private void setupLayout() {
        JPanel right_up = new JPanel();
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        center.add(grid, BorderLayout.NORTH);
        right_up.setLayout(new BorderLayout());
        right_up.add(nextPhase, BorderLayout.EAST);
        System.out.println("Tou a criar o nextphase");
        add(right_up, BorderLayout.EAST);
        add(center, BorderLayout.WEST);
        validate();
    }

    @Override
    public void update(Observable o, Object arg) {
        getParent().repaint();
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
    }

    private void registerObservers() {
        grid.registerObservers();
        nextPhase.registerObservers();
    }
}
