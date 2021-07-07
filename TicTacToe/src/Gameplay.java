import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gameplay {

	static ArrayList<Integer> playerPositions = new ArrayList<>();
	static ArrayList<Integer> cpuPositions = new ArrayList<>();
	
	public static void printBoard(char[][] gameBoard) {
		for(char[] row: gameBoard) {
			for(char sym:row) {
				System.out.print(sym);
			}
			System.out.println();
		}
	}
	
	public static void placeMark(char[][] gameBoard,int pos,String user) {
		int rowVal = 0, colVal = 0;
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);
		}
		else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		if(pos < 4) {
			rowVal = 0;
			colVal = (pos - 1)*2;
		}
		else if (pos < 7) {
			rowVal = 2;
			colVal = (pos - 4)*2;
		}
		else {
			rowVal = 4;
			colVal = (pos - 7)*2;
		}
		
		gameBoard[rowVal][colVal] = symbol;
	}
	
	public static String checkWinner() {
		
		List<Integer> topRow = Arrays.asList(1,2,3);
		List<Integer> midRow = Arrays.asList(4,5,6);
		List<Integer> bottomRow = Arrays.asList(7,8,9);
		List<Integer> leftCol = Arrays.asList(1,4,7);
		List<Integer> midCol = Arrays.asList(2,5,8);
		List<Integer> rightCol = Arrays.asList(3,6,9);
		List<Integer> cross1 = Arrays.asList(1,5,9);
		List<Integer> cross2 = Arrays.asList(3,5,7);
		
		List<List<Integer>> winningConditions = new ArrayList<>();
		winningConditions.add(topRow);
		winningConditions.add(midRow);
		winningConditions.add(bottomRow);
		winningConditions.add(leftCol);
		winningConditions.add(midCol);
		winningConditions.add(rightCol);
		winningConditions.add(cross1);
		winningConditions.add(cross2);
		
		for(List<Integer> l:winningConditions) {
			if(playerPositions.containsAll(l)) {
				return "Player wins!";
			}
			else if(cpuPositions.containsAll(l)) {
				return "AI wins!";
			}
			else if(!(playerPositions.containsAll(l)) && (playerPositions.size() + cpuPositions.size() >= 9)) {
				return "Draw";
			}
		}
		
		return "";
	}
}
