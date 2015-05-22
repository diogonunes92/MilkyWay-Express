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
import milkyway_logic.gameplanner.Game;

/**
 *
 * @author Diogo
 */
public class IuGraphic extends JFrame {

    Game game;
    GamePanel gPanel;

    IuGraphic(Game j) {
        game = j;
        addComponents();
        setVisible(true);
        this.setSize(1000, 900);
        this.setMinimumSize(new Dimension(650, 450));
    }

    private void addComponents() {
        
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        gPanel = new GamePanel(game);
        cp.add(gPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
