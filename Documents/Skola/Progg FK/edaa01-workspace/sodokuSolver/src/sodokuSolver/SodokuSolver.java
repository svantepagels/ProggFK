package sodokuSolver;

public class SodokuSolver {
	private int[][] spelPlan;

	public SodokuSolver() {
		spelPlan = new int[9][9];		
	}

	/**
	 * Sets the value of the field in place [i][j] in the matrix.
	 * @param value the value to be set. 
	 * @param i row of the field.
	 * @param j column of the field. 
	 */
	public void setValue(int value, int i, int j) {
		spelPlan[i][j] = value;
	}
	
	/**
	 * Returns a String representation of the value in a certain field.
	 * @param i row of the field.
	 * @param j column of the field. 
	 * @return the value in the field. 
	 */
	public String getValue(int i, int j){
		StringBuilder sb = new StringBuilder();
		sb.append(spelPlan[i][j]);
		return sb.toString();
	}

//	/**
//	 * Prints the board to the console.
//	 */
//	public void print() {
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				System.out.print(spelPlan[i][j] + " ");
//			}
//			System.out.println("");
//		}
//	}
	
	/**
	 * Solves the sudoku-board. Returns false if the board is unsolvable. 
	 * @return true if solved. 
	 */
	public boolean solve(){
		if(isPossible()){ //Kontrollerar om brädet är tillåtet.
			
			return solve(0,0);
		} else{
			return false;
		}
		
	}
	
	private boolean solve(int i, int j) {
		if(j>8){
			j = 0;
			i++;
			if(i>8){
				return true;
			}
		}
		
//		Fall 1: Rutan är inte ifylld av användaren.
		if (spelPlan[i][j] == 0) {			
			for(int k = 1; k < 10; k++){
				if(isPossible(i,j,k)){
					spelPlan[i][j] = k;
					if(solve(i, j + 1)){
						return true;
					}
				}
			}
			spelPlan[i][j] = 0;
			return false;
		} else{ //Fall 2: Rutan är ifylld.
			return solve(i, j + 1);
		}
	}

	//kontrollerar om samtliga värden på brädet är tillåtna. används för att snabba upp processen att upptäcka att ett bräde är olösligt
	private boolean isPossible() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int value = spelPlan[i][j];
				if (value != 0 && !isPossible(i,j,value)) {
					return false;
				}
			}
		}
		return true;
	}
	
	//kontrollerar om värdet value är tillåtet på platsen rad i, kolumn j
	private boolean isPossible(int row, int col, int value) {
		
		for (int i = 0; i <= 8; i++) { //kontrollerar raden
			if (i != col && value == spelPlan[row][i]) {
				return false;
			}
		}
		
		for (int i = 0; i <= 8; i++) { //kontrollerar kolumnen
			if (i != row && value == spelPlan[i][col]) {
				return false;
			}
		}
		
		//kontrollerar rutan
		
		int relRow = row % 3;
		int relCol = col % 3;
		
		int startRow = row - row % 3;
		int startCol = col - col % 3;
		
		for (int i = 0; i < 3 ; i++) {
			for (int j = 0; j < 3; j++) {
				if (!(i == relRow && j == relCol) && value == spelPlan[startRow + i][startCol + j]) {
					return false;
				}
			}
		}
		
		return true;
	}

}
