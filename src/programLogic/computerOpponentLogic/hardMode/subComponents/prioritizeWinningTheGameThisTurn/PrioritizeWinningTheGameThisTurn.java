package programLogic.computerOpponentLogic.hardMode.subComponents.prioritizeWinningTheGameThisTurn;

import programLogic.Game;
import programLogic.computerOpponentLogic.hardMode.subComponents.prioritizeWinningTheGameThisTurn.subComponents.AttemptToCompleteAColumn;
import programLogic.computerOpponentLogic.hardMode.subComponents.prioritizeWinningTheGameThisTurn.subComponents.AttemptToCompleteARow;
import programLogic.computerOpponentLogic.hardMode.subComponents.prioritizeWinningTheGameThisTurn.subComponents.attemptToCompleteADiagonal.AttemptToCompleteADiagonal;

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
