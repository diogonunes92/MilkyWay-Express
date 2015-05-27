package milkywayGIU.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import util.Constants;

public class GamePanel extends JPanel implements Observer {

    private GameBoard gameBoard;
    private Model model;

    private NextPhaseComponent nextPhaseComponent;
    private WeaponComponent weaponComponent;
    private CreditsComponent creditsComponent;
    private CargoComponent cargoComponent;

    private JPanel centerPanel, rightPanel;
    private JPanel weaponPanel, creditsPanel, cargoPanel, nextPhasePanel;

    GamePanel(Model model) {

        this.model = model;
        this.model.addObserver(this);
        this.setLayout(new BorderLayout());

        this.setupComponents();
        this.setupLayout();

        this.registerObservers();

    }

    private void setupComponents() {

        // Components object instances
        gameBoard = new GameBoard(model);
        nextPhaseComponent = new NextPhaseComponent(model);
        weaponComponent = new WeaponComponent(model);
        cargoComponent = new CargoComponent(model);
        creditsComponent = new CreditsComponent(model);

        // Main Panels
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        rightPanel = new JPanel();
        rightPanel.setLayout( new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(Constants.RIGHT_PANEL_WIDTH, this.getHeight()));

    }

    private void setupLayout() {

        centerPanel.add(gameBoard, BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.WEST);

        rightPanel.add(Box.createRigidArea(new Dimension(0,Constants.RIGHT_PANEL_SPACE)));
        rightPanel.add(nextPhaseComponent);
        rightPanel.add(Box.createRigidArea(new Dimension(0,Constants.RIGHT_PANEL_SPACE)));
        rightPanel.add(weaponComponent);
        rightPanel.add(Box.createRigidArea(new Dimension(0,Constants.RIGHT_PANEL_SPACE)));
        rightPanel.add(creditsComponent);
        rightPanel.add(Box.createRigidArea(new Dimension(0,Constants.RIGHT_PANEL_SPACE)));
        rightPanel.add(cargoComponent);
        
        this.add(rightPanel, BorderLayout.EAST);

    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private void registerObservers() {
        //gameBoard.registerObservers();
        nextPhaseComponent.registerObservers();
    }
}
