public class TicTacToeGame {
	
	//Initialization
	public static String[] board = new String[10];

	/**
	 * UC1 Creating Board
	 */
	public void createBoard() {
		for (int i = 1; i < 10; i++) {
			board[i] = "";
		}
	}

	public static void main(String[] args) {
		
		TicTacToeGame tictacobject = new TicTacToeGame();

		tictacobject.createBoard();
	}
}
