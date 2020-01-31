package programLogic.computerOpponentLogic.hardModeAndVeryHardMode.subComponents.prioritizeWinningTheGameThisTurn;

import programLogic.Game;
import programLogic.computerOpponentLogic.hardModeAndVeryHardMode.subComponents.prioritizeWinningTheGameThisTurn.subComponents.AttemptToCompleteAColumn;
import programLogic.computerOpponentLogic.hardModeAndVeryHardMode.subComponents.prioritizeWinningTheGameThisTurn.subComponents.AttemptToCompleteARow;
import programLogic.computerOpponentLogic.hardModeAndVeryHardMode.subComponents.prioritizeWinningTheGameThisTurn.subComponents.attemptToCompleteADiagonal.AttemptToCompleteADiagonal;

public class PrioritizeWinningTheGameThisTurn
{	
	public static void attemptToWinTheGameThisTurn()
	{
		AttemptToCompleteARow.attemptToCompleteARow();
		if (Game.isStillComputersTurn())
		{
			AttemptToCompleteAColumn.attemptToCompleteAColumn();
		}
		if (Game.isStillComputersTurn())
		{
			AttemptToCompleteADiagonal.attemptToCompleteADiagonal();
		}
	}
	
} // End of class.
