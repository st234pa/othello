public class Board implements Comparable<Board> {
	private String[][] _board;
	private int _eval;
	private static int[][] BOARDVALUE = {
		{15, -7, 10, 7, 7, 10, -7, 15},
		{-7, -10, -8, 2, 2, -8,-10, -7},
		{10, -8, 3, 3, 3, 3, -8, 10},
		{7, 2, 3, 1, 1, 3,  2, 7},
		{7, 2,  3, 1, 1, 3, 2, 7},
		{15, -7, 10, 7, 7, 10, -7, 15},
		{-7, -10, -8, 2, 2, -8,-10, -7},
		{10, -8, 3, 3, 3, 3, -8, 10},
	};
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
	public void set(int i, int j, String piece) {
		_board[i][j] = piece;
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
	public int getEval() {
		return _eval;
	}
	public void setEval(int x) {
		_eval = x;
	}
	public int heuristic() {
		int ans = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (get(i,j).equals("x")) ans += BOARDVALUE[i][j];
				else if (get(i,j).equals("o")) ans -= BOARDVALUE[i][j];
			}
		}
		return ans;
	}
	public int compareTo(Board other) {
		if (this.getEval() > other.getEval()) return 1;
		else if (this.getEval() == other.getEval()) return 0;
		else return -1;
	}
	public Board inverse() {
		Board ans = new Board();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.get(i,j).equals("x")) ans.set(i,j,"o");
				else if (this.get(i,j).equals("o")) ans.set(i,j,"x");
			}
		}
		return ans;
	}
}