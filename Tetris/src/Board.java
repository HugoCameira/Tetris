import isel.leic.pg.Console;

public class Board {
	public static final int BASE_LINE = 0, BASE_COL = 1, DIM_LINES = 20,
			DIM_COLS = 10;

	public static void drawGrid() {
		Console.color(Console.WHITE, Console.BLACK);
		for (int l = 0; l < DIM_LINES; ++l) {
			for (int c = 0; c < DIM_COLS; ++c) {
				put(BASE_LINE + l, BASE_COL + c, '.');
			}
		}
		Console.color(Console.BLACK, Console.WHITE);
		for (int l = 0; l < DIM_LINES; ++l) {
			put(BASE_LINE + l, BASE_COL - 1, '|');
		}
		for (int l = 0; l < DIM_LINES; ++l) {
			put(BASE_LINE + l, DIM_COLS + 1, '|');
		}
		for (int c = 0; c < DIM_COLS; ++c) {
			put(DIM_LINES, BASE_COL + c, '-');
		}
		put(DIM_LINES , BASE_COL - 1, '+');
		put(DIM_LINES , DIM_COLS + 1, '+');
	}
	
	public static void drawRightGrid(){
		Console.color(Console.WHITE, Console.BLACK);
		Console.cursor(BASE_LINE + 1, DIM_COLS + 3);
		Console.print("Score:");
		Console.cursor(BASE_LINE + 1, DIM_COLS + 14);
		Console.print("Lines:");
		Console.cursor(BASE_LINE + 2, DIM_COLS + 14);
		Console.print("Level:");
		Console.cursor(BASE_LINE + 6, DIM_COLS + 3);
		Console.print("Next");
	}
	public static void drawRightCommands(){
		Console.color(Console.WHITE, Console.GRAY);
		Console.cursor(DIM_LINES - 7, DIM_COLS + 3);
		Console.print(" Left - Move left   ");
		Console.cursor(DIM_LINES - 6, DIM_COLS + 3);
		Console.print("Right - Move right  ");
		Console.cursor(DIM_LINES - 5, DIM_COLS + 3);
		Console.print("    Q - Rotate left ");
		Console.cursor(DIM_LINES - 4, DIM_COLS + 3);
		Console.print("    W - Rotate right");
		Console.cursor(DIM_LINES - 3, DIM_COLS + 3);
		Console.print(" Down - Move Down   ");
		Console.cursor(DIM_LINES - 2, DIM_COLS + 3);
		Console.print("Space - Drop        ");
		Console.cursor(DIM_LINES - 1, DIM_COLS + 3);
		Console.print("  Esc - Terminate   ");
	}
	private static void put(int line, int col, char c) {
		Console.cursor(line, col);
		Console.print(c);
	}

	public static boolean validPosition(int line, int col) {
		return line >= 0 && line < DIM_LINES && col >= 0 && col < DIM_COLS;
	}
	
	//public static boolean colision(){
		//color[] grid = new color[10];
		
	//}
}
