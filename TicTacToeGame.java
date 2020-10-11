package com.capg.ws;

import java.util.Scanner;

public class TicTacToeGame {
	Scanner scanner = new Scanner(System.in);
	static String user;
	public static String[] board = new String[10];
	static String computer = "";

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
			user = scanner.nextLine();
			if (user.equals("X")) {
				computer = "O";
				break;
			} else if (user.equals("O")) {
				computer = "X";
				break;
			} else {
				System.out.println("Invalid input");
			}
		} while (user != "O" || user != "X");
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
	 * @param 
	 * @param 
	 * @return
	 */
	public String makeMove(int position, String whoIsPlaying, String symbol) {
		boolean check = isWinner(symbol);
		boolean tie = isTie();
		if (board[position] == " " && check == false && tie == false) {
			if (whoIsPlaying.equals("User")) {
				board[position] = user;
				whoIsPlaying = "Computer";
			} else {
				board[position] = computer;
				whoIsPlaying = "User";
			}
		} else {
			System.out.println("Enter empty position");
		}
		return whoIsPlaying;
	}

	
	public int computerIsPlaying() {
		int position = (int) Math.floor((Math.random()) * 10) % 9 + 1;
		return position;
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
	 * Uscase7
	 * @return
	 */
	public boolean isTie() {
		boolean flag = true;
		for (int i = 1; i < board.length; i++) {
			if (board[i] == " ") {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * Usecase7
	 * @param symbol
	 * @return
	 */
	public boolean isWinner(String symbol) {
		boolean flag = false;
		if (board[1].equals(symbol) && board[2].equals(symbol) && board[3].equals(symbol)) {
			flag = true;
		} else if (board[4].equals(symbol) && board[5].equals(symbol) && board[6].equals(symbol)) {
			flag = true;
		} else if (board[7].equals(symbol) && board[8].equals(symbol) && board[9].equals(symbol)) {
			flag = true;
		} else if (board[1].equals(symbol) && board[5].equals(symbol) && board[9].equals(symbol)) {
			flag = true;
		} else if (board[3].equals(symbol) && board[5].equals(symbol) && board[7].equals(symbol)) {
			flag = true;
		} else if (board[1].equals(symbol) && board[4].equals(symbol) && board[7].equals(symbol)) {
			flag = true;
		} else if (board[2].equals(symbol) && board[5].equals(symbol) && board[8].equals(symbol)) {
			flag = true;
		} else if (board[3].equals(symbol) && board[6].equals(symbol) && board[9].equals(symbol)) {
			flag = true;
		}
		return flag;
	}

	public static void main(String[] args) {
		TicTacToeGame tictactoe = new TicTacToeGame();
		Scanner scan = new Scanner(System.in);
		tictactoe.createBoard();
		tictactoe.chooseSymbol();
		String player = tictactoe.playFirst();
		do {
			if (player.equals("Computer")) {
				int compsPosition = tictactoe.computerIsPlaying();

				player = tictactoe.makeMove(compsPosition, player, user);
				tictactoe.showBoard();
			} else {
				System.out.println("Enter the position for move");
				int position = scan.nextInt();
				scan.nextLine();
				player = tictactoe.makeMove(position, player, computer);
				tictactoe.showBoard();
			}
			if (tictactoe.isWinner(user) == true) {
				System.out.println("User wins");
				break;
			} else if (tictactoe.isWinner(computer) == true) {
				System.out.println("Computer Wins");
				break;
			}
		} while (tictactoe.isTie() == false);
		tictactoe.showBoard();
		scan.close();
	}
}