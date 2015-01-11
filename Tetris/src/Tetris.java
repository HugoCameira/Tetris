import static isel.leic.pg.Console.NO_KEY;
import isel.leic.pg.Console;

import java.awt.event.KeyEvent;

public class Tetris {
	private static final int STEP_TIME = 1000; // 1 second by each step

	/** 
	 * The game state
	 */
	private static long nextStepTime; // Time to call next step()
	private static Piece piece;		  // Current piece controlled
	private static boolean gameOver = false;

	public static void main(String[] args) {
		init();
		piece = new Piece();	// The first piece
		piece.show();
		nextStepTime = System.currentTimeMillis() + STEP_TIME;
		Console.startMusic("TetrisTheme");
		run();					// Run de game
		terminate();
	}

	private static final int 
	    LINES = Board.DIM_LINES+1, COLS = Board.DIM_COLS+24;
	
	private static void init() {
		Console.open("PG Tetris", LINES, COLS);
		Console.exit(true);					// Enable exit console
		Board.drawGrid();					// Draw initial board
		Board.drawRightGrid();
		Board.drawRightCommands();
	}

	private static final String GAME_OVER_TXT = "GAME  OVER";
	
	private static void terminate() {
		Console.cursor(LINES/2, (COLS-GAME_OVER_TXT.length())/2);
		Console.color(Console.RED, Console.YELLOW);
		Console.print(GAME_OVER_TXT);		// Message GAME OVER
		while (Console.isKeyPressed()) ;	// Wait if key is pressed
		Console.waitKeyPressed(20000);		// Wait 20 seconds for any key 
		Console.close();					// Close Console window
	}

	private static void run() {
		int key = NO_KEY;
		long waitTime; 						// Time to wait for next step
		do {
			waitTime = nextStepTime - System.currentTimeMillis();
			if (waitTime <= 0) 
				step();						// Next step of the game
			else {
				key = Console.waitKeyPressed(waitTime);
				if (key!=NO_KEY) {  		// A key was pressed ? 
					action(key);			// Do action for the key
					while (Console.isKeyPressed(key))  // Wait to release key
						if (System.currentTimeMillis() >= nextStepTime)
							step();			// Next step with the key held down
				}
			}
		} while (!gameOver);
	}

	private static void step() {
		if (! piece.down()) {   	// If possible, move the piece down  
			//piece.hide();			// Hide the piece that hit bottom 	
			Console.stopMusic();
			Console.playSound("drop");
			piece = new Piece();	// Create a new piece of random type
			piece.show();			// Show the new piece
		}
		nextStepTime += STEP_TIME;  // Set next time to move the piece
	}

	private static void action(int key) {
		switch (key) {
		case KeyEvent.VK_LEFT: 	piece.moveLeft(); break;
		case KeyEvent.VK_RIGHT:	piece.moveRight(); break;
		case KeyEvent.VK_Q:		piece.rotateLeft();	Console.playSound("rotate"); break;
		case KeyEvent.VK_W:		piece.rotateRight(); Console.playSound("rotate"); break;
		case KeyEvent.VK_DOWN:	piece.down(); break;
		case KeyEvent.VK_ESCAPE: gameOver=true; break;
		}
	}
}
