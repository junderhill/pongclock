import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: jason
 * Date: 15/12/2011
 * Time: 13:45
 * To change this template use File | Settings | File Templates.
 */
public class PongGame {
    public final int gameHeight = 480;
    public final int gameWidth = 640;

    public Paddle leftPaddle;
    public Paddle rightPaddle;
    public Puck puck;

    private Date previousTime;
    private Side sideToWin;

    public PongGame() {
        Reset();
        sideToWin = Side.None;
        previousTime = new Date();
    }

    public void Reset() {
        leftPaddle = new Paddle(this, Side.Left);
        rightPaddle = new Paddle(this, Side.Right);
        puck = new Puck(this);
    }

    public void RunGameLoop() {
        CheckForWinner();
        CheckNextMoveForBoundaryCollision();
        MovePuck();
        MovePaddles();
        CheckForPaddleCollision();
    }

    private void MovePaddles() {
        if (puck.GetDirectionOfTravel() == DirectionOfTravel.Right) {
            if (sideToWin != Side.Right) {
                rightPaddle.MoveTowardsPuck(puck);
            }
            else{
                rightPaddle.MoveAwayFromPuck(puck);
            }
        } else {
            if (sideToWin != Side.Left) {
                leftPaddle.MoveTowardsPuck(puck);
            }
            else{
                leftPaddle.MoveAwayFromPuck(puck);
            }
        }
    }

    private void CheckForPaddleCollision() {
        if ((puck.posX <= (this.leftPaddle.posX + this.leftPaddle.Width)) && (puck.posY >= this.leftPaddle.posY) && ((puck.posY + puck.Height) <= this.leftPaddle.posY + this.leftPaddle.Height)) {
            //Paddle collision left paddle
            puck.speedX = puck.speedX * -1;
        }

        if (((puck.posX + puck.Width) >= this.rightPaddle.posX) && (puck.posY >= this.rightPaddle.posY) && ((puck.posY + puck.Height) <= this.rightPaddle.posY + this.rightPaddle.Height)) {
            //Paddle collision right paddle
            puck.speedX = puck.speedX * -1;
        }
    }

    private void MovePuck() {
        puck.posX += puck.speedX;
        puck.posY += puck.speedY;
    }

    private void CheckNextMoveForBoundaryCollision() {
        //Check for collisions with the top and bottom boundaries of the game area.
        if ((puck.posY + puck.speedY + puck.Height) >= gameHeight) {
            puck.ReverseY();
        }

        if ((puck.posY + puck.speedY) <= 0) {
            puck.ReverseY();
        }
    }

    private void CheckForWinner() {
        Date currentTime = new Date();
        if (previousTime.getHours() != currentTime.getHours()) {
            sideToWin = Side.Left;
        } else if (previousTime.getMinutes() != currentTime.getMinutes()) {
            sideToWin = Side.Right;
        } else {
            sideToWin = Side.None;
        }
        previousTime = currentTime;
    }
}
