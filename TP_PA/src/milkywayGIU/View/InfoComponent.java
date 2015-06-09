/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkywayGIU.View;

import java.awt.Component;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import util.Constants;

/**
 *
 * @author Diogo
 */
public class InfoComponent extends JPanel implements Observer {

    Model model;
    private JLabel number_rounds, number_rounds_info, coin, coin_info,
            header_info, pirate_attacks, pirate_attacks_info;
    private Box mBox1, mBox2;

    public InfoComponent(Model model) {
        this.model = model;
        setMaximumSize(new Dimension(Constants.RIGHT_PANEL_COMPONENT_WIDTH, Constants.RIGHT_PANEL_COMPONENT_HEIGHT));
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.setVisible(true);

        this.setupComponents();
        this.setupLayout();
    }

    private void setupComponents() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.number_rounds = new JLabel();
        this.pirate_attacks = new JLabel();
        this.pirate_attacks_info = new JLabel();
        this.header_info = new JLabel();
        this.number_rounds_info = new JLabel();
        this.coin = new JLabel();
        this.coin_info = new JLabel();

        mBox1 = Box.createHorizontalBox();
        mBox2 = Box.createHorizontalBox();
    }

    private void setupLayout() {
        header_info.setText("INFO");
        header_info.setFont(Constants.FONT_20);
        header_info.setAlignmentY(TOP_ALIGNMENT);
        header_info.setAlignmentX(Component.CENTER_ALIGNMENT);

        number_rounds.setText("Number of Rounds: ");
        number_rounds.setFont(Constants.FONT_12);
        number_rounds.setAlignmentX(Component.LEFT_ALIGNMENT);

        number_rounds_info.setText(String.valueOf(model.getPlayer().getNumber_rounds()));
        number_rounds_info.setFont(Constants.FONT_12);
        number_rounds_info.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        pirate_attacks.setText("Pirate Attacks: ");
        pirate_attacks.setFont(Constants.FONT_12);
        pirate_attacks.setAlignmentX(Component.LEFT_ALIGNMENT);

        pirate_attacks_info.setText(String.valueOf(model.getPlayer().getPirateAttackNr()));
        pirate_attacks_info.setFont(Constants.FONT_12);
        pirate_attacks_info.setAlignmentX(Component.LEFT_ALIGNMENT);

        this.add(header_info);
        this.add(Box.createRigidArea(new Dimension(0, Constants.INSIDE_PANEL_SPACE)));

        mBox1.add(number_rounds);
        mBox1.add(number_rounds_info);
        mBox2.add(pirate_attacks);
        mBox2.add(pirate_attacks_info);

        this.add(mBox1);
        this.add(mBox2);
    }

    @Override
    public void update(Observable o, Object arg) {
         number_rounds_info.setText(String.valueOf(model.getPlayer().getNumber_rounds()));
         pirate_attacks_info.setText(String.valueOf(model.getPlayer().getPirateAttackNr()));
    }

    void registerObservers() {
        model.addObserver(this);
    }
}
