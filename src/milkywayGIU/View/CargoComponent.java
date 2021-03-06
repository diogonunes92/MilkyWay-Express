package milkywayGIU.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import milkyway_logic.cards.Planet;
import milkyway_logic.states.Buy;
import milkyway_logic.states.Sell;
import util.Constants;

public class CargoComponent extends JPanel implements Observer {

    private final Model model;
    private JPanel firstCargo, secondCargo, thirdCargo;
    private JLabel titleLabel;
    private Box mBox;
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

        firstCargo = new JPanel();
        secondCargo = new JPanel();
        thirdCargo = new JPanel();

        mBox = Box.createHorizontalBox();

        upgradeCargoButton = new JButton();
    }

    private void setupLayout() {

        titleLabel.setText("CARGO");
        titleLabel.setFont(Constants.FONT_16);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        firstCargo.setBackground(Color.WHITE);
        secondCargo.setBackground(Color.WHITE);
        thirdCargo.setBackground(Color.ORANGE);
        
        mBox.add(Box.createHorizontalStrut(30));
        mBox.add(firstCargo);
        mBox.add(Box.createHorizontalStrut(20));
        mBox.add(secondCargo);
        mBox.add(Box.createHorizontalStrut(20));
        mBox.add(thirdCargo);
        mBox.add(Box.createHorizontalStrut(30));

        upgradeCargoButton.setText("Upgrage Cargo");
        upgradeCargoButton.setBackground(Color.LIGHT_GRAY);
        upgradeCargoButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, Constants.INSIDE_PANEL_SPACE)));
        this.add(mBox);
        this.add(Box.createRigidArea(new Dimension(0, Constants.INSIDE_PANEL_SPACE)));
        this.add(upgradeCargoButton);
        this.add(Box.createRigidArea(new Dimension(0, Constants.INSIDE_PANEL_SPACE)));
    }

    void registerObservers() {
        model.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        try {
            g.drawImage(getBackgroundImage(), 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(CargoComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Image getBackgroundImage() throws IOException {

        Image img = ImageIO.read(getClass().getResource("/images/cargo.png"));
        img.flush();
        return img;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (model.getState() instanceof Buy || model.getState() instanceof Sell) {

            firstCargo.setBackground(Color.WHITE);
            secondCargo.setBackground(Color.WHITE);
            thirdCargo.setBackground(Color.WHITE);
            
            if (!model.getPlayer().getSpaceship().isCargoUpdated()) {
                thirdCargo.setBackground(Color.orange);
            }

            if (this.model.getPlayer().getSpaceship().getCargo().size() > 0) {
                firstCargo.setBackground(this.model.getPlayer().getSpaceship().getCargo().get(0).getColorObject());
                firstCargo.setOpaque(true);
            }
            if (this.model.getPlayer().getSpaceship().getCargo().size() > 1) {
                secondCargo.setBackground(this.model.getPlayer().getSpaceship().getCargo().get(1).getColorObject());
                secondCargo.setOpaque(true);
            }
            if (this.model.getPlayer().getSpaceship().getCargo().size() > 2) {
                thirdCargo.setBackground(this.model.getPlayer().getSpaceship().getCargo().get(2).getColorObject());
                thirdCargo.setOpaque(true);
            }
            
            repaint();
        }
    }

    private void registerListeners() {

        upgradeCargoButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (model.getBoard()[model.getPlayer().getSpaceship().getPosX()][model.getPlayer().getSpaceship().getPosY()] instanceof Planet) {
                    model.upgradeCargo();
                } else {
                    JOptionPane.showMessageDialog(getParent(), "You're not on a Planet!");
                }

            }
        });

        firstCargo.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println(firstCargo.getBackground().toString());
                model.sellCargo(firstCargo.getBackground());
            }

        });
        
        secondCargo.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println(secondCargo.getBackground().toString());
                model.sellCargo(secondCargo.getBackground());
            }

        });
    }
}
