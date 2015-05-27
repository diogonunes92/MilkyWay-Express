package milkywayGIU.View;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import milkywayGIU.Model.Model;
import milkyway_logic.states.Buy;
import milkyway_logic.states.Move;
import milkyway_logic.states.Sell;
import util.Constants;

public class CreditsComponent extends JPanel implements Observer {

    private Model model;
    private JLabel title;
    private JPanel panel;

    public CreditsComponent(Model model) {
        this.model = model;

        this.setMaximumSize(new Dimension(Constants.RIGHT_PANEL_COMPONENT_WIDTH, Constants.RIGHT_PANEL_COMPONENT_HEIGHT));
        this.setVisible(true);
        this.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        this.setBackground(Color.darkGray);
        
        setupLayout();
    }

    private void setupLayout() {
        
    }
    @Override
    public void update(Observable o, Object arg) {

        if (model.getState() instanceof Move) {

        } else if (model.getState() instanceof Sell) {

        } else if (model.getState() instanceof Buy) {

        }
    }
}
