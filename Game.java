import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
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
		PriorityQueue<Board> listmoves = possibleMoves2(_board);
		if (listmoves.size() == 0) {
			System.out.println("No possible moves. Player passes.");
			_consecPasses++;
		}	
		else {
			int r = 3;
			int c = 4;
			while (r < 0 || r > 7 || c < 0 || c > 7 || !validMove(_board, r, c)) {
				r = getRow();
				c = getCol();
			}
			_board = makeMove(_board, r, c);
			_tiles++;
			_consecPasses = 0;
		}
		System.out.println(_board);
	}
	public int getRow() {
		System.out.println("Row: ");
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		return a;
	}
	public int getCol() {
		System.out.println("Column: ");
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		return a;
	}
	public void computerTurn(Board b) {
		System.out.println("Computer's turn...");
		if (_n == 1) {
			PriorityQueue<Board> listmoves = possibleMoves2(b);
			if (listmoves.size() != 0) {
				_board = (bestMove(listmoves)).inverse();
				_tiles++;
				_consecPasses = 0;
			}
			else _consecPasses++;
			System.out.println(_board);
		}
		else {
			ArrayList<Board> listmoves = possibleMoves(b);
			if (listmoves.size() != 0) {
				_board = (bestMove(listmoves, _n)).inverse();
				_tiles++;
				_consecPasses = 0;
			}
			else _consecPasses++;
			System.out.println(_board);
		}
		
	}
	public boolean validMove(Board b, int r, int c) {
		if (b.get(r,c).equals("-")) {
			for (int i = -1; i <=1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (r+i >= 0 && r+i<8 && c+j>= 0 && c+j <8) {
						if (b.get(r+i, c+j).equals("o")) {
							if (goodFlip(b,r,c,i,j)) return true;
						}
					}
				}
			}
		}
		return false;
	}
	public boolean goodFlip(Board b, int r, int c, int ud, int lr) {
		int i = r + ud;
		int j = c + lr;
		while(i >= 0 && i<8 && j>= 0 && j<8 && !(b.get(i,j).equals("-"))) {
			if (b.get(i,j).equals("x")) return true;
			i+=ud;
			j+=lr;
		}
		return false;
	}
	public Board flip(Board b, int r, int c,  int ud, int lr) {
		Board newboard = new Board();
		newboard.copy(b);
		int i = r+ud;
		int j = c+lr;
		while(i>= 0 && i<8 && j>= 0 && j <8 && newboard.get(i,j).equals("o")) {
			newboard.set(i,j,"x");
			i+=ud;
			j+=lr;
		}
		return newboard;
	}
	public Board makeMove(Board b, int r, int c) {
		Board newboard = new Board();
		newboard.copy(b);
		newboard.set(r,c,"x");
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (r+i >= 0 && r+i<8 && c+j>= 0 && c+j <8) {
					if (newboard.get(r+i,c+j).equals("o")) {
						if (goodFlip(newboard, r, c, i, j)) newboard = flip(newboard, r, c, i, j);
					}
				}
			}
		}
		newboard.setEval(newboard.heuristic());
		return newboard;
	}
	public int calculate(Board b, int n) {
		if (n == 1) return b.getEval();
		else {
			ArrayList<Board> responses = possibleMoves(b.inverse());
			return calculate(bestMove(responses, n-1), n-1) * (-1);
		}
	}
	public ArrayList<Board> possibleMoves(Board b) {
		ArrayList<Board> listmoves = new ArrayList<Board>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (validMove(b, i, j)) listmoves.add(makeMove(b,i,j));
			}
		}
		return listmoves;
	}
	public PriorityQueue<Board> possibleMoves2(Board b) {
		PriorityQueue<Board> listmoves = new PriorityQueue<Board>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (validMove(b, i, j)) listmoves.add(makeMove(b,i,j));
			}
		}
		return listmoves;
	}
	public Board bestMove(ArrayList<Board> a, int n) {
		Board ans = a.get(0);
		
		for (int i = 1; i < a.size(); i++) {
			if (calculate(a.get(i),n) > calculate(ans, n)) ans = a.get(i);
		}
		
		return ans;
	}
	public Board bestMove(PriorityQueue<Board> a) {
		return a.peek();
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