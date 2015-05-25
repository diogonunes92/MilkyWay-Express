package milkywayGIU.View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import milkywayGIU.Model.Model;

public class IuGraphic extends JFrame implements Observer {

    Model model;
    GamePanel gPanel;

    IuGraphic(Model m) {
        this.model = m;
        m.constructGame();
        addComponents();
        setVisible(true);
        this.setSize(1400, 675);
        this.setMinimumSize(new Dimension(650, 450));
    }

    private void addComponents() {

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        gPanel = new GamePanel(model);
        cp.add(gPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void update(Observable o, Object arg) {
        getParent().repaint();
    }
}
