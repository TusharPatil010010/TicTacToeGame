package com.capg.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {
	
	static Scanner input = new Scanner(System.in);

	public static String[] board = new String[10];
	public static String computer;
	public static String player;
	public static boolean moveflag;
	

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
	 * @return
	 */
	public String chooseSymbol() {
		System.out.println("Choose Symbol between X/O ");
		String symbol = input.nextLine();
		
		boolean flag = false;
		do {		
			if(symbol.equalsIgnoreCase("x")) {
				player = "X";
				computer = "O";
			}
			else if(symbol.equalsIgnoreCase("o")) {
				player = "X";
				computer = "O";
			}
			else {
				System.out.println("Enter valid input");
				flag = true;
			}
		} while(flag);
		return symbol;
	}
	
	/**
	 * UC3 show board to user
	 */
	public void showBoard() {
		System.out.println("TIC TAC TOE ");	
		System.out.println();
		for(int i = 1; i < 10; i++) {
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
	 * @param position
	 */
	public void makeMove(int position) {
			if(board[position] == "" && position > 0 && position < 10) {
				board[position] = player;
			}
			else {
				System.out.println("Cannot choose this position, choose an empty position or enter valid index");
				moveflag = true;
			}
	}

	public static void main(String[] args) {
		
		TicTacToeGame tictacobject = new TicTacToeGame();

		tictacobject.createBoard();
		tictacobject.chooseSymbol();
		System.out.println("Player's symbol : " + player);
		System.out.println("Computer's symbol : " + computer);
		tictacobject.showBoard();
		
		moveflag = false;
		do {
		System.out.println("Enter the position where you want to make your move ");
		int position = input.nextInt();
		tictacobject.makeMove(position);
		tictacobject.showBoard();
		} while(moveflag);
	}
}