public class Board implements Comparable<Board> {
	private String[][] _board;
	private int _eval;
	public Board() {
		_board = new String[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				_board[i][j] = "-";
		}
		_board[3][4] = "x";
		_board[4][3] = "x";
		_board[4][4] = "o";
		_board[3][3] = "o";
		_eval = 0;
	}
	public String get(int i, int j) {
		return _board[i][j];
	}
	public String toString() {
		String ans = "  0 1 2 3 4 5 6 7 ";
		ans += "\n";
		for (int i = 0; i < 8; i++) {
			ans += i + " ";
			for (int j = 0; j < 8; j++) {
				ans += _board[i][j] + " ";
			}
			ans += "\n";
		}
		return ans;
	}
	public int eval() {
		return _eval;
	}
	public int compareTo(Board other) {
		if (this.eval() > other.eval()) return 1;
		else if (this.eval() == other.eval()) return 0;
		else return -1;
	}
}