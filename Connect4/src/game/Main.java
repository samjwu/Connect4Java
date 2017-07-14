package game;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * Class to run Connect Four game
 *
 */
public class Main {
	
	//game board is 7x6
	private final int length;
	private final int width;
	private final char[][] board;
	
	//https://stackoverflow.com/questions/4018851/final-and-static-in-java
	//http://www.alt-codes.net/
	private final static char[] symbols = new char[] {'☻', '☺'};
	//private final static char[] symbols = new char[] {'X', '○'};
	private final char space = '*';
	
	private int prevrow = -1;
	private int prevcol = -1;
	
	//constructor for Main class
	public Main (int length, int width) {
		this.length = length;
		this.width = width;
		
		board = new char[width][];
		
		for(int i = 0; i < width; i++) {
			Arrays.fill(board[i] = new char[length], space);
		}
	}
	
	@Override
	//print the board contents
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
	
	//function for players to move
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
				if (board[i][col-1] == space) {
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
	
	//function to check if a player wins the game
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
		return horizontalline().contains(fourinarow) || verticalline().contains(fourinarow) || ascendingdiagonalline().contains(fourinarow);
	}
	
	//return String containing the chip types (symbols) in the last played row
	private String horizontalline() {
		return new String(board[prevrow]);
	}
	
	//return String containing the chip types (symbols) in the last played column
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
	 * starting from the  coordinate
	 * @return diagonal (String): contains the chip types (symbols) in the last played descending diagonal
	 */
	private String descendingdiagonalline() {
		StringBuilder diagonal = new StringBuilder(width);
		//i goes down the columns (x) in a row (y)
		for (int j = 0; j < length; j++) {
			//i gets the row (y)
			//note for any coordinate in a descending diagonal, successive coordinates (x + y) or (column + row) increase by 2
			//when j = 0, current coordinate is the  of diagonal
			//when j = length - 1, current coordinate is the  of diagonal
			int i = prevcol + prevrow + j;
			//add the coordinate only if it is legal (on the board)
			if (i >= 0 && i < width) {
				diagonal.append(board[i][j]);
			}
		}
		return diagonal.toString();
	}
	
	//main function to run game
	public static void main(String[] args) {
		
		int nummoves = 42; //7x6=42
		int playerturn = 0; //players are 0 and 1
		Main board = new Main(7, 6); //game board is 7x6
		
		System.out.println("Connect 4");
		System.out.println("Enter a number to place a piece in a column (1 to 7)\n");
		System.out.println(board);
		
		Scanner input = new Scanner(System.in);
		
		
		while (true) {
			//get chip based on player turn
			char chip = symbols[playerturn];
			
			//do a player's turn
			board.move(chip, input);
			System.out.println(board);
			
			//next player turn
			playerturn = 1 - playerturn;
			
			//check if a player wins the game
			
			//decrement number of moves
			nummoves--;
			if (nummoves == 0) {
				break;
			}
		}
		
		//if no more moves left and there is no winner
		System.out.println("Game Over. Tie.");
	}
}