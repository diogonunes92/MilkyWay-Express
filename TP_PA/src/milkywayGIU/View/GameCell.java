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
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import milkyway_logic.cards.Planet;
import milkyway_logic.states.Buy;
import milkyway_logic.states.Move;
import util.Constants;

public class GameCell extends JPanel implements Observer {

    private int row, col;
    private Model model;
    private JPanel firstCube;
    private JPanel secondCube;

    GameCell(Model j, int r, int c) {
        this.row = r;
        this.col = c;
        this.model = j;

        this.setPreferredSize(new Dimension(Constants.CARD_WIDTH, Constants.CARD_HEIGHT));

        setupCubes();
        registerListeners();
    }

    private void registerListeners() {

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                System.out.println(model.getState());
                if (model.getState() instanceof Move) {
                    model.move(row, col);
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

            if (x_spaceship == row && y_spaceship == col) {
                if (model.getBoard()[row][col] != null) {
                    if (model.getBoard()[row][col].getIsTurned()) {
                        drawIcon(g, model.getBoard()[row][col].getPlanetName());
                    } else {
                        drawIcon(g, "card_down");
                    }
                    drawIcon(g, "Spaceship_v2");
                } else {
                    drawIcon(g, "Null");
                    drawIcon(g, "Spaceship_v2");
                }
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            } else {
                if (model.getBoard()[row][col] != null) {
                    if (model.getBoard()[row][col].getIsTurned()) {
                        drawIcon(g, model.getBoard()[row][col].getPlanetName());
                    } else {
                        drawIcon(g, "card_down");
                    }
                    g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                } else {
                    drawIcon(g, "Null");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        firstCube.setLocation(27, 65);
        secondCube.setLocation(42, 65);

        if (model.getState() instanceof Move) {
            if (model.getBoard()[row][col] instanceof Planet) {
                if (model.getBoard()[row][col].getCubeList().size() > 0) {

                    switch (model.getBoard()[row][col].getCubeList().get(0).getColor()) {
                        case "red":
                            firstCube.setBackground(Color.red);
                            firstCube.setOpaque(true);
                            break;
                        case "blue":
                            firstCube.setLocation(27, 65);
                            firstCube.setBackground(Color.blue);
                            firstCube.setOpaque(true);
                            break;
                        case "yellow":
                            firstCube.setLocation(27, 65);
                            firstCube.setBackground(Color.yellow);
                            firstCube.setOpaque(true);
                            break;
                        case "black":
                            if (!model.getBoard()[row][col].isPirate()) {
                                firstCube.setLocation(27, 65);
                                firstCube.setBackground(Color.black);
                                firstCube.setOpaque(true);
                            } else {
                                firstCube.setLocation(35, 65);
                                firstCube.setBackground(Color.black);
                                firstCube.setOpaque(true);
                            }
                            break;
                        case "white":
                            firstCube.setLocation(27, 65);
                            firstCube.setBackground(Color.white);
                            firstCube.setOpaque(true);
                            break;
                    }
                }
                if (model.getBoard()[row][col].getCubeList().size() > 1) {

                    switch (model.getBoard()[row][col].getCubeList().get(1).getColor()) {
                        case "red":
                            secondCube.setBackground(Color.red);
                            secondCube.setOpaque(true);
                            break;
                        case "blue":
                            secondCube.setLocation(42, 65);
                            secondCube.setBackground(Color.blue);
                            secondCube.setOpaque(true);
                            break;
                        case "yellow":
                            secondCube.setLocation(42, 65);
                            secondCube.setBackground(Color.yellow);
                            secondCube.setOpaque(true);
                            break;
                        case "black":
                            secondCube.setLocation(42, 65);
                            secondCube.setBackground(Color.black);
                            secondCube.setOpaque(true);
                            break;
                        case "white":
                            secondCube.setLocation(42, 65);
                            secondCube.setBackground(Color.white);
                            secondCube.setOpaque(true);
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        revalidate();
    }

    private void drawIcon(Graphics g, String name) throws IOException {

        if (name.equals("Spaceship_v2")) {
            Image img = getNave();
            g.drawImage(img, 40, 15, getWidth() / 2, getHeight() / 2, null);
        } else if (!name.equals("Null") && name != null) {
            Image img = getCardImage(name);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        } else if (name.equals("red")) {
            Image img = getCardImage(name);
            g.drawImage(img, 50, 20, getWidth() / 2, getHeight() / 2, null);
        } else if (name.equals("yellow")) {
            Image img = getCardImage(name);
            g.drawImage(img, 50, 20, getWidth() / 2, getHeight() / 2, null);
        } else if (name.equals("black")) {
            Image img = getCardImage(name);
            g.drawImage(img, 50, 20, getWidth() / 2, getHeight() / 2, null);
        } else if (name.equals("white")) {
            Image img = getCardImage(name);
            g.drawImage(img, 50, 20, getWidth() / 2, getHeight() / 2, null);
        } else if (name.equals("blue")) {
            Image img = getCardImage(name);
            g.drawImage(img, 50, 20, getWidth() / 2, getHeight() / 2, null);
        }
        //validate();
    }

    private Image getNave() throws IOException {
        Image img = ImageIO.read(getClass().getResource("/images/Spaceship_v2.png"));
        return img;
    }

    private Image getCardImage(String name) throws IOException {

        if (!name.equals("null") && name != null) {
            //System.out.println("nome -> " + name);
            Image img = ImageIO.read(getClass().getResource("/images/" + name + ".png"));
            return img;
        } else {
            Image img = ImageIO.read(getClass().getResource("/images/Null.png"));
            img.flush();
            return img;
        }
    }

    private void setupCubes() {
        firstCube = new JPanel();
        firstCube.setBackground(Color.gray);
        secondCube = new JPanel();
        secondCube.setBackground(Color.gray);
        firstCube.setOpaque(false);
        secondCube.setOpaque(false);
        this.add(firstCube);
        this.add(secondCube);
    }

    private void setupCubeListeners() {

    }
};
