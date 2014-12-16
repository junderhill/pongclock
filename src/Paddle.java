/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 15/12/2011
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public class Paddle implements GameObject {

    public int posX;
    public int posY;

    public int speedY;

    public final int Height = 60;
    public final int Width = 10;

    private PongGame game;

    public Paddle(PongGame pongGame, Side side) {
        game = pongGame;

        speedY = 5;
        switch (side) {
            case Left:
                posX = 10;
                posY = (pongGame.gameHeight / 2) - (this.Height / 2);
                break;
            case Right:
                posX = pongGame.gameWidth - Width - 10;
                posY = (pongGame.gameHeight / 2) - (this.Height / 2);
                break;
        }


    }


    public void MoveTowardsPuck(Puck puck) {
        if (this.PuckIsInlineWithPaddle()) {
            //No need to move as the paddle is inline.
        } else if ((this.posY + this.Height) > puck.posY) {
            this.Move(VerticalDirection.Down);
        } else if ((this.posY) < puck.posY) {
            this.Move(VerticalDirection.Up);
        }
    }

    private boolean PuckIsInlineWithPaddle() {
        int PuckYPosition = game.puck.posY;
        int PuckHeight = game.puck.Height;

        return (PuckYPosition <= this.posY) && ((PuckYPosition + PuckHeight) >= (this.posY + this.Height));
    }

    private void Move(VerticalDirection verticalDirection) {
        if (SpaceToMove(verticalDirection)) {
            if (verticalDirection == VerticalDirection.Up) {
                this.posY -= this.speedY;
            } else {
                this.posY += this.speedY;
            }
        }
    }

    private boolean SpaceToMove(VerticalDirection verticalDirection) {
        if (verticalDirection == VerticalDirection.Up) {
            return (this.posY - this.speedY) >= 0;
        } else {
            return ((this.posY + this.Height) + this.speedY) <= game.gameHeight;
        }

    }

    public void MoveAwayFromPuck(Puck puck) {
        if(this.PuckIsInlineWithPaddle())
        {
            if(this.SpaceToMove(VerticalDirection.Up))
            {
                this.Move(VerticalDirection.Up);
            }else{
                this.Move(VerticalDirection.Down);
            }
        }
        else if(this.posY < puck.posY)
        {
            this.Move(VerticalDirection.Down);
        }
        else if(this.posY > puck.posY)
        {
            this.Move(VerticalDirection.Up);
        }
    }
}