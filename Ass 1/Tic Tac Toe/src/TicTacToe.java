
public class TicTacToe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] gameboard = {{' ', '|', ' ', '|', ' '}, 
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '}};
		
		for (int i = 0; i<5; i++) {
			System.out.println(gameboard[i]);
		}
		
	}

}
