package com.capg.tictactoe;

import java.util.Scanner;

public class TicTacToeGame {
	
	Scanner input = new Scanner(System.in);

	public static String[] board = new String[10];
	public static String computer;
	public static String player;
	
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

	public static void main(String[] args) {
		
		TicTacToeGame tictacobject = new TicTacToeGame();

		tictacobject.createBoard();
		tictacobject.chooseSymbol();
		System.out.println("Player's symbol : " + player);
		System.out.println("Computer's symbol : " + computer);
		tictacobject.showBoard();
	}
}