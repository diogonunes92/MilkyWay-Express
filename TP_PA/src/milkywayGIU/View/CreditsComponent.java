package milkywayGIU.View;

import java.awt.Color;
import java.awt.Dimension;
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
    private JLabel titleLabel, creditsLabel, roundsPlayed, pirateAttacks;

    public CreditsComponent(Model model) {
        this.model = model;

        this.setMaximumSize(new Dimension(Constants.RIGHT_PANEL_COMPONENT_WIDTH, Constants.RIGHT_PANEL_COMPONENT_HEIGHT));
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.setBackground(Color.CYAN);
        this.setVisible(false);

        setupComponents();
        setupLayout();
    }

    private void setupComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel = new JLabel();
        creditsLabel = new JLabel();
        roundsPlayed = new JLabel();
        pirateAttacks = new JLabel();
    }

    private void setupLayout() {

        titleLabel.setText("CREDITS");
        titleLabel.setFont(Constants.FONT_13);
        titleLabel.setAlignmentY(CENTER_ALIGNMENT);

        creditsLabel.setText(String.valueOf(model.getPlayer().getCoins()));
        creditsLabel.setFont(Constants.FONT_25);
        creditsLabel.setAlignmentY(CENTER_ALIGNMENT);

        add(titleLabel);
        add(creditsLabel);

    }

    void registerObservers() {
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        creditsLabel.setText(String.valueOf(model.getPlayer().getCoins()));
    }
}
