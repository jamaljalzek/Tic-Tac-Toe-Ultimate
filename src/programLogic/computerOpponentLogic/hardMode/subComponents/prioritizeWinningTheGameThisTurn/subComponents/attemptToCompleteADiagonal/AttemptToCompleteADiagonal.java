package programLogic.computerOpponentLogic.hardMode.subComponents.prioritizeWinningTheGameThisTurn.subComponents.attemptToCompleteADiagonal;

import programLogic.GameBoard;

public class AttemptToCompleteADiagonal
{
	public static void attemptToCompleteADiagonal()
	{
		AttemptToCompleteTheDownRightDiagonal.attemptToCompleteTheDownRightDiagonal();
		AttemptToCompleteTheUpRightDiagonal.attemptToCompleteTheUpRightDiagonal();
	}
	
	
	public static boolean canThisDiagonalBeCompletedThisTurn(int computerTileCount)
	{
		int numberOfSpotsInEachDiagonal = GameBoard.getDimension();
		return computerTileCount == (numberOfSpotsInEachDiagonal - 1);
	}

} // End of class.
