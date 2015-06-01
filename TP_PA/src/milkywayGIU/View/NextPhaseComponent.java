package milkywayGIU.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import milkyway_logic.states.Buy;
import milkyway_logic.states.Move;
import milkyway_logic.states.Sell;
import util.Constants;

public class NextPhaseComponent extends JPanel implements Observer {

    private final Model model;
    private JButton nextButton;
    private JLabel currentStateLabel, titleLabel, creditsLabel;
    private Box mBox;

    public NextPhaseComponent(Model model) {
        this.model = model;

        setMaximumSize(new Dimension(Constants.RIGHT_PANEL_COMPONENT_WIDTH, Constants.RIGHT_PANEL_COMPONENT_HEIGHT));
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.setVisible(true);

        this.setupComponents();
        this.setupLayout();
        this.registerListener();
    }

    private void setupComponents() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.currentStateLabel = new JLabel();
        this.nextButton = new JButton();
        this.titleLabel = new JLabel();
        this.creditsLabel = new JLabel();

        mBox = Box.createHorizontalBox();
    }

    private void setupLayout() {

        nextButton.setText("Next Phase");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.setBackground(Color.LIGHT_GRAY);

        currentStateLabel.setText("Move");
        currentStateLabel.setFont(Constants.FONT_25);
        currentStateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleLabel.setText("CREDITS: ");
        titleLabel.setFont(Constants.FONT_20);
        titleLabel.setAlignmentY(CENTER_ALIGNMENT);

        creditsLabel.setText(String.valueOf(model.getPlayer().getCoins()));
        creditsLabel.setFont(Constants.FONT_20);
        creditsLabel.setAlignmentY(CENTER_ALIGNMENT);

        mBox.add(titleLabel);
        mBox.add(Box.createHorizontalStrut(20));
        mBox.add(creditsLabel);

        this.add(Box.createRigidArea(new Dimension(0, Constants.INSIDE_PANEL_SPACE)));
        this.add(currentStateLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(nextButton);
        this.add(Box.createRigidArea(new Dimension(0, Constants.INSIDE_PANEL_SPACE)));
        this.add(mBox);
    }

    private void registerListener() {

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                if (model.getState() instanceof Move) {

                    if (model.getPlayer().getSpaceship().isHasMoved()) {
                        model.getPlayer().getSpaceship().setHasMoved(false);
                        model.explore();
                        model.nextState();
                        nextButton.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(getParent(), "You have to move at least once.");
                    }

                } else {
                    model.nextState();
                }
            }
        }
        );
    }

    void registerObservers() {
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        creditsLabel.setText(String.valueOf(model.getPlayer().getCoins()));

        if (model.getState() instanceof Move) {
            currentStateLabel.setText("Move");
        } else if (model.getState() instanceof Sell) {
            currentStateLabel.setText("Sell");
        } else if (model.getState() instanceof Buy) {
            currentStateLabel.setText("Buy");
        }
    }
}
