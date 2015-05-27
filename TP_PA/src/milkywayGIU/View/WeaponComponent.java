package milkywayGIU.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import milkywayGIU.Model.Model;
import milkyway_logic.states.Buy;
import milkyway_logic.states.Move;
import milkyway_logic.states.Sell;
import util.Constants;

public class WeaponComponent extends JPanel implements Observer {

    private Model model;
    private JLabel titleLabel, weaponPowerLabel;
    private JButton weaponUpgrade1Button, weaponUpgrade2Button;
    private JPanel panel;

    public WeaponComponent(Model model) {
        this.model = model;

        this.setMaximumSize(new Dimension(Constants.RIGHT_PANEL_COMPONENT_WIDTH, Constants.RIGHT_PANEL_COMPONENT_HEIGHT));
        this.setVisible(true);
        this.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        this.setBackground(Color.darkGray);

        setupComponents();
        setupLayout();
    }

    private void setupComponents() {

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        titleLabel = new JLabel();
        weaponPowerLabel = new JLabel();
        weaponUpgrade1Button = new JButton();
        weaponUpgrade2Button = new JButton();

        panel = new JPanel();
    }

    private void setupLayout() {

        panel.setLayout(new GridLayout(4, 2));
        panel.setBackground(Color.orange);

        titleLabel.setText("WEAPON POWER");
        titleLabel.setFont(Constants.FONT_13);

        weaponUpgrade1Button.setBackground(Color.red);
        weaponUpgrade1Button.setText("Upgrade Weapon 1");

        weaponUpgrade2Button.setBackground(Color.red);
        weaponUpgrade2Button.setText("Upgrade Weapon 2");

        weaponPowerLabel.setText(String.valueOf(this.model.getPlayer().getSpaceship().getPower()));
        weaponPowerLabel.setFont(Constants.FONT_25);

        panel.add(titleLabel);
        panel.add(weaponPowerLabel);
        panel.add(weaponUpgrade1Button);
        panel.add(weaponUpgrade2Button);

        this.add(panel);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (model.getState() instanceof Move) {

        } else if (model.getState() instanceof Sell) {

        } else if (model.getState() instanceof Buy) {

        }
    }

}
