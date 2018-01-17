import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class TicTacToe {
	
	//seen is to store all positions/cells played
	HashSet<Integer> seen = new HashSet<Integer>();
	private int boardSize = 3;
	private int totalCells = boardSize * boardSize;
	
	// Prompts user and gets user's inputs
	private int getUserInput(Scanner readInput) {
		int menuInput;
		
		System.out.println("Please enter a cell number: ");
		do {
			try {
				menuInput = readInput.nextInt();
				
				if (seen.contains(menuInput)) {
					System.out.print("That cell has already been marked. Please re-enter: ");
					continue;
					
				} else if (menuInput >=1 && menuInput <= totalCells) {
					break;
					
				} else {
					System.out.print("You have not entered a number between 1 and 9. Please re-enter: ");
					continue;
					}
				} catch (final InputMismatchException e){
					System.out.print("You have entered an invalid choice. Try again: ");
					readInput.nextLine();
					}
			} while (true);

		return menuInput;

		}
	
	//Starting board
	private String [][] startBoard() {
		String [][] board = new String[boardSize][boardSize];
		
		for (int row = 0; row < boardSize; row ++) {
			for (int col = 0; col < boardSize; col ++) {
				board[row][col] = ".";
			}
		}
		
		return board;
	}
	
	//Loops through board and sets cell to player's piece (X or O)
	private void makeMove(String [][] board, int position, String player) {
		
		int counter = 1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (counter == position) {
					board[i][j] = player;
				}
				
				counter++;
			}
		}
	}
	
	//Prints board
	private void printBoard(String [][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("|" + board[i][j]);
			}
			System.out.println("|");
		}
		System.out.println();
	}
	
	/*
	 * User's move, takes user's input and makes a call to the makeMove method
	 * Add user's move to hashset, seen
	 */
	
	private void userMove(String [][] board) {
		
		Scanner readInput = new Scanner(System.in);
		int userInput;
		userInput = getUserInput(readInput);
		makeMove(board, userInput, "X");
		
		seen.add(userInput);
		
	}
	
	
	
	/*
	 * Computer's move, takes a random number between 1 and 9
	 * Will only occur if the size of hashset, seen is less than 9
	 * and will get make sure to get a random number that has not been played (in hashset, seen)
	 * Makes call to the makeMove method
	 * Add computer's move to hashset, seen
	 */
	private void computerMove(String [][] board) {
		int num;
		Random random = new Random();
		
		if (seen.size() < totalCells) {
			do {
				num = random.nextInt(totalCells) + 1;
			}
			
			while (seen.contains(num));
			
			makeMove(board, num, "O");
			seen.add(num);
		}
		
	}

	/*
	 * Set of instructions to play game
	 */
	public void playGame() {
		
		String [][] board = startBoard();
		String winner = ".";
		ValidateGame findWinner = new ValidateGame();
		
		System.out.println("Welcome to Tic Tac Toe Game.\n");
		while (findWinner.validate(board) == "." && seen.size() < totalCells) {
			printBoard(board);
			userMove(board);
			System.out.println();
			winner = findWinner.validate(board);
			computerMove(board);
			winner = findWinner.validate(board);
		}
		
		printBoard(board);
		
		if (winner == ".") {
			System.out.println("Draw. No winner.");
			
		} else if (winner == "X") {
			System.out.println("Congratulations! You are the winner.");
		} else {
			System.out.println("Sorry, computer wins.");
		}

	}

}
