package com.capg.ws;

import java.util.Scanner;

public class TicTacToeGame {
	Scanner scanner = new Scanner(System.in);

	static String inputSymbol;
	public static String[] board = new String[10];
	static String computer;
	static int countMoves = 0;
	static int Max = 10;
	static int count = 0;

	/**
	 * Usecase 1
	 */
	public void createBoard() {
		for (int i = 1; i < 10; i++) {
			board[i] = " ";
		}
	}

	/**
	 * Usecase2
	 */
	public void chooseSymbol() {

		do {
			System.out.println("Enter the symbol for player from X or O");
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
	 * Uecase3
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
	 * Usecase4 and Usecase5
	 * 
	 * @param position
	 * @param whoIsPlaying
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
	 * Usecase6
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
	 * Usecase7
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
	 * Usecase8
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

	public String compMove() {
		String play = "Computer";
		int winPosition = winPosition();

		int position = (int) Math.floor(Math.random() * 10) % 9 + 1;
		if (count > 0 && count <= Max) {
			int pos = blockUsersWin();
			if (pos != 0) {
				position = pos;
			}
		} else if (winPosition != 0) {
			position = winPosition;
		}
		if (board[position].equals(" ")) {
			board[position] = computer;
			System.out.println("Computer played the move");
			play = "User";
			showBoard();
			count++;
			countMoves++;
		}
		return play;
	}

	/**
	 * UC9 Bock user if he is winning
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

	public static void main(String[] args) {
		TicTacToeGame tictac = new TicTacToeGame();
		Scanner scan = new Scanner(System.in);
		tictac.createBoard();
		tictac.chooseSymbol();
		String player = tictac.playFirst();
		while (true) {
			if (player.equals("Computer")) {
				player = tictac.compMove();
				if (tictac.isWinner(board, computer) == 1) {
					System.out.println("Winner is Computer");
					break;
				} else if (tictac.isWinner(board, computer) == 2) {
					System.out.println("No one has won the game");
					break;
				}
			} else {
				System.out.println("Enter the position for move");
				int position = scan.nextInt();
				scan.nextLine();
				player = tictac.makeMove(position);
				tictac.showBoard();
				if (tictac.isWinner(board, inputSymbol) == 1) {
					System.out.println("Winner is User");
					break;
				} else if (tictac.isWinner(board, inputSymbol) == 2) {
					System.out.println("No one has won the game");
					break;
				}
			}
		}
		scan.close();
	}
}