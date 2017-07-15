package game;

import java.util.Scanner;

/**
 * 
 * Class to run Connect Four game
 *
 */
public class Main {
	//main function to run game
	public static void main(String[] args) {
		
		int nummoves = 42; //7x6=42
		int playerturn = 0; //players are 0 and 1
		Board board = new Board(7, 6); //game board is 7x6
		
		System.out.println("Connect 4");
		System.out.println("Enter a number to place a piece in a column (1 to 7)\n");
		System.out.println(board);
		
		Scanner input = new Scanner(System.in);
		
		
		while (true) {
			//get chip based on player turn
			//0 for red, 1 for yellow
			char chip;
			if (playerturn == 0) {
				chip = Symbols.Red.toChar();
			}
			else {
				chip = Symbols.Yellow.toChar();
			}
			
			//do a player's turn
			board.move(chip, input);
			System.out.println(board);
			
			//check if a player wins the game
			if (board.win()) {
				System.out.println("GAME OVER");
				System.out.println("Player " + chip + " wins");
				return;
			}
			
			//next player turn
			playerturn = 1 - playerturn;
			
			//decrement number of moves
			nummoves--;
			
			//end game if no moves left
			if (nummoves == 0) {
				break;
			}
		}
		
		//if no more moves left and there is no winner
		System.out.println("GAME OVER");
		System.out.println("Tie");
	}
}