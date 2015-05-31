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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import milkyway_logic.states.Buy;
import util.Constants;

public class WeaponComponent extends JPanel implements Observer {

    private final Model model;
    private JLabel titleLabel, weaponPowerLabel;
    private JButton weaponUpgrade1Button, weaponUpgrade2Button;
    private String backgroundImage;

    public WeaponComponent(Model model) {
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
        weaponPowerLabel = new JLabel();
        weaponUpgrade1Button = new JButton();
        weaponUpgrade2Button = new JButton();
    }

    private void setupLayout() {

        titleLabel.setText("WEAPON POWER");
        titleLabel.setFont(Constants.FONT_16);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        weaponPowerLabel.setText(String.valueOf(this.model.getPlayer().getSpaceship().getPower()));
        weaponPowerLabel.setFont(Constants.FONT_25);
        weaponPowerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        weaponUpgrade1Button.setBackground(Color.LIGHT_GRAY);
        weaponUpgrade1Button.setText("Upgrade Weapon 1");
        weaponUpgrade1Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        weaponUpgrade2Button.setBackground(Color.LIGHT_GRAY);
        weaponUpgrade2Button.setText("Upgrade Weapon 2");
        weaponUpgrade2Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createHorizontalStrut(5));
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, Constants.INSIDE_PANEL_SPACE)));
        this.add(weaponPowerLabel);
        this.add(Box.createRigidArea(new Dimension(0, Constants.INSIDE_PANEL_SPACE)));
        this.add(weaponUpgrade1Button);
        this.add(Box.createRigidArea(new Dimension(0, Constants.INSIDE_PANEL_SPACE)));
        this.add(weaponUpgrade2Button);

    }

    void registerObservers() {
        model.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(getBackgroundImage(), 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(WeaponComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Image getBackgroundImage() throws IOException {

        Image img = ImageIO.read(getClass().getResource("/images/weapon_panel.jpg"));
        img.flush();
        return img;
    }

    private void registerListeners() {

        weaponUpgrade1Button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (model.getState() instanceof Buy) {
                    if (!model.getPlayer().getSpaceship().isFirstWeaponUpdated()) {
                        model.upgradeWeapon();
                    } else {
                        JOptionPane pane = new JOptionPane("First weapon already updated!");
                        final JDialog d = pane.createDialog((JFrame) null, "Error");
                        d.setLocation(500, 300);
                        d.setVisible(true);
                    }
                } else {
                    JOptionPane pane = new JOptionPane("Just on Buy state!");
                    final JDialog d = pane.createDialog((JFrame) null, "Error");
                    d.setLocation(500, 300);
                    d.setVisible(true);
                }
            }
        });

        weaponUpgrade2Button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (model.getState() instanceof Buy) {
                    if (model.getPlayer().getSpaceship().isFirstWeaponUpdated()) {
                        if (!model.getPlayer().getSpaceship().isSecondWeaponUpdated()) {
                            model.upgradeWeapon();
                        } else {
                            JOptionPane pane = new JOptionPane("Second weapon already updated!");
                            final JDialog d = pane.createDialog((JFrame) null, "Error");
                            d.setLocation(500, 300);
                            d.setVisible(true);
                        }
                    } else {
                        JOptionPane pane = new JOptionPane("Upgrade other weapon first!");
                        final JDialog d = pane.createDialog((JFrame) null, "Error");
                        d.setLocation(500, 300);
                        d.setVisible(true);
                    }
                } else {
                    JOptionPane pane = new JOptionPane("Just on Buy state!");
                    final JDialog d = pane.createDialog((JFrame) null, "Error");
                    d.setLocation(500, 300);
                    d.setVisible(true);
                }
            }
        }
        );
    }

    @Override

    public void update(Observable o, Object arg) {
        System.out.println("UpdateWeaponComponent");

//        if(model.getState().upgradeWeapon() instanceof Buy){
        weaponPowerLabel.setText(String.valueOf(this.model.getPlayer().getSpaceship().getPower()));
//        } 
    }
}
