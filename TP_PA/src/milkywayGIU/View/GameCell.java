package milkywayGIU.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import milkyway_logic.cards.Planet;
import milkyway_logic.states.Buy;
import milkyway_logic.states.Move;
import util.Constants;

public class GameCell extends JPanel implements Observer {

    private final Model model;
    private final int row, col;
    private JPanel firstCube;
    private JPanel secondCube;

    GameCell(Model j, int r, int c) {
        this.row = r;
        this.col = c;
        this.model = j;

        this.setPreferredSize(new Dimension(Constants.CARD_WIDTH, Constants.CARD_HEIGHT));

        setupComponents();
        setupLayout();

        if (model.getBoard()[row][col] != null) {
            registerListeners();
        }
    }

    private void setupComponents() {
        firstCube = new JPanel();
        secondCube = new JPanel();
    }

    private void setupLayout() {

        firstCube.setBackground(Color.gray);
        firstCube.setOpaque(false);

        secondCube.setBackground(Color.gray);
        secondCube.setOpaque(false);

        this.add(firstCube);
        this.add(secondCube);

    }

    private void registerListeners() {

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                System.out.println(model.getState());
                if (model.getState() instanceof Move) {
                    model.move(row, col);
                    model.pirateAttack();
                }
            }
        });

        this.firstCube.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                int x_spaceship = model.getPlayer().getSpaceship().getPosX();
                int y_spaceship = model.getPlayer().getSpaceship().getPosY();

                if (model.getState() instanceof Buy && x_spaceship == row && y_spaceship == col) {
                    System.out.println("Ai credo toquei no 1º cubo!");
                    System.out.println("Size -> " + model.getPlayer().getSpaceship().getCargo().size());

                    if (model.getPlayer().getSpaceship().getCargo().size() < 2) {
                        firstCube.setOpaque(false);
                        model.buyCargo(model.getBoard()[row][col].getCubeList().get(0).getColor());
                        revalidate();
                    }
                }
            }
        });

        this.secondCube.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                int x_spaceship = model.getPlayer().getSpaceship().getPosX();
                int y_spaceship = model.getPlayer().getSpaceship().getPosY();
                System.out.println(model.getState());

                if (model.getState() instanceof Buy && x_spaceship == row && y_spaceship == col) {
                    System.out.println("Ai credo toquei no 2º cubo!");
                    if (model.getPlayer().getSpaceship().getCargo().size() < 2) {
                        secondCube.setOpaque(false);
                        model.buyCargo(model.getBoard()[row][col].getCubeList().get(1).getColor());
                        revalidate();
                    }
                }
            }
        }
        );
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            int x_spaceship = model.getPlayer().getSpaceship().getPosX();
            int y_spaceship = model.getPlayer().getSpaceship().getPosY();

            if (model.getBoard()[row][col] != null) {
                if (model.getBoard()[row][col].getIsTurned()) {
                    drawIcon(g, model.getBoard()[row][col].getPlanetName());
                } else {
                    drawIcon(g, "card_down");
                }
                if (x_spaceship == row && y_spaceship == col) {
                    drawIcon(g, "Spaceship_v2");
                }
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            } else {
                drawIcon(g, "Null");
                if (x_spaceship == row && y_spaceship == col) {
                    drawIcon(g, "Spaceship_v2");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        firstCube.setLocation(
                40, 87);
        secondCube.setLocation(
                55, 87);

        if (model.getState() instanceof Move && model.getBoard()[row][col] instanceof Planet) {
            if (model.getBoard()[row][col].getCubeList().size() > 0) {
                firstCube.setBackground(model.getBoard()[row][col].getCubeList().get(0).getColorObject());
                firstCube.setOpaque(true);
            }
            if (model.getBoard()[row][col].getCubeList().size() > 1) {
                secondCube.setBackground(model.getBoard()[row][col].getCubeList().get(1).getColorObject());
                secondCube.setOpaque(true);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (model.getPlayer().isIsAttacked()) {
            model.getPlayer().setIsAttacked(false);
            JOptionPane.showMessageDialog(getParent(), "You have been attacked by a pirate! It made " + model.getPlayer().getPirateDamage() + " damage!");
            model.getPlayer().setPirateDamage(0);
        }
        revalidate();
    }

    private void drawIcon(Graphics g, String name) throws IOException {

        if (name.equals("Spaceship_v2")) {
            g.drawImage(getNave(), 40, 15, getWidth() / 2, getHeight() / 2, null);
        } else if (!name.equals("Null") && name != null) {
            g.drawImage(getCardImage(name), 0, 0, getWidth(), getHeight(), null);
        }
    }

    private Image getNave() throws IOException {
        return ImageIO.read(getClass().getResource("/images/spaceship_final.png"));
    }

    private Image getCardImage(String name) throws IOException {

        if (!name.equals("null") && name != null) {
            return ImageIO.read(getClass().getResource("/images/" + name + ".png"));

        } else {
            return ImageIO.read(getClass().getResource("/images/Null.png"));
        }
    }
};
