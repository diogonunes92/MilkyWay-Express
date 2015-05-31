package milkywayGIU.View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import milkywayGIU.Model.Model;
import util.Constants;

public class IuGraphic extends JFrame {

    private Model model;
    private GamePanel gamePanel;
    private Container container;

    private JMenuBar menuBar;
    private JMenu menuGame, menuHelp;
    private JMenuItem menuNewGame, menuLoadGame, menuSaveGame, menuInstructions, menuCredits;

    IuGraphic(Model model) {
        this.model = model;
        this.model.constructGame();

        container = getContentPane();
        container.setLayout(new BorderLayout());

        gamePanel = new GamePanel(model);
        container.add(gamePanel, BorderLayout.AFTER_LINE_ENDS);

        setupComponents();
        setupLayout();
        registListeners();

        this.setTitle("::: MilkyWay Express :::");
        this.setVisible(true);
        this.setSize(Constants.WINDOW_WIDTH, Constants.HEIGHT);
        this.setResizable(false);
    }

    private void setupComponents() {

//         <Menu bar in the top
        menuBar = new JMenuBar();

        menuGame = new JMenu("Game");
        menuNewGame = new JMenuItem("Novo Jogo");
        menuNewGame.setFont(Constants.FONT_12);
        menuSaveGame = new JMenuItem("Salvar Jogo");
        menuSaveGame.setFont(Constants.FONT_12);
        menuLoadGame = new JMenuItem("Carregar Jogo");
        menuLoadGame.setFont(Constants.FONT_12);

        menuHelp = new JMenu("Help");
        menuInstructions = new JMenuItem("Game Instructions");
        menuInstructions.setFont(Constants.FONT_12);

        menuCredits = new JMenuItem("Credits");
        menuCredits.setFont(Constants.FONT_12);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupLayout() {

        //Game Menu option
        menuGame.add(menuNewGame);
        menuGame.add(menuLoadGame);
        menuGame.add(menuSaveGame);
        menuBar.add(menuGame);

        //Help
        menuHelp.add(menuInstructions);
        menuHelp.add(menuCredits);

        menuBar.add(menuHelp);
        this.setJMenuBar(menuBar);

    }

    protected void registListeners() {

        menuNewGame.addActionListener(new MenuNewGame());
        menuLoadGame.addActionListener(new MenuLoadGame());
        menuSaveGame.addActionListener(new MenuSaveGame());
        menuInstructions.addActionListener(new MenuInstructions());
        menuCredits.addActionListener(new MenuCredits());
    }

    class MenuNewGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(IuGraphic.this, "Implement Instructions");
        }
    }

    class MenuSaveGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(IuGraphic.this, "Implement Instructions");
        }
    }

    class MenuLoadGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(IuGraphic.this, "Implement Instructions");
        }
    }

    class MenuInstructions implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(IuGraphic.this, "Implement Instructions");
        }
    }

    class MenuCredits implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(IuGraphic.this, "Advanced Programming Course Project\n\n" + "Diogo Nunes, 21200000\ndiogonuuunes@gmail.com\n\n" + "Marco Barbosa, 21200304\nmarco.de.barbosa@gmail.com\n\n\n", "Credits", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
