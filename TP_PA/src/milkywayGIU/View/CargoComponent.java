package milkywayGIU.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import milkywayGIU.Model.Model;
import util.Constants;

public class CargoComponent extends JPanel implements Observer {

    private Model model;
    private JLabel titleLabel, firstCargo, secondCargo, thirdCargo;
    private JButton upgradeCargoButton;
    private JPanel panel;

    public CargoComponent(Model model) {
        this.model = model;

        this.setMaximumSize(new Dimension(Constants.RIGHT_PANEL_COMPONENT_WIDTH, Constants.RIGHT_PANEL_COMPONENT_HEIGHT));
        this.setVisible(true);
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.setBackground(Color.darkGray);

        setupComponents();
        setupLayout();
    }

    private void setupComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        panel = new JPanel();
        titleLabel = new JLabel();
        firstCargo = new JLabel();
        secondCargo = new JLabel();
        thirdCargo = new JLabel();

        upgradeCargoButton = new JButton();

        upgradeCargoButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                model.upgradeCargo();
            }
        });
    }

    private void setupLayout() {

        panel.setLayout(new GridLayout(4, 1));
        panel.setBackground(Color.pink);

        titleLabel.setText("CARGO");
        titleLabel.setFont(Constants.FONT_13);

        firstCargo.setPreferredSize(new Dimension(20, 20));
        firstCargo.setBackground(Color.BLUE);
        firstCargo.setText(this.model.getPlayer().getSpaceship().getCargo().get(0).getColor());

        secondCargo.setPreferredSize(new Dimension(20, 20));
        secondCargo.setBackground(Color.yellow);
        secondCargo.setText("black");

        thirdCargo.setPreferredSize(new Dimension(20, 20));
        thirdCargo.setBackground(Color.RED);
        thirdCargo.setText("rose");

        upgradeCargoButton.setText("Upgrage Cargo");

        panel.add(titleLabel);
        panel.add(firstCargo);
        panel.add(secondCargo);
        panel.add(thirdCargo);
        panel.add(upgradeCargoButton);

        this.add(panel);
    }

    void registerObservers() {
        model.addObserver(this);
    }

    @Override

    public void update(Observable o, Object arg) {
        System.out.println("UpdateCargoComponent");
    }
}
