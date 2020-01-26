package programLogic.prioritizeWinningTheGameThisTurn;

import programLogic.prioritizeWinningTheGameThisTurn.subComponents.AttemptToCompleteAColumn;
import programLogic.prioritizeWinningTheGameThisTurn.subComponents.AttemptToCompleteARow;
import programLogic.prioritizeWinningTheGameThisTurn.subComponents.attemptToCompleteADiagonal.AttemptToCompleteADiagonal;

public class PrioritizeWinningTheGameThisTurn
{	
	public static void attemptToWinTheGameThisTurn()
	{
		AttemptToCompleteARow.attemptToCompleteARow();
		AttemptToCompleteAColumn.attemptToCompleteAColumn();
		AttemptToCompleteADiagonal.attemptToCompleteADiagonal();
	}
	
} // End of class.
