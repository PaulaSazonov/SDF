
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This is a variant of the classic Whack-A-Mole game. The purpose is to find
 * and whack the moles hidden in the garden. The garden is a two-dimensional
 * array, and the user has limited attempts to find the moles.
 *
 */
public class WhackAMole {
	private int score;
	private int molesLeft;
	private int attemptsLeft;
	private char[][] molegrid;

	/**
	 * Constructor for the game, instance variables initialized
	 * 
	 * @param numAttempts
	 *            Sets the number of attempts a player has
	 * @param gridDimension
	 *            Sets the dimensions for the two-dimensional array
	 */
	public WhackAMole(int numAttempts, int gridDimension) {
		this.attemptsLeft = numAttempts;
		this.molegrid = new char[gridDimension][gridDimension];
		this.molesLeft = 0;
		this.score = 0;
		for (int i = 0; i < gridDimension; i++) {
			for (int j = 0; j < gridDimension; j++) {
				molegrid[i][j] = '*';
			}
		}
	}

	/**
	 * A method that places a mole in a given location, according to the coordinates
	 * given as parameters. Updates the number of moles placed in the garden.
	 * 
	 * @param x
	 *            Sets the horizontal coordinate to place the mole on the grid
	 * @param y
	 *            Sets the vertical coordinate to place the mole on the grid
	 * @return Returns true if placing the mole is possible, false if not
	 */
	public boolean place(int x, int y) {
		if (molegrid[x][y] != '*') {
			System.out.println("There's already a mole there");
			return false;
		}
		molegrid[x][y] = 'M';
		molesLeft++;
		return true;
	}

	/**
	 * A method that takes a whack in a given location, according to the coordinates
	 * given as parameters. If that location contains a mole, the score, number of
	 * attemptsLeft and molesLeft are updated. If not, only attemptsLeft is updated.
	 * 
	 * @param x
	 *            The horizontal coordinate to try a whack
	 * @param y
	 *            The vertical coordinate to try a whack
	 */
	public void whack(int x, int y) {
		if (molegrid[x][y] == 'M') {
			molegrid[x][y] = 'W';
			molesLeft--;
			score++;
			attemptsLeft--;
			System.out.println("You got one! Your score is now " + score + ", you have " + getAttemptsLeft()
					+ " attempts left and " + molesLeft + " moles left to catch. \n");
			printGridToUser();
		} else if (molegrid[x][y] == 'W') {
			attemptsLeft--;
			System.out.println("You already whacked there, try another spot. \n");
		} else {
			attemptsLeft--;
			System.out.println("Oops, no moles there. You have " + getAttemptsLeft() + " attempts left.\n");
		}
		if (getAttemptsLeft() == 0) {
			System.out.println(
					"GAME OVER! \nYou used all your attempts. Thanks for playing!\nHere's where those pescy critters were hiding:\n");
			printGrid();
			return;
		} else if (molesLeft == 0) {
			System.out.println(
					" * * * * * Congratulations! * * * * * \n You discovered all those pescy moles and gave them a good whacking, your garden is now safe. \nHave a good day!");
			return;
		}

	}

	/**
	 * A method which prints the grid to the player without showing where the moles
	 * are. For every spot that has a recorded "whacked mole," character W is
	 * printed out, other spots printed out as *
	 */
	public void printGridToUser() {
		for (int i = 0; i < molegrid.length; i++) {
			for (int j = 0; j < molegrid.length; j++) {
				if (molegrid[i][j] == 'W') {
					System.out.print(molegrid[i][j] + " ");
				} else {
					System.out.print('*' + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * A method which prints the grid completely. The spots where the moles are
	 * located are printed out as M, the spots where the moles have been whacked are
	 * printed out as W. The spots that don't have a mole are printed out as *
	 */
	public void printGrid() {
		for (int i = 0; i < molegrid.length; i++) {
			for (int j = 0; j < molegrid.length; j++) {
				System.out.print(molegrid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	// Getters for testing purposes
	public int getAttemptsLeft() {
		return attemptsLeft;
	}
	public char[][] getMolegrid() {
		return this.molegrid;
	}
	public int getMolesLeft() {
		return this.molesLeft;
	}
	public int getScore() {
		return this.score;
	}

	/**
	 * Main method, where input is read from the player in the form of integers, and
	 * the game is played accordingly. Input coordinates -1, -1 indicate that the
	 * player gives up and wishes to end the game. If the player gives up they get
	 * to see the complete grid. The game ends if the player is able to uncover all
	 * the moles or if the player runs out of attempts.
	 * 
	 */
	public static void main(String[] args) {
		WhackAMole whackAMole = new WhackAMole(50, 10);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to playing Whack-A-Mole! \nYou have " + whackAMole.getAttemptsLeft()
				+ " attempts to find 10 moles hidden in the garden. \nEnter the coordinates in range 0-9 where you would like to try whacking a mole. \nIf You want to give up, enter coordinates -1,-1 to exit the game. \n");
		
		// the game is set by placing ten moles randomly in the grid.
		// (This could also be done using:
		// (int) Math.floor(Math.Random()*10)
		// for both indices, for each mole.
		
		whackAMole.place(1, 1);
		whackAMole.place(2, 5);
		whackAMole.place(5, 4);
		whackAMole.place(7, 8);
		whackAMole.place(2, 8);
		whackAMole.place(9, 9);
		whackAMole.place(0, 5);
		whackAMole.place(6, 0);
		whackAMole.place(3, 7);
		whackAMole.place(4, 2);

		do {
			try {
				System.out.println("Please enter a horizontal coordinate:");
				int horizontalIndex = scanner.nextInt();
				System.out.println("Please enter a vertical coordinate:");
				int verticalIndex = scanner.nextInt();
				if (horizontalIndex == -1 && verticalIndex == -1) {
					System.out.println("Thanks for playing! \nHere's where those pescy critters were hiding:\n");
					whackAMole.printGrid();
					break;
				} else if (horizontalIndex < 0 || verticalIndex < 0 || horizontalIndex > 9 || verticalIndex > 9) {
					System.out.println("Please enter positive numbers in range [0-9] as coordinates.");
				} else {
					whackAMole.whack(horizontalIndex, verticalIndex);
				}
				// Only integers are valid input coordinates
			} catch (InputMismatchException ime) {
				System.out.println("Please enter a valid number in range [0-9]");
			}
			scanner.nextLine();
		} while (whackAMole.getAttemptsLeft() > 0 && whackAMole.molesLeft > 0);
	}
}
