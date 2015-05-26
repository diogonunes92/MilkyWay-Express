package milkywayGIU.View;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import milkywayGIU.Model.Model;
import util.Constants;

public class IuGraphic extends JFrame implements Observer {

    private Model model;
    private GamePanel gPanel;
    private Container container;

    private JMenuBar menuBar;
    private JMenu menuGame;
    private JMenuItem menuNewGame, menuLoadGame, menuSaveGame;
    private JMenu menuHelp;
    private JMenuItem menuInstructions;
    private JMenuItem menuCredits;

    private final Font Font12 = new Font("Verdana", Font.PLAIN, 12);
    private final Font Font15 = new Font("Verdana", Font.BOLD, 15);
    private final Font Font25 = new Font("Verdana", Font.BOLD, 25);

    IuGraphic(Model model) {
        this.model = model;
        this.model.constructGame();
        this.model.addObserver(this);

        container = getContentPane();
        container.setLayout(new BorderLayout());
        gPanel = new GamePanel(model);
        container.add(gPanel, BorderLayout.CENTER);

        createComponents();
        setComponents();

        this.setVisible(true);
        this.setSize(Constants.WINDOW_WIDTH, Constants.HEIGHT);
        //this.setResizable(false);
    }

    private void createComponents() {

//         <Menu bar in the top
        menuBar = new JMenuBar();

        menuGame = new JMenu("Game");
        menuNewGame = new JMenuItem("Novo Jogo");
        menuNewGame.setFont(Font12);
        menuSaveGame = new JMenuItem("Salvar Jogo");
        menuSaveGame.setFont(Font12);
        menuLoadGame = new JMenuItem("Carregar Jogo");
        menuLoadGame.setFont(Font12);

        menuHelp = new JMenu("Help");
        menuInstructions = new JMenuItem("Game Instructions");
        menuInstructions.setFont(Font12);

        menuCredits = new JMenu("Credits");
        menuCredits.setFont(Font12);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setComponents() {
        setJMenuBar(menuBar);

        //Game Menu option
        menuGame.add(menuNewGame);
        menuGame.add(menuLoadGame);
        menuGame.add(menuSaveGame);
        menuBar.add(menuGame);

        //Help
        menuHelp.add(menuInstructions);
        menuHelp.add(menuCredits);

        menuBar.add(menuHelp);
    }

    @Override
    public void update(Observable o, Object arg) {
        //area.repaint();
    }

}
