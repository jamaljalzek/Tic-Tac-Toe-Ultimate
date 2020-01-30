package programLogic.computerOpponentLogic.mediumMode;

import programLogic.Game;
import programLogic.computerOpponentLogic.mediumMode.subComponents.SearchForColumnClosestToCompletionByComputer;
import programLogic.computerOpponentLogic.mediumMode.subComponents.SearchForDiagonalClosestToCompletionByComputer;
import programLogic.computerOpponentLogic.mediumMode.subComponents.SearchForRowClosestToCompletionByComputer;
import programLogic.computerOpponentLogic.mediumMode.subComponents.SelectPathClosestToCompletionByComputer;

public class MediumMode
{	
	/**
	 * During this difficulty, the computer does use a strategy to win. Specifically, it will try
	 * to place its tiles in a row, column, or diagonal to win.
	 * The strategy can be described as follows:
	 * 1. Find the row that is closest to being completed (by the computer).
	 * 2. Find the column that is closest to being completed.
	 * 3. Find the diagonal (out of the 2 that go through the center) that is closest to being completed.
	 * 4. Finally, select the most favorable row, column, or diagonal, and randomly choose an available spot inside.
	 *  
	 * @param Game.thisGame()
	 */
	public static void mediumMode()
	{
		SearchForRowClosestToCompletionByComputer.searchForRowClosestToCompletionByComputer();
		SearchForColumnClosestToCompletionByComputer.searchForColumnClosestToCompletionByComputer();
		SearchForDiagonalClosestToCompletionByComputer.searchForDiagonalClosestToCompletionByComputer();		
		SelectPathClosestToCompletionByComputer.selectPathClosestToCompletionByComputer();
	}
	
} // End of class.
