import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;
public class Game2 {
	private Board _board;
	private int _consecPasses, _n, _tiles;
	public Game2(Board b, int n) {
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
	public void computerTurn(Board b) {
		System.out.println("Computer's turn...");

		System.out.println(_board);
	}
	public boolean validMove(Board b, int r, int c) {
		if (b.get(r,c).equals("-")) {
			for (int i = -1; i <=1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (r+i >= 0 && r+i<8 && c+j>= 0 && c+j <8) {
						if (goodFlip(b,r,c,i,j)) {
							System.out.println(b);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	public boolean goodFlip(Board b, int r, int c, int ud, int lr) {
		r+=ud;
		c+=lr;
		while(r+ud >= 0 && r+ud<8 && c+lr>= 0 && c+lr <8 && !(b.get(r,c).equals("-"))) {
			if (b.get(r,c).equals("x")) return true;
			r+=ud;
			c+=lr;
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
			i=r+ud;
			j=c+lr;
		}
		newboard.set(r,c,"x");
		return newboard;
	}
	public Board makeMove(Board b, int r, int c) {
		Board newboard = new Board();
		newboard.copy(b);
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (r+i >= 0 && r+i<8 && c+j>= 0 && c+j <8) {
					if (!(newboard.get(r+i,c+j).equals("x"))) {
						if (goodFlip(newboard, r, c, i, j)) newboard = flip(newboard, r, c, i, j);
					}
				}
			}
		}
		return newboard;
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