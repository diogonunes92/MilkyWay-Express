package milkywayGIU.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import util.Constants;

public class CargoComponent extends JPanel implements Observer {

    private final Model model;
    private JLabel titleLabel, firstCargo, secondCargo, thirdCargo;
    private JPanel cargoPanel;
    private JButton upgradeCargoButton;

    public CargoComponent(Model model) {
        this.model = model;

        this.setMaximumSize(new Dimension(Constants.RIGHT_PANEL_COMPONENT_WIDTH, Constants.RIGHT_PANEL_COMPONENT_HEIGHT));
        this.setVisible(true);
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.setBackground(Color.darkGray);

        setupComponents();
        setupLayout();
        registerListeners();
    }

    private void setupComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel = new JLabel();
        firstCargo = new JLabel();
        secondCargo = new JLabel();
        thirdCargo = new JLabel();

        cargoPanel = new JPanel();

        upgradeCargoButton = new JButton();
    }

    private void setupLayout() {

        titleLabel.setText("CARGO");
        titleLabel.setFont(Constants.FONT_16);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        cargoPanel.setLayout(new GridLayout(1, 3));
        cargoPanel.setSize(new Dimension(200, 50));

        firstCargo.setSize(new Dimension(40, 40));
        firstCargo.setBackground(Color.BLUE);
        firstCargo.setText(this.model.getPlayer().getSpaceship().getCargo().get(0).getColor());

        secondCargo.setSize(new Dimension(40, 40));
        secondCargo.setBackground(Color.yellow);
        secondCargo.setText("black");

        thirdCargo.setSize(new Dimension(40, 40));
        thirdCargo.setBackground(Color.RED);
        thirdCargo.setText("rose");

        cargoPanel.add(firstCargo);
        cargoPanel.add(secondCargo);
        cargoPanel.add(thirdCargo);

        upgradeCargoButton.setText("Upgrage Cargo");
        upgradeCargoButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(titleLabel);
        this.add(cargoPanel);
        this.add(upgradeCargoButton);

    }

    void registerObservers() {
        model.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            g.drawImage(getBackgroundImage(), 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(WeaponComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Image getBackgroundImage() throws IOException {

        Image img = ImageIO.read(getClass().getResource("/images/cargo.png"));
        img.flush();
        return img;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UpdateCargoComponent");
    }

    private void registerListeners() {

        upgradeCargoButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                model.upgradeCargo();
            }
        });
    }
}
