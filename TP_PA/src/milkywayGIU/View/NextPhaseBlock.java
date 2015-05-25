package milkywayGIU.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import milkywayGIU.Model.Model;
import milkyway_logic.states.Buy;
import milkyway_logic.states.Move;
import milkyway_logic.states.Sell;

/**
 *
 * @author Diogo
 */
public class NextPhaseBlock extends JPanel implements Observer {

        private Model model;
        JTextField currentPhase;
        JButton nextButton;

        public NextPhaseBlock(Model model) {
            this.model = model;
            currentPhase = new JTextField(15);
            nextButton = new JButton();
            nextButton.setText("Next Phase");
            currentPhase.setText("Starting Game");
            setMaximumSize(new Dimension(200, 40));
            System.out.println("Tou a criar o nextphase");
            setVisible(true);
            setupLayout();
            addListener();
        }

        private void setupLayout() {
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            JPanel gamePanel = new JPanel();

            gamePanel.add(currentPhase);
            gamePanel.add(nextButton);
            add(gamePanel);

        }

        void registerObservers() {
            model.addObserver(this);
        }

        @Override
        public void update(Observable o, Object arg) {

            if (model.getState() instanceof Move) {
                currentPhase.setText("Move");
            } else if (model.getState() instanceof Sell) {
                currentPhase.setText("Sell");
            } else if (model.getState() instanceof Buy) {
                currentPhase.setText("Buy");
            }
            getParent().repaint();
        }

        private void addListener() {
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {
                    model.nextState();
                }
            });
        }
    }