package milkywayGIU.View;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;

public class GamePanel extends JPanel implements Observer {

    private GameBoard gameBoard;
    private Model model;
    private NextPhaseBlock nextPhaseBlock;
    
    private JPanel centerPanel; 
    private JPanel rightUpPanel;
    

    GamePanel(Model model) {

        this.model = model;
        this.model.addObserver(this);
        this.setLayout(new BorderLayout());
        
        this.setupComponents();
        this.setupLayout();
        
        this.registerObservers();
    }

    private void setupComponents() {
        
        gameBoard = new GameBoard(model);
        nextPhaseBlock = new NextPhaseBlock(model);
        
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        
        rightUpPanel = new JPanel();
        rightUpPanel.setLayout(new BorderLayout());
    }

    private void setupLayout() {
        
        centerPanel.add(gameBoard, BorderLayout.NORTH);
        
        rightUpPanel.add(nextPhaseBlock, BorderLayout.EAST);
        
        this.add(rightUpPanel, BorderLayout.EAST);
        this.add(centerPanel, BorderLayout.WEST);
        //this.validate();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private void registerObservers() {
        //gameBoard.registerObservers();
        nextPhaseBlock.registerObservers();
    }
}
