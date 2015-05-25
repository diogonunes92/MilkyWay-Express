/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milkywayGIU.View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import milkyway_logic.gameplanner.Game;

/**
 *
 * @author Diogo
 */
public class IuGraphic extends JFrame implements Observer{

    Model model;
    GamePanel gPanel;

    IuGraphic(Model m) {
        this.model = m;
        m.constructGame();
        addComponents();
        setVisible(true);
        this.setSize(1000, 900);
        this.setMinimumSize(new Dimension(650, 450));
    }

    private void addComponents() {
        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        gPanel = new GamePanel(model);
        cp.add(gPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
}
