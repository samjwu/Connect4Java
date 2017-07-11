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
	
	private final char space = '*';
	
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
	
	//main function to run game
	public static void main(String[] args) {
		
		int nummoves = 42; //7x6=42
		boolean playerturn = true;
		Main board = new Main(7, 6); //game board is 7x6
		
		System.out.println("Connect 4");
		System.out.println("Enter a number to place a piece in a column (1 to 7)");
		System.out.println(board);
		
		Scanner input = new Scanner(System.in);
		
	}
}