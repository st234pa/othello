import java.util.Scanner;
import java.util.PriorityQueue;
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
			System.out.print("\033[H\033[2J");
			System.out.flush();
			System.out.println(_board);
			playerTurn();
			if (_tiles < 64) 
				computerTurn();
		}
		showResults();
	}
	public void playerTurn() {
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
	public void computerTurn() {
		System.out.println("Computer's turn.");
		_board = bestMove(possibleMoves(_board));
		System.out.println(_board);
	}
	public static boolean validMove(Board b, int r, int c) {
		return true;
	}
	public static boolean goodFlip(Board b, int r, int c, int ud, int lr) {
		return true;
	}
	public static Board flip(Board b, int r, int c,  int ud, int lr) {
		return null;
	}
	public static Board makeMove(Board b, int r, int c) {
		return null;
	}
	public static PriorityQueue<Board> possibleMoves(Board b) {
		return null;
	}
	public static Board bestMove(PriorityQueue<Board> moves) {
		return moves.peek();
	}
	public void showResults() {
		System.out.println("Game over.");
		int player = 0;
		int computer = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (_board.get(i,j).equals("x")) player++;
				else if (_board.get(i,j).equals("o")) computer++;
			}
		}
		if (player > computer) System.out.println("You win!");
		else if (player < computer) System.out.println("You lose. :(");
		else System.out.println("Draw.");
	}
}