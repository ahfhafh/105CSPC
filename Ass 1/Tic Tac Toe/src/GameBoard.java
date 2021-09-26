
public class GameBoard {
		
	char[][] gameboard = {{' ', '|', ' ', '|', ' '}, 
						{'-', '+', '-', '+', '-'},
						{' ', '|', ' ', '|', ' '},
						{'-', '+', '-', '+', '-'},
						{' ', '|', ' ', '|', ' '}};
	
	public void printBoard() {
		for (int i = 0; i<5; i++) {
			System.out.println(gameboard[i]);
		}
	}
}
