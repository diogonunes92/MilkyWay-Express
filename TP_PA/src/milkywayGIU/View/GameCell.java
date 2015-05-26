package milkywayGIU.View;

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
import milkyway_logic.states.Move;
import util.Constants;

public class GameCell extends JPanel implements Observer {

    private int row, col;
    private Model model;
    private String namePlanet;

    GameCell(Model j, int r, int c, String name) {

        this.row = r;
        this.col = c;
        this.model = j;
        this.namePlanet = name;

        this.setPreferredSize(new Dimension(Constants.CARD_WIDTH, Constants.CARD_HEIGHT));

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
            this.drawIcon(g, namePlanet);
            int x_spaceship = model.getPlayer().getSpaceship().getPosX();
            int y_spaceship = model.getPlayer().getSpaceship().getPosY();

            if (x_spaceship == row && y_spaceship == col) {
                this.drawIcon(g, "Spaceship_v2");
            } else {
                this.drawIcon(g, namePlanet);
            }

        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO: analyse this method. Is printing the board 
        
        revalidate();
    }

    private void drawIcon(Graphics g, String name) throws IOException {

        if (name.equals("Spaceship_v2")) {
            Image img = getNave();
            g.drawImage(img, 40, 15, getWidth() / 2, getHeight() / 2, null);

        } else if (!name.equals("Null") && name != null) {
            Image img = getCardImage(name);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        }
        //validate();
    }

    private Image getNave() throws IOException {
        Image img = ImageIO.read(getClass().getResource("/images/Spaceship_v2.png"));
        return img;
    }

    private Image getCardImage(String namePlanet) throws IOException {

        if (!namePlanet.equals("null") && namePlanet != null) {

            System.out.println("nome -> " + namePlanet);
            Image img = ImageIO.read(getClass().getResource("/images/" + namePlanet + ".png"));
            return img;
        } else {
            Image img = ImageIO.read(getClass().getResource("/images/Null.png"));
            img.flush();
            return img;
        }
    }
};
