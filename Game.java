import java.util.Scanner;
public class Game {
	private Board _board;
	private int _consecPasses, _n, _tiles;
	public Game(Board b, int n) {
		_board = b;
		_consecPasses = 0;
		_n = n;
		_tiles = 4;
	}
	public void run() {
		while (_tiles < 64 && _consecPasses < 2) {
			playerTurn();
		}
	}
	public void playerTurn() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println("Player's turn.");
		Scanner scan = new Scanner(System.in);
		System.out.print("Row: ");
		int r = scan.nextInt();
		System.out.print("Column: ");
		int c = scan.nextInt();
		if (validMove(_board, r, c)) {
			_board = makeMove(_board, r, c);
		}
		else _consecPasses++;
	}
	public static boolean validMove(Board b, int r, int c) {

	}
	public static Board makeMove(Board b, int r, int c) {
		
	}
}