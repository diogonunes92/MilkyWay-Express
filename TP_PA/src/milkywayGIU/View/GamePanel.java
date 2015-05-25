package milkywayGIU.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import milkywayGIU.Model.Model;
import milkyway_logic.states.*;

public class GamePanel extends JPanel implements Observer {

    GameGrid grid;
    Model model;
    NextPhaseBlock nextPhase;

    GamePanel(Model model) {

        this.model = model;
        this.model.addObserver(this);
        setLayout(new BorderLayout());
        setupComponents();
        setupLayout();
        registerObservers();
    }

    private void setupComponents() {
        grid = new GameGrid(model);
        nextPhase = new NextPhaseBlock(model);
    }

    private void setupLayout() {
        JPanel right_up = new JPanel();
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        center.add(grid, BorderLayout.NORTH);
        right_up.setLayout(new BorderLayout());
        right_up.add(nextPhase, BorderLayout.EAST);
        System.out.println("Tou a criar o nextphase");
        add(right_up, BorderLayout.EAST);
        add(center, BorderLayout.WEST);
        validate();
    }

    @Override
    public void update(Observable o, Object arg) {
        getParent().repaint();
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);

    }

    private void registerObservers() {
        grid.registerObservers();
        nextPhase.registerObservers();
    }

    class GameCell extends JPanel implements Observer {

        int row, col;
        Model model;
        String name_planet;

        GameCell(Model j, int r, int c, String name) {
            row = r;
            col = c;
            this.model = j;
            name_planet = name;

            setPreferredSize(new Dimension(60, 60));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent ev) {
                    System.out.println(model.getState());
                    if (model.getState() instanceof Move) {
                        model.move(row, col);
                        System.out.println("Pos Spaceship(" + model.getPlayer().getSpaceship().getPosX() + "," + model.getPlayer().getSpaceship().getPosY() + ")");
                        System.out.println("CLICK!");
                    }
                }
            });
        }

        @Override
        public void paintBorder(Graphics g) {
            super.paintBorder(g);
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        @Override
        public void paintComponent(Graphics g) {
            try {
                drawIcon(g, name_planet);
                int x_spaceship = model.getPlayer().getSpaceship().getPosX();
                int y_spaceship = model.getPlayer().getSpaceship().getPosY();

                if (x_spaceship == row && y_spaceship == col) {
                    drawIcon(g, "Spaceship_v2");
                } else {
                    drawIcon(g, name_planet);
                }

            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void update(Observable o, Object arg) {
            getParent().repaint();
            validate();

        }

        private void drawIcon(Graphics g, String name) throws IOException {

            if (name.equals("Spaceship_v2")) {
                Image img = getNave();
                g.drawImage(img, 40, 15, getWidth() / 2, getHeight() / 2, null);

            } else if (!name.equals("Null") && name != null) {
                Image img = getCardImage(name);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
            }
            validate();
        }

        private Image getNave() throws IOException {
            Image img = ImageIO.read(getClass().getResource("/images/Spaceship_v2.png"));
            return img;
        }

        private Image getCardImage(String name_planet) throws IOException {
            if (!name_planet.equals("null") && name_planet != null) {
                System.out.println("nome -> " + name_planet);
                Image img = ImageIO.read(getClass().getResource("/images/" + name_planet + ".png"));
                return img;
            } else {
                Image img = ImageIO.read(getClass().getResource("/images/Null.png"));
                return img;
            }
        }
    };

    class NextPhaseBlock extends JPanel implements Observer {

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
            JPanel p = new JPanel();

            p.add(currentPhase);
            p.add(nextButton);
            add(p);

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
            nextPhase.repaint();
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

    final class GameGrid extends JPanel {

        Model model;
        ArrayList<GameCell> cells = new ArrayList<>();

        GameGrid(Model m) {

            model = m;
            model.constructGame();

        }

        void registerObservers() {
            for (GameCell cell : cells) {
                model.addObserver(cell);
            }
        }

        @Override
        public void update(Graphics g) {
            super.update(g); //To change body of generated methods, choose Tools | Templates.
            //getParent().repaint();

        }

        @Override
        public void paintComponent(Graphics g) {
            GameCell cell;

            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            for (int i = 0; i < 7; i++) {
                JPanel p = new JPanel();
                for (int j = 0; j < 9; j++) {
                    System.out.println("Pos (" + i + "," + j + ") -> " + model.getBoard()[i][j]);
                    if (model.getBoard()[i][j] != null) {
                        cell = new GameCell(model, i, j, model.getBoard()[i][j].getPlanetName());
                        if (!model.getBoard()[i][j].getIsTurned()) {
                            cell = new GameCell(model, i, j, "card_down");

                        }

                    } else {
                        cell = new GameCell(model, i, j, "null");
                    }

                    cells.add(cell);
                    p.add(cell);
                }
                add(p);
            }
        }
    }
}
