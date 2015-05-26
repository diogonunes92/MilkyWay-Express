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
import util.Constants;

public class NextPhaseBlock extends JPanel implements Observer {

    private Model model;
    private JTextField currentPhase;
    private JButton nextButton;

    public NextPhaseBlock(Model model) {
        this.model = model;
        this.currentPhase = new JTextField(15);
        this.nextButton = new JButton();
        this.nextButton.setText("Next Phase");
        this.currentPhase.setText("Starting Game");

        setMaximumSize(new Dimension(Constants.NEXT_PHASE_BLOCK_WIDTH, 40));

        System.out.println("Tou a criar o nextphase");

        this.setVisible(true);
        this.setupLayout();
        this.addListener();
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
        
        //this.repaint();
    }

    private void addListener() {
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                System.out.println("MODEL EXPLORE");
                model.explore();
                model.nextState();
            }
        });
    }
}
