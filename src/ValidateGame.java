
public class ValidateGame {
	
	/*
	 * Check if horizontal line matches
	 * If it does, change the cell to the first item of the array
	 * If not, return "."
	 */
	private String horizontal(String array[]) {
		
		String cell = ".";
		int notEqual = 0;
		
		if (array[0] != ".") {
				
			for (int row = 0; row < array.length - 1; row++) {
				if (array[row] != array[row+1]) {
					notEqual++;
				}		
			}	
		}
		
		if (notEqual == 0) {
			cell = array[0];
		}
		
		return cell;
	}
	
	/*
	 * Checks if values in vertical line match
	 * If if does, change cell to the first value
	 * If not, return "."
	 */
	private String vertical(String[][] array, int col) {
		String cell = ".";
		int notEqual = 0;
		
		if (array[0][col] != ".") {
			for (int row = 0; row< array.length - 1; row++) {
				if (array[row][col] != array[row+1][col]) {
					notEqual++;
				}
			}
		}
		
		if (notEqual == 0) {
			cell = array[0][col];
		}
		
		return cell;
	}
	
	/*
	 * Check if values in diagonal match
	 * If it does, change cell to first value if the diagonal
	 * If not, return "."
	 */
	private String diagonal(String[][] array) {
		String cell = ".";
		int notEqual = 0;
		
		if (array[0][0] != ".") {
			for (int row = 0; row< array.length - 1; row++) {

				if (array[row][row] != array[row+1][row + 1]) {
					notEqual++;
				}
			}
			
		}
		
		if (notEqual == 0) {
			cell = array[0][0];
		}
		
		return cell;
	}
	
	/*
	 * Check if values in backwards diagonal match
	 * It it does, change cell to value
	 * If not, return "."
	 */
	private String backwardsDiagonal(String[][] array) {
		String cell = ".";
		int notEqual = 0;
		int column = array.length - 1 ;
		
		if (array[0][array.length - 1] != ".") {
			for (int row = 0; row< array.length - 1; row++) {

				if (array[row][column] != array[row+1][column-1]) {
					notEqual++;
					
				}
				column--;
			}
			
		}
		
		if (notEqual == 0) {
			cell = array[0][array.length - 1];
		}
		
		return cell;
	}
	
	/*
	 * Check board for winner
	 */
	public String validate(String[][] board) {
		
		String winner = ".";
		//Loop through board and check each row
		for (int row = 0; row < board.length; row ++) {
			String horiResult = horizontal(board[row]);
			
			if (horiResult != ".") {
				winner = horiResult;
			}
		}
		
		//Loop through and check each column
		for (int col = 0; col < board[0].length; col++) {
			String vertResult = vertical(board, col);
			
			if (vertResult != ".") {
				winner = vertResult;
			}
		}
		
		//Check diagonal
		String diaResult = diagonal(board);
		
		if (diaResult != ".") {
			winner = diaResult;
		}
		
		//Check backwards diagonal
		String bwDiaResult = backwardsDiagonal(board);
		
		if (bwDiaResult != ".") {
			winner = bwDiaResult;
		}
		
		return winner;
		
	}

}
