import java.util.Scanner;
public class Driver {
	public static void main(String[] args) {
		System.out.println("OTHELLO -- by Stephanie Yoon");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter how many moves to look ahead (1-4).");
		int n = 5;
		while (n<1 || n>4) {
			System.out.print("n: ");
			n = scan.nextInt(); 
		}
		System.out.println("Player is x. Computer is o.");
		Board board = new Board();
		Game game = new Game(board, n);
		game.run();
	}
}