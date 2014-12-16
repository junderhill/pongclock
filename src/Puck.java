/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 15/12/2011
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public class Puck implements GameObject {
    public int posX;
    public int posY;

    public int speedX;
    public int speedY;

    public final int Height = 10;
    public final int Width = 10;


    private PongGame game;

    public Puck(PongGame pongGame) {
        game = pongGame;

        //Set starting position.
        posX = (pongGame.gameWidth / 2) - (this.Width / 2);
        posY = (pongGame.gameHeight / 2) - (this.Height / 2);

        //Starting Speed
        speedX = 5;
        speedY = 3;

    }


    public DirectionOfTravel GetDirectionOfTravel() {
        if (speedX > 0) {
            return DirectionOfTravel.Right;
        } else {
            return DirectionOfTravel.Left;
        }
    }

    public void ReverseY(){
        this.speedY = this.speedY * -1;
    }
}
