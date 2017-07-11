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
		return boardcontents.toString();
	}
	
	//main function to run game
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Main board = new Main(7, 6);
		System.out.println("Connect 4");
		System.out.println("Enter a number to place a piece in a column (1 to 7)");
		//System.out.println(board);
		
	}
}