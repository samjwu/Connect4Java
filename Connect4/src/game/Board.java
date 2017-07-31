package game;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * Class to create board for Connect Four game
 * as well as game logic
 * for making moves and checking win conditions
 *
 */
public class Board {
	
	//game board is 7x6
	private final int length;
	private final int width;
	private final char[][] board;
	
	//https://stackoverflow.com/questions/4018851/final-and-static-in-java
	//http://www.alt-codes.net/
	//private final static char[] symbols = new char[] {'☻', '☺'};
	//private final static char[] symbols = new char[] {'X', '○'};
	//private final char space = '*';
	
	private int prevrow = -1;
	private int prevcol = -1;
	
	/**
	 * constructor for Board class
	 * @param length (int): long side of board (all the columns)
	 * @param width (int): short side of board (all the rows)
	 */
	public Board (int length, int width) {
		this.length = length;
		this.width = width;
		
		board = new char[width][];
		
		for(int i = 0; i < width; i++) {
			//https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html
			Arrays.fill(board[i] = new char[length], Symbols.Space.toChar());
		}
	}
	
	/**
	 * @Override
	 * Print the board contents as a String
	 * @return String that represents the board
	 */
	public String toString() {
		//https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html
		StringBuilder boardcontents = new StringBuilder();
		
		//add length # of pieces to make row, then add width # of rows to make board
		for (int j = 0; j < width; j++) {
            for (int i = 0; i < length; i++) {
                boardcontents.append(board[j][i]); //add piece
                boardcontents.append(" ");
            }
            boardcontents.append("\n");
        }
		
		return boardcontents.toString();
	}
	
	/**
	 * Function for players to move
	 * @param chip (char): char symbol representing a chip
	 * @param input (Scanner): to get player input for column to place chip
	 */
	public void move(char chip, Scanner input) {
		while (true) {
			//get player input column
			System.out.print("Player " + chip + " turn: ");
			int col = input.nextInt();
			System.out.println("");
			
			//check for valid input
			if (col < 1 || col > length) {
				System.out.println("You can only place a chip in columns 1 to " + width);
                continue;
			}
			
			//place the chip in the column indicated by input
			for (int i = width - 1; i >= 0; i--) {
				//recall columns are zero indexed
				if (board[i][col-1] == Symbols.Space.toChar()) {
					board[i][col-1] = chip;
					prevrow = i;
					prevcol = col-1;
					return;
				}
			}
			
			//else if column is full
			System.out.println("Column " + col + " is full. You may not place a chip there.");
			//continue
		}
	}
	
	/**
	 * Function to check if a player wins the game
	 * @return boolean indicating true for a win and false otherwise
	 */
	public boolean win() {
		//moves have not been made
		if (prevcol == -1) {
			return false;
		}

		//get the last played chip type (symbol)
		char chip = board[prevrow][prevcol];
		//if all four chips in a row in a String are the same, then that player wins
		String fourinarow = String.format("%c%c%c%c", chip, chip, chip, chip);
		
		//get all possible String line combinations
		//check if the Strings contain four of the same chip types (symbols) in a row
		//https://www.tutorialspoint.com/java/lang/string_contains.htm
		return horizontalline().contains(fourinarow) || verticalline().contains(fourinarow) || ascendingdiagonalline().contains(fourinarow) || descendingdiagonalline().contains(fourinarow);
	}
	
	/**
	 * Gets all the columns in the last played row
	 * @return String containing the chip types (symbols) in the last played row
	 */
	private String horizontalline() {
		return new String(board[prevrow]);
	}
	
	/**
	 * Goes down all the rows in the last played column
	 * @return String containing the chip types (symbols) in the last played column
	 */
	private String verticalline() {
		StringBuilder column = new StringBuilder(width);
		//go down the rows (y) in a column (x)
		for (int i = 0; i < width; i++) {
			column.append(board[i][prevcol]);
		}
		return column.toString();
	}
	
	/**
	 * Gets all coordinates in an ascending diagonal
	 * starting from the top right, moves down and leftwards to the bottom left coordinate
	 * Note outer loop increments by 1 and inner loop decrements by 1
	 * (net gain/loss is 0)
	 * which corresponds to loop invariant [column + row = constant] for ascending diagonal
	 * @return diagonal (String): contains the chip types (symbols) in the last played ascending diagonal
	 */
	private String ascendingdiagonalline() {
		StringBuilder diagonal = new StringBuilder(width);
		//i goes down the rows (y) in a column (x)
		for (int i = 0; i < width; i++) {
			//j gets the column (x)
			//note for any coordinate in an ascending diagonal, x + y or column + row are the same
			//when i = 0, current coordinate is the top-right of diagonal
			//when i = width - 1, current coordinate is the bottom-left of diagonal
			int j = prevcol + prevrow - i;
			//add the coordinate only if it is legal (on the board)
			if (j >= 0 && j < length) {
				diagonal.append(board[i][j]);
			}
		}
		return diagonal.toString();
	}
	
	/**
	 * Gets all coordinates in a descending diagonal
	 * starting from the top left, moves down and rightwards to the bottom right coordinate
	 * Note outer loop increments by 1 and inner loop increments by 1
	 * (net gain is 2)
	 * which corresponds to loop invariant [current column + row = previous column + row + 2] for descending diagonal
	 * @return diagonal (String): contains the chip types (symbols) in the last played descending diagonal
	 */
	private String descendingdiagonalline() {
		StringBuilder diagonal = new StringBuilder(width);
		//i goes down the rows (y) in a column (x)
		for (int i = 0; i < width; i++) {
			//j gets the column (x)
			//note for any coordinate in a descending diagonal, successive coordinates (x + y) or (column + row) increase by 2
			//when i = 0, current coordinate is the top-left of diagonal
			//when i = width - 1, current coordinate is the bottom-right of diagonal
			int j = prevcol - prevrow + i;
			//add the coordinate only if it is legal (on the board)
			if (j >= 0 && j < length) {
				diagonal.append(board[i][j]);
			}
		}
		return diagonal.toString();
	}
}