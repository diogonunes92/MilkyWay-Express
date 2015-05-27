package milkywayGIU.View;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import milkywayGIU.Model.Model;
import milkyway_logic.states.Buy;
import milkyway_logic.states.Move;
import milkyway_logic.states.Sell;
import util.Constants;

public class CargoComponent extends JPanel implements Observer {

    private Model model;
    private JTextField text;

    public CargoComponent(Model model) {
        this.model = model;
        this.text = new JTextField(15);

        this.setMaximumSize(new Dimension(Constants.RIGHT_PANEL_COMPONENT_WIDTH, Constants.RIGHT_PANEL_COMPONENT_HEIGHT));
        this.setVisible(true);
        this.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        this.setBackground(Color.darkGray);

        setupLayout();
    }

    private void setupLayout() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel weapPanel = new JPanel();

        text.setText("CARGO");
        weapPanel.add(text);
        this.add(weapPanel);
    }

    void registerObservers() {
        model.addObserver(this);
    }
    
    @Override

    public void update(Observable o, Object arg) {

        if (model.getState() instanceof Move) {

        } else if (model.getState() instanceof Sell) {

        } else if (model.getState() instanceof Buy) {

        }
    }

}
