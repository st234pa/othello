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
			Board inverse = _board.inverse();
			if (_tiles < 64 && _consecPasses < 2) 
				computerTurn(inverse);
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
			_tiles++;
			_consecPasses = 0;
		}
		else _consecPasses++;
		System.out.println(_board);
	}
	public void computerTurn(Board inverse) {
		System.out.println("Computer's turn...");
		PriorityQueue<Board> pm = possibleMoves(inverse);
		if (pm.size() != 0) {
			_board = (bestMove(pm)).inverse();
			_tiles++;
			_consecPasses = 0;
		}
		else _consecPasses++;
		System.out.println(_board);
	}
	public boolean validMove(Board b, int r, int c) {
		
		return true;
	}
	public boolean goodFlip(Board b, int r, int c, int ud, int lr) {
		
		return false;
	}
	public Board flip(Board b, int r, int c,  int ud, int lr) {
		
		return b;
	}
	public Board makeMove(Board b, int r, int c) {
		b.set(r,c, "x");
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!(b.get(r+i,c+j).equals("x"))) {
					if (goodFlip(b, r, c, i, j)) b = flip(b, r, c, i, j);
				}
			}
		}
		return b;
	}
	public void calculate(Board b, int n) {
		if (n == 1) b.setEval(b.heuristic());
		else {
			Board inverse = b.inverse();
			PriorityQueue<Board> responses = possibleMoves(inverse);
			calculate(bestMove(responses), n-1);
		}
	}
	public PriorityQueue<Board> possibleMoves(Board b) {
		PriorityQueue<Board> moves = new PriorityQueue<Board>();
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (validMove(b, r, c)) {
					Board k = makeMove(b,r,c);
					calculate(k, _n);
					moves.add(k);
				}
			}
		}
		return moves;
	}
	public Board bestMove(PriorityQueue<Board> moves) {
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
		if (player > computer) System.out.println("You win! Your score is " + (player - computer) + ".");
		else if (player < computer) System.out.println("You lose. :( Your score is " + (player - computer) + ".");
		else System.out.println("Draw.");
	}
}