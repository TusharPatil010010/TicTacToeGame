package com.capg.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {

	static Scanner input = new Scanner(System.in);

	public static String[] board = new String[10];
	public static String computer;
	public static String player;
	public static boolean moveflag;
	public static String symbol;
	public static boolean flagNextDecide;

	/**
	 * UC1 Creating Board
	 */
	public void createBoard() {
		for (int i = 1; i < 10; i++) {
			board[i] = "";
		}
	}

	/**
	 * UC2 Choose input symbol for player and computer
	 * 
	 * @return
	 */
	public String chooseSymbol() {
		System.out.println("Choose Symbol between X/O ");
		String choice = input.nextLine();
		symbol = choice;

		boolean flag = false;
		do {
			if (symbol.equalsIgnoreCase("x")) {
				player = "X";
				computer = "O";
			} else if (symbol.equalsIgnoreCase("o")) {
				player = "X";
				computer = "O";
			} else {
				System.out.println("Enter valid input");
				flag = true;
			}
		} while (flag);
		return symbol;
	}

	/**
	 * UC3 show board to user
	 */
	public void showBoard() {
		System.out.println("TIC TAC TOE ");
		System.out.println();
		for (int i = 1; i < 10; i++) {
			System.out.print(board[i]);
			if ((2 * i) % 3 != 0) {
				System.out.print(" | ");
			}
			if (i % 3 == 0 && i != 9) {
				System.out.println("\n_ _ _ _ ");
			}
		}
		System.out.println();
	}

	/**
	 * UC4 and UC5 choose position to make move at that position
	 * 
	 * @param position
	 */
	public void makeMove(int position) {
		if (board[position] == "" && position > 0 && position < 10) {
			board[position] = player;
		} else {
			System.out.println("Cannot choose this position, choose an empty position or enter valid index");
			moveflag = false;
		}
	}

	/**
	 * UC6 Toss for who goes first
	 * 
	 * @param toss
	 */
	public void toss(String toss) {

		if (Math.random() < 0.5) {
			System.out.println("heads it is");
			if (toss.equalsIgnoreCase("heads")) {
				System.out.println("Player goes first");
			} else {
				System.out.println("Computer's turn");
			}
			System.out.println("");
		} else {
			System.out.println("tails it is");
			if (toss.equalsIgnoreCase("tails")) {
				System.out.println("Player goes first");
			} else {
				System.out.println("Computer's turn");
			}
		}
	}

	/**
	 * UC7 decide next move
	 */
	public void decideNext() {
		boolean flagNext = false;
		int count = 1;
		if (board[1].equals(symbol) && board[2].equals(symbol) && board[3].equals(symbol)) {
			flagNext = true;
		} else if (board[4].equals(symbol) && board[5].equals(symbol) && board[6].equals(symbol)) {
			flagNext = true;
		} else if (board[7].equals(symbol) && board[8].equals(symbol) && board[9].equals(symbol)) {
			flagNext = true;
		} else if (board[1].equals(symbol) && board[5].equals(symbol) && board[9].equals(symbol)) {
			flagNext = true;
		} else if (board[3].equals(symbol) && board[5].equals(symbol) && board[7].equals(symbol)) {
			flagNext = true;
		} else if (board[1].equals(symbol) && board[4].equals(symbol) && board[7].equals(symbol)) {
			flagNext = true;
		} else if (board[2].equals(symbol) && board[5].equals(symbol) && board[8].equals(symbol)) {
			flagNext = true;
		} else if (board[3].equals(symbol) && board[6].equals(symbol) && board[9].equals(symbol)) {
			flagNext = true;
		}
		if (flagNext == true) {
			System.out.println("Win");
			flagNextDecide = false;
		} else {
			for (int i = 1; i < board.length; i++) {
				if (board[i] != "") {
					count++;
				}
			}
			if (count == 9) {
				System.out.println("Tie");
			} else {
				System.out.println("Next turn");
			}
		}
	}

	public static void main(String[] args) {

		TicTacToeGame tictacobject = new TicTacToeGame();

		tictacobject.createBoard();
		System.out.println("Player# choose heads or tails ");
		String toss = input.nextLine();
		tictacobject.toss(toss);

		symbol = tictacobject.chooseSymbol();
		System.out.println("Player's symbol : " + player);
		System.out.println("Computer's symbol : " + computer);
		tictacobject.showBoard();

		do {
			flagNextDecide = true;
			System.out.println("Enter the position where you want to make your move ");
			int position = input.nextInt();
			tictacobject.makeMove(position);
			tictacobject.showBoard();
			tictacobject.decideNext();
		} while (flagNextDecide);
	}
}