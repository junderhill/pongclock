import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 15/12/2011
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public class PongClock {
    private static PongGame game;

    public static void main(String[] args) throws InterruptedException {
        game = new PongGame();

        JFrame mainWindow = CreateWindow();

        while (true) {
            game.RunGameLoop();
            PaintDisplay(mainWindow.getGraphics());
            Thread.sleep(30);
        }
    }

    private static void PaintDisplay(Graphics g) {

        //Color background
        g.setColor(Color.black);
        g.fillRect(0, 0, game.gameWidth, game.gameHeight);

        //Draw Time
        //TODO: Time doesn't appear to be drawn.
        g.setColor(Color.lightGray);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        String timeText = simpleDateFormat.format(new Date());
        g.drawString(timeText, 10, 10);

        //Draw game objects
        g.setColor(Color.white);
        g.fillRect(game.puck.posX, game.puck.posY, game.puck.Width, game.puck.Height);
        g.fillRect(game.leftPaddle.posX, game.leftPaddle.posY, game.leftPaddle.Width, game.leftPaddle.Height);
        g.fillRect(game.rightPaddle.posX, game.rightPaddle.posY, game.rightPaddle.Width, game.rightPaddle.Height);
    }

    private static JFrame CreateWindow() {
        JFrame mainWindow = new JFrame("Pong Clock");
        int windowHeight = game.gameHeight;
        int windowWidth = game.gameWidth;
        mainWindow.setBounds(0, 0, windowWidth, windowHeight);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
        return mainWindow;
    }
}
