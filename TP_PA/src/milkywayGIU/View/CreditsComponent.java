package milkywayGIU.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import util.Constants;

public class CreditsComponent extends JPanel implements Observer {

    private Model model;
    private JLabel titleLabel, creditsLabel;
    private JPanel panel;

    public CreditsComponent(Model model) {
        this.model = model;

        this.setMaximumSize(new Dimension(Constants.RIGHT_PANEL_COMPONENT_WIDTH, Constants.RIGHT_PANEL_COMPONENT_HEIGHT));
        this.setVisible(true);
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.setBackground(Color.darkGray);

        setupComponents();
        setupLayout();
    }

    private void setupComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        titleLabel = new JLabel();
        creditsLabel = new JLabel();
        
        panel = new JPanel();
    }

    private void setupLayout() {

        panel.setLayout(new GridLayout(2, 1));
        panel.setBackground(Color.red);
        
        titleLabel.setText("CREDITS");
        titleLabel.setFont(Constants.FONT_13);
        
        creditsLabel.setText(String.valueOf(model.getPlayer().getCoins()));
        creditsLabel.setFont(Constants.FONT_25);
        
        panel.add(titleLabel);
        panel.add(creditsLabel);
        
        this.add(panel);
    }

    void registerObservers() {
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        //creditsLabel.setText(String.valueOf(model.getPlayer().getCoins()));
    }
}
