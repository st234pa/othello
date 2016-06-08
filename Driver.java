import java.util.Scanner;
public class Driver {
	public static void main(String[] args) {
		System.out.println("OTHELLO -- by Stephanie Yoon");
		Scanner scan = new Scanner(System.in);
		System.out.print("n: ");
		int n = scan.nextInt();
		System.out.println("Player is x. Computer is o.");
		Board board = new Board();
		Game game = new Game(board, n);
		game.run();
	}
}