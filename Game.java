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
	}
}