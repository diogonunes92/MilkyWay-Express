package milkyway_logic.gameplanner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import milkyway_logic.cards.Card;
import milkyway_logic.elements.Player;
import milkyway_logic.elements.Spaceship;
import milkyway_logic.gameboard.BoardConstructor;
import milkyway_logic.states.StartGame;
import milkyway_logic.states.State;
import util.Constants;

public final class Game {

    private State state;
    private Player player;
    private Card[][] board;
    private static int bankCoins;

    private static int roundsPlayed = 0;

    public Game() {
        state = new StartGame(this);
    }

    public boolean initialize() {

        BoardConstructor mBoardConstructor = new BoardConstructor();
        this.setBoard(mBoardConstructor.getGameBoard());
        this.setBankCoins(Constants.INITIAL_PLAYER_COINS - Constants.INITIAL_PLAYER_COINS);

        this.setPlayer(new Player(1, "player_1", Constants.INITIAL_PLAYER_COINS, new Spaceship(Constants.INITIAL_SPACESHIP_POSITION_X, Constants.INITIAL_SPACESHIP_POSITION_Y)));

        return true;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Card[][] getBoard() {
        return board;
    }

    public void setBoard(Card[][] board) {
        this.board = board;
    }

    public static int getBankCoins() {
        return bankCoins;
    }

    public static void setBankCoins(int bankCoins) {
        Game.bankCoins = bankCoins;
    }

    public void constructGame() {
        this.state = state.constructGame();
    }

    public void move(String move) {
        this.state = state.move(move);
    }

    public void upgradeWeapon() {
        this.state = state.upgradeWeapon();
    }

    public void upgradeCargo() {
        this.state = state.upgradeCargo();
    }

    public void buyCargo(String carga) {
        this.state = state.buyCargo(carga);
    }

    public void sellCargo(String carga) {
        this.state = state.sellCargo(carga);
    }

    public void isFinished() {
        this.state = state.isFinished();
    }

    public void pirateAtack() {
        this.state = state.pirateAtack();
    }

    public void nextState() {
        this.state = state.nextState();
    }

    public void replenishMarkets() {
        this.state = state.replenishMarkets();
    }

    public void turnCards() {
        this.state = state.turnCards();
    }

    public static int getRoundsPlayed() {
        return roundsPlayed;
    }

    public static void setRoundsPlayed() {
        Game.roundsPlayed++;
    }

    public boolean moveSpaceship(String move) {

//        TODO: check how can we verify the limits from here
        if (move.equalsIgnoreCase("f") && this.getBoard()[this.getPlayer().getSpaceship().getPosX() - 1][this.getPlayer().getSpaceship().getPosY()] instanceof Card && this.isInsideLimits(this.getPlayer().getSpaceship().getPosX() - 1, this.getPlayer().getSpaceship().getPosY())) {
            this.getPlayer().getSpaceship().setPosX(-1);

        } else if (move.equalsIgnoreCase("b") && this.getBoard()[this.getPlayer().getSpaceship().getPosX()][this.getPlayer().getSpaceship().getPosY() + 1] instanceof Card && this.isInsideLimits(this.getPlayer().getSpaceship().getPosX(), this.getPlayer().getSpaceship().getPosY() + 1)) {
            this.getPlayer().getSpaceship().setPosY(1);

        } else if (move.equalsIgnoreCase("l") && this.getBoard()[this.getPlayer().getSpaceship().getPosX()][this.getPlayer().getSpaceship().getPosY() - 1] instanceof Card) {
            this.getPlayer().getSpaceship().setPosY(-1);

        } else if (move.equalsIgnoreCase("r") && this.getBoard()[this.getPlayer().getSpaceship().getPosX()][this.getPlayer().getSpaceship().getPosY() + 1] instanceof Card) {
            this.getPlayer().getSpaceship().setPosY(1);
        }
        this.getPlayer().setCoins(-1);

        return true;

    }

    public boolean isInsideLimits(int posX, int posY) {

        if (posX <= Constants.BOARD_LIMIT_INF_X || posX > Constants.BOARD_LIMIT_SUP_X) {
            return false;

        } else if (posY <= Constants.BOARD_LIMIT_INF_Y || posY > Constants.BOARD_LIMIT_SUP_Y) {
            return false;
        }

        return true;
    }

    public void saveGame() throws IOException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("save_file_milky_way.txt"));

        objectOutputStream.writeObject(player);
        objectOutputStream.writeInt(roundsPlayed);
        objectOutputStream.write(bankCoins);

// create two students objects and add them in a list. create a course
// object and add the list of students to a list
//        Student student1 = new Student();
//        student1.setAge(30);
//        student1.setName("Student1");
//
//        Student student2 = new Student();
//        student2.setAge(31);
//        student2.setName("Student2");
//
//        Course course = new Course();
//        course.setName("Java IO");
//        List<Student> students = new ArrayList<>();
//        students.add(student1);
//        students.add(student2);
//        course.setStudents(students);
// write the course object to the objectoutputstream. This writes the
// object as well all objects that it referes to.
// it writes only those objects that implement serializable
//        objectOutputStream.writeObject(course);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public void loadGame() throws IOException {
// the object input stream reads the objects back from the file and
// creates java objects out of them. It recreates all
// references that were present when the objects were written
//        ObjectInputStream objectInputStream = new ObjectInputStream(
//                new FileInputStream("javaObjects.txt"));
//
//// start getting the objects out in the order in which they were written
//        Date date = (Date) objectInputStream.readObject();
//        System.out.println(date);
//        System.out.println(objectInputStream.readBoolean());
//        System.out.println(objectInputStream.readFloat());
//
//// get the course object
//        Course readCourse = (Course) objectInputStream.readObject();
//        System.out.println(readCourse.getName());
//        Student student1Read = readCourse.getStudents().get(0);
//        System.out.println(student1Read.getAge());
//        System.out.println(student1Read.getName());
//        objectInputStream.close();
    }

}
