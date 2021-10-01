import java.util.Scanner;

public class TicTacToe {
	
	TicTacToe() {
		
	}

	String[[][]] gameboard = [[" ","|", " ", "|", " "], ["-", "+", "-", "+", "-"], [" ","|", " ", "|", " "]]
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		GameBoard board1 = new GameBoard();
		
		board1.printBoard();
		System.out.println("Enter position from 1-9");
		int position = Integer.parseInt(input.nextLine());
		
		board1.
	}

}
