import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) {
		char[][] gameBoard = 
							{
								{' ','|',' ','|',' '},
								{'-','+','-','+','-'},
								{' ','|',' ','|',' '},
								{'-','+','-','+','-'},
								{' ','|',' ','|',' '}
							};
		
		Gameplay.printBoard(gameBoard);
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			try {
				
				System.out.println("Enter your next move (1 to 9):");
				int playerMove = scan.nextInt();
				while(Gameplay.playerPositions.contains(playerMove) || Gameplay.cpuPositions.contains(playerMove)) {
					System.out.println("Position taken! Enter a different position");
					playerMove = scan.nextInt();
				}
				
				Gameplay.placeMark(gameBoard,playerMove,"player");
				
				String result = Gameplay.checkWinner();
				if(result.equals("Player wins!") || result.equals("AI wins!") || result.equals("Draw")) {
					Gameplay.printBoard(gameBoard);
					System.out.println(result);
					break;
				}
				
				Random rand = new Random();
				int cpuMove = rand.nextInt(9) + 1;
				while(Gameplay.playerPositions.contains(cpuMove) || Gameplay.cpuPositions.contains(cpuMove)) {
					if(Gameplay.playerPositions.size() + Gameplay.cpuPositions.size() >= 9) {
						break;
					}
					cpuMove = rand.nextInt(9) + 1;
				}
				
				Gameplay.placeMark(gameBoard,cpuMove,"cpu");
				
				result = Gameplay.checkWinner();
				if(result.equals("Player wins!") || result.equals("AI wins!")) {
					Gameplay.printBoard(gameBoard);
					System.out.println(result);
					break;
				}

				Gameplay.printBoard(gameBoard);
				
			} catch (InputMismatchException e) {
				System.out.println("Incorrect input");
				scan.next();
			}
		}
		scan.close();
	}
}
