package milkywayGIU.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import util.Constants;

public class GamePanel extends JPanel implements Observer {
    
    private GameBoard gameBoard;
    private final Model model;
    
    private NextPhaseComponent nextPhaseComponent;
    private WeaponComponent weaponComponent;
    private CreditsComponent creditsComponent;
    private CargoComponent cargoComponent;
    
    private JPanel centerPanel, rightPanel;
    
    GamePanel(Model model) {
        
        this.model = model;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        
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
        centerPanel.setOpaque(false);
        
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(Constants.RIGHT_PANEL_WIDTH, this.getHeight()));
        rightPanel.setOpaque(true);
        rightPanel.setBackground(Color.LIGHT_GRAY);
    }
    
    private void setupLayout() {
        
        centerPanel.add(gameBoard);
        
        rightPanel.add(Box.createRigidArea(new Dimension(0, Constants.RIGHT_PANEL_SPACE)));
        rightPanel.add(nextPhaseComponent);
        rightPanel.add(Box.createRigidArea(new Dimension(0, Constants.RIGHT_PANEL_SPACE)));
        rightPanel.add(weaponComponent);
        rightPanel.add(Box.createRigidArea(new Dimension(0, Constants.RIGHT_PANEL_SPACE)));
        rightPanel.add(cargoComponent);
        rightPanel.add(Box.createRigidArea(new Dimension(0, Constants.RIGHT_PANEL_SPACE)));
        rightPanel.add(creditsComponent);

        this.add(centerPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
    }
    
    private void registerObservers() {
        
        model.addObserver(this);
        
        gameBoard.registerObservers();
        nextPhaseComponent.registerObservers();
        creditsComponent.registerObservers();
        weaponComponent.registerObservers();
        cargoComponent.registerObservers();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

        try {
            g.drawImage(getBackgroundImage(), 0, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(WeaponComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        System.err.println("UpdateGamePanel");
        this.repaint();
        
    }
    
    private Image getBackgroundImage() throws IOException {
        
        Image img = ImageIO.read(getClass().getResource("/images/background_man.jpg"));
        img.flush();
        return img;
    }
}
