package milkywayGIU.View;

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
    private JLabel currentPhase;
    private JButton nextButton;

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

        this.currentPhase = new JLabel();
        this.nextButton = new JButton();
    }

    private void setupLayout() {

        nextButton.setText("Next Phase");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        currentPhase.setText(model.getState().toString());
        currentPhase.setFont(Constants.FONT_25);
        currentPhase.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, Constants.INSIDE_PANEL_SPACE)));
        this.add(currentPhase);
        this.add(Box.createRigidArea(new Dimension(0, 60)));
        this.add(nextButton);
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
        System.out.println("UpdateNextPhaseComponent");

        if (model.getState() instanceof Move) {
            currentPhase.setText("Move");
        } else if (model.getState() instanceof Sell) {
            currentPhase.setText("Sell");
        } else if (model.getState() instanceof Buy) {
            currentPhase.setText("Buy");
        }
    }
}
