// http://www.ucigame.org/gallery.html

import ucigame.*;
@SuppressWarnings("serial")

/* 

Directions:

In the two-player game PineappleTennis, the objective for each player
is to prevent the pineapple from hitting his/her wall.
The players are allowed to move anywhere on the court.
Player one (the green tennis racket) controls the arrow keys in order
to move in the respective directions, while player two (the blue racket)
controls the WASD keys. Each goal is worth one point. The pineapple is
first hit to player one, and after each goal, it goes to the player who
hit the goal. The player who scores ten points first wins the game.
To reset the game, press R on the keyboard.

 */

//The class PineappleTennis is a subclass of the class Ucigame
public class PineappleTennis extends Ucigame
{
	//Creates the Sprite objects pineapple, racket1, racket2, scoreboard
	Sprite pineapple;
	Sprite racket1;
	Sprite racket2;
	Sprite scoreboard;
	
	//Initializes the score of each player to zero
	int p1score, p2score = 0;
	
	/*
	Creates the Sound object rememberTheName, which is Victor Wang's favorite song,
	"Remember the Name" by Fort Minor
	 */
	Sound rememberTheName;
	
	//The void method sets up the program
	public void setup()
	{
		
		framerate(30);
		
		//Sets the background to an image of a tennis court
		Image bkg = getImage("images/tennisct.png");
		canvas.background(bkg);
		
		/*
		 * Makes the pineapple a graphic of a happy pineapple (aka Julia)
		 * and sets the position of the pineapple to the center of the
		 * tennis court
		 * Sets the x and y motion amounts for the pineapple
		 * Initially moves toward player one
		 */
		pineapple = makeSprite(getImage("images/pine.png"));
		pineapple.position(canvas.width()/2-30, canvas.height()/2-10);
		pineapple.motion(70, 4);
		
		/*
		 * Makes racket one a graphic of a green tennis racket
		 * and sets the position to the left baseline of the tennis court
		 */
		racket1 = makeSprite(getImage("images/green.png"));
		racket1.position(canvas.width() - racket1.width() + 80,
		(canvas.height() - racket1.height()) / 2);

		/*
		 * Makes racket two a graphic of a blue tennis racket
		 * and sets the position to the right baseline of the tennis court
		 */
		racket2 = makeSprite(getImage("images/blue.png"));
		racket2.position(0,
	    (canvas.height() - racket2.height()) / 2);

		/*
		 * Creates the score board and sets the position to the top of the
		 * page
		 * Makes the font Arial, bold, size 60, and the color black
		 */
		scoreboard = makeSprite(getImage("images/score.png"));
		scoreboard.position(688, 3);
		scoreboard.font("Arial", BOLD, 60, 0, 0, 0);
		
		//Sets the sound to "Remember the Name" and plays it in a loop
		rememberTheName = getSound("sounds/RTN.mp3");
		rememberTheName.loop();

		
	}
	
	public void draw()
	{
		//Clears the screen
		canvas.clear();
		
		//Draws the score board and puts the scores of both players on the board
		scoreboard.draw();
		scoreboard.putText(p1score, 63, 85);
		scoreboard.putText(p2score, 237, 85);
		
		/*
		 * Draws and moves the pineapple
		 * Makes the pineapple bounce if it comes into contact with either racket
		 * or any of the four walls of the court
		 */
		pineapple.move();
		/*
		 * PIXELPERFECT tests whether the pineapple overlaps with either racket.
		 * If there is an overlap, Ucigame changes the position and
		 * speed of the pineapple so that it "bounces".
		 */
		pineapple.bounceIfCollidesWith(racket1, PIXELPERFECT);
		pineapple.bounceIfCollidesWith(racket2, PIXELPERFECT);
		pineapple.bounceIfCollidesWith(TOPEDGE, BOTTOMEDGE, LEFTEDGE, RIGHTEDGE);
		pineapple.draw();

		/*
		 * Draws the rackets
		 * Prevents them from coming into contact with any of the four walls
		 */
		racket1.stopIfCollidesWith(TOPEDGE, BOTTOMEDGE, LEFTEDGE, RIGHTEDGE);
		racket2.stopIfCollidesWith(TOPEDGE, BOTTOMEDGE, LEFTEDGE, RIGHTEDGE);
		racket1.draw();
		racket2.draw();
		
		/*
		 * Player Two scores a goal if the pineapple collides with the left wall,
		 * and his/her score increases by one
		 * The pineapple ball is then fed to Player Two for the next point (hence the positive 70)
		 */
		pineapple.checkIfCollidesWith(LEFTEDGE);
		if (pineapple.collided()) {
		p2score++;
		//Resets the position of the pineapple and rackets
		pineapple.position(canvas.width()/2-30, canvas.height()/2-10);
		racket1.position(canvas.width() - racket1.width() + 80,
		(canvas.height() - racket1.height()) / 2);
		racket2.position(0,
		(canvas.height() - racket2.height()) / 2);	
		pineapple.motion(70, 4);
		}
		
		/*
		 * Player One scores a goal if the pineapple collides with the right wall,
		 * and his/her score increases by one
		 * The pineapple ball is then fed to Player One for the next point (hence the negative 70)
		 */
		pineapple.checkIfCollidesWith(RIGHTEDGE);
		if (pineapple.collided()) {
		p1score++;
		//Resets the position of the pineapple and rackets
		pineapple.position(canvas.width()/2-30, canvas.height()/2-10);
		racket1.position(canvas.width() - racket1.width() + 80,
		(canvas.height() - racket1.height()) / 2);
		racket2.position(0,
		(canvas.height() - racket2.height()) / 2);					
		pineapple.motion(-70, 4);
		}
		
		/*
		 * When one player reaches ten points, the game ends, and all objects are hidden from view.
		 * If player one wins, an image reading "PLAYER ONE WINS! SUCKS TO BE YOU PLAYER TWO" is
		 * displayed. If player two wins, an image reading "PLAYER TWO WINS! SUCKS TO BE YOU PLAYER ONE"
		 * is displayed. These displays are not meant to make the losing player lose self confidence.
		 * Everyone is a winner.
		 */
		if (p1score == 10) {
			pineapple.hide();
			racket1.hide();
			racket2.hide();
			Image bkg1 = getImage("images/bkg1.png");
			canvas.background(bkg1);
		}
		if (p2score == 10) {
			pineapple.hide();
			racket1.hide();
			racket2.hide();
			Image bkg2 = getImage("images/bkg2.png");
			canvas.background(bkg2);
		}
	
	}

	public void onKeyPress()
	{
		//Moves racket one with the arrow keys
		if (keyboard.isDown(keyboard.UP))
			racket1.nextY(racket1.y() - 8);
		if (keyboard.isDown(keyboard.DOWN))
			racket1.nextY(racket1.y() + 8);
		if (keyboard.isDown(keyboard.LEFT))
			racket1.nextX(racket1.x() - 8);
		if (keyboard.isDown(keyboard.RIGHT))
			racket1.nextX(racket1.x() + 8);
		
		//Moves racket two with the WASD keys
		if (keyboard.isDown(keyboard.W))
			racket2.nextY(racket2.y() - 8);
		if (keyboard.isDown(keyboard.S))
			racket2.nextY(racket2.y() + 8);
		if (keyboard.isDown(keyboard.A))
			racket2.nextX(racket2.x() - 8);
		if (keyboard.isDown(keyboard.D))
			racket2.nextX(racket2.x() + 8);
		
		//Resets the game when the R key is pressed so that the players can have more fun
		if (keyboard.isDown(keyboard.R))
		{
			Image bkg = getImage("images/tennisct.png");
			canvas.background(bkg);
			pineapple.show();
			pineapple.position(canvas.width()/2-30, canvas.height()/2-10);
			pineapple.motion(70, 4);
			racket1.show();
			racket1.position(canvas.width() - racket1.width() + 80,
			(canvas.height() - racket1.height()) / 2);
			racket2.position(0,
			(canvas.height() - racket2.height()) / 2);					
			racket2.show();
			p1score = 0;
			p2score = 0;
			
		}
	
	}
}
