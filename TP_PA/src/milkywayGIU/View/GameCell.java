package milkywayGIU.View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import milkywayGIU.Model.Model;
import milkyway_logic.cards.Planet;
import milkyway_logic.elements.Cube;
import milkyway_logic.states.Move;
import util.Constants;

public class GameCell extends JPanel implements Observer {

    private int row, col;
    private Model model;

    GameCell(Model j, int r, int c) {
        this.row = r;
        this.col = c;
        this.model = j;

        this.setPreferredSize(new Dimension(Constants.CARD_WIDTH, Constants.CARD_HEIGHT));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                System.out.println(model.getState());

                if (model.getState() instanceof Move) {
                    model.move(row, col);
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
        super.paintComponent(g);
        try {
            int x_spaceship = model.getPlayer().getSpaceship().getPosX();
            int y_spaceship = model.getPlayer().getSpaceship().getPosY();

            if (x_spaceship == row && y_spaceship == col) {
                if (model.getBoard()[row][col] != null) {
                    if (model.getBoard()[row][col].getIsTurned()) {
                        drawIcon(g, model.getBoard()[row][col].getPlanetName());
                        if (this.model.getBoard()[row][col] instanceof Planet) {
                            /*for (int i = 0; i < this.model.getBoard()[row][col].getCubeList().size(); i++) {
                                drawIcon(g, this.model.getBoard()[row][col].getCubeList().get(i).getColor());
                            }*/
                        }
                    } else {
                        drawIcon(g, "card_down");
                    }
                    drawIcon(g, "Spaceship_v2");
                } else {
                    drawIcon(g, "Null");
                    drawIcon(g, "Spaceship_v2");
                }
            } else {
                if (model.getBoard()[row][col] != null) {
                    if (model.getBoard()[row][col].getIsTurned()) {
                        drawIcon(g, model.getBoard()[row][col].getPlanetName());
                    } else {
                        drawIcon(g, "card_down");
                    }
                } else {
                    drawIcon(g, "Null");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
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
            System.out.println("nome -> " + name);
            Image img = ImageIO.read(getClass().getResource("/images/" + name + ".png"));
            return img;
        } else {
            Image img = ImageIO.read(getClass().getResource("/images/Null.png"));
            img.flush();
            return img;
        }
    }
};
