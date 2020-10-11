package com.capg.ws;

import java.util.Scanner;

public class TicTacToeGame {
	Scanner scanner = new Scanner(System.in);

	public static String[] board = new String[10];
	static String computer;
	static String inputSymbol;
	static int countMoves = 0;

	/**
	 * UC1 creating a tic tac board
	 */
	public void createBoard() {
		for (int i = 1; i < 10; i++) {
			board[i] = " ";
		}
	}

	/**
	 * UC2 choosing input
	 */
	public void chooseSymbol() {

		do {
			System.out.println("Choose the symbol between X/O");
			inputSymbol = scanner.nextLine();
			if (inputSymbol.equals("X")) {
				computer = "O";
				break;
			} else if (inputSymbol.equals("O")) {
				computer = "X";
				break;
			} else {
				System.out.println("Invalid input");
			}
		} while (inputSymbol != "O" || inputSymbol != "X");
	}

	/**
	 * UC3 show the board to user
	 */
	public void showBoard() {
		for (int i = 1; i < 10; i++) {
			System.out.print(board[i]);
			if ((2 * i) % 3 != 0) {
				System.out.print(" | ");
			}
			if (i % 3 == 0 && i != 9) {
				System.out.println("\n----------");
			}
		}
		System.out.println();
	}

	/**
	 * UC4 and UC5 making move at entered position
	 * 
	 * @param position
	 * @return
	 */
	public String makeMove(int position) {
		String play = "User";
		if (position < 1 || position > 9) {
			System.out.println("Invalid position");
		} else if (board[position].equals(" ")) {
			board[position] = inputSymbol;
			play = "Computer";
			countMoves++;
		} else {
			System.out.println("Enter an empty position");
			showBoard();
		}
		return play;
	}

	/**
	 * UC6 toss for who goes first
	 * 
	 * @return
	 */
	public String playFirst() {
		int chance = (int) Math.floor(Math.random() * 10) % 2;
		String startFirst;
		String toss;
		if (chance == 1) {
			toss = "Heads";
			startFirst = "User";
		} else {
			toss = "Tails";
			startFirst = "Computer";
		}
		System.out.println(toss + "..." + startFirst + " will play first");
		return startFirst;
	}

	/**
	 * UC7 deciding the next move winner/tie/nextMove
	 * 
	 * @param symbol
	 * @return
	 */
	public int isWinner(String[] board, String symbol) {
		if (board[1].equals(symbol) && board[2].equals(symbol) && board[3].equals(symbol)) {
			return 1;
		} else if (board[4].equals(symbol) && board[5].equals(symbol) && board[6].equals(symbol)) {
			return 1;
		} else if (board[7].equals(symbol) && board[8].equals(symbol) && board[9].equals(symbol)) {
			return 1;
		} else if (board[1].equals(symbol) && board[5].equals(symbol) && board[9].equals(symbol)) {
			return 1;
		} else if (board[3].equals(symbol) && board[5].equals(symbol) && board[7].equals(symbol)) {
			return 1;
		} else if (board[1].equals(symbol) && board[4].equals(symbol) && board[7].equals(symbol)) {
			return 1;
		} else if (board[2].equals(symbol) && board[5].equals(symbol) && board[8].equals(symbol)) {
			return 1;
		} else if (board[3].equals(symbol) && board[6].equals(symbol) && board[9].equals(symbol)) {
			return 1;
		}
		if (countMoves == 9) {
			return 2;
		}
		return 0;
	}

	/**
	 * UC8 Computer checks if there is any position so that he wins
	 * 
	 * @return
	 */
	public int winPosition() {
		String[] copyOfBoard = board.clone();
		for (int i = 1; i < copyOfBoard.length; i++) {
			if (copyOfBoard[i].equals(" ")) {
				copyOfBoard[i] = computer;
				if (isWinner(copyOfBoard, computer) == 1) {
					return i;
				} else {
					copyOfBoard[i] = " ";
				}
			}
		}
		return 0;
	}

	/**
	 * UC9 Computer checks for opponents win and blocks
	 * 
	 * @return
	 */
	public int blockUsersWin() {
		String[] copyOfBoard = board.clone();
		for (int i = 1; i < copyOfBoard.length; i++) {
			if (copyOfBoard[i].equals(" ")) {
				copyOfBoard[i] = inputSymbol;
				if (isWinner(copyOfBoard, inputSymbol) == 1) {
					return i;
				} else {
					copyOfBoard[i] = " ";
				}
			}
		}
		return 0;
	}

	/**
	 * UC10 If computer goes first, it checks for corners If available makes it's
	 * first move there
	 * 
	 * @return
	 */
	public int checkCorners() {
		int[] corners = { 1, 3, 7, 9 };
		int position;
		int choice = (int) Math.floor(Math.random() * 10) % 4;
		position = corners[choice];
		for (int i = 0; i < 4; i++) {
			if (board[position].equals(" ")) {
				return position;
			} else {
				choice = (int) Math.floor(Math.random() * 10) % 4;
				position = corners[choice];
			}
		}
		return 10;
	}

	/**
	 * UC11 If corners are filled computer checks for centre and then for sides
	 * 
	 * @return
	 */
	public int checkSides() {
		int[] sides = { 2, 4, 6, 8 };
		int position;
		int choice = (int) Math.floor(Math.random() * 10) % 4;
		position = sides[choice];
		for (int i = 0; i < 4; i++) {
			if (board[position].equals(" ")) {
				return position;
			} else {
				choice = (int) Math.floor(Math.random() * 10) % 4;
				position = sides[choice];
			}
		}
		return 10;
	}

	/**
	 * This method defines the computers logic required for the other cases
	 * 
	 * @return
	 */
	public String compMove() {
		String play = "Computer";
		int winPosition = winPosition();
		int blockPosition = blockUsersWin();
		int position = (int) Math.floor(Math.random() * 10) % 9 + 1;
		if (winPosition != 0) {
			position = winPosition;
		} else if (winPosition == 0 && blockPosition != 0) {
			position = blockPosition;
		} else if (winPosition == 0 && blockPosition == 0) {
			if (checkCorners() == 10) {
				if (board[5].equals(" ")) {
					position = 5;
				} else {
					position = checkSides();
				}
			} else {
				position = checkCorners();
			}
		}
		if (board[position].equals(" ")) {
			board[position] = computer;
			System.out.println("Computer's turn");
			play = "User";
			System.out.println("____________");
			showBoard();
			System.out.println("____________");
			countMoves++;
		}
		return play;
	}

	public static void main(String[] args) {
		TicTacToeGame tictac = new TicTacToeGame();
		Scanner scan = new Scanner(System.in);
		tictac.createBoard();
		System.out.println("TIC TAC TOE");
		tictac.chooseSymbol();
		String player = tictac.playFirst();
		while (true) {
			if (player.equals("Computer")) {
				player = tictac.compMove();
				if (tictac.isWinner(board, computer) == 1) {
					System.out.println(" Computer wins");
					break;
				} else if (tictac.isWinner(board, computer) == 2) {
					System.out.println("No one won, It's a tie");
					break;
				}
			} else {
				System.out.println("User's turn");
				System.out.println("Enter the position for move");
				int position = scan.nextInt();
				scan.nextLine();
				player = tictac.makeMove(position);
				System.out.println("____________");
				tictac.showBoard();
				System.out.println("____________");
				if (tictac.isWinner(board, inputSymbol) == 1) {
					System.out.println(" User wins");
					break;
				} else if (tictac.isWinner(board, inputSymbol) == 2) {
					System.out.println("No one won, It's a tie");
					break;
				}
			}
		}
		scan.close();
	}
}